import React from 'react'
import Loader from './Loader/Loader'
import Player from './Player/Player'
import defaultSrc from './default.mp3'
import './App.css'

class App extends React.Component {
  constructor(props) {
    super(props)
    this.audioRef = React.createRef()
    this.state = {
      currentTime: 0,
      duration: 0,
      src: defaultSrc
    }
  }

  uploadFile = (url) => {
    this.setState({
      src: url
    })
  }

  componentDidMount() {
    this.audioRef.current.addEventListener('timeupdate', event => {
      let currentTime = event.target.currentTime
      this.setState({ currentTime })
    })
    this.audioRef.current.addEventListener('loadedmetadata', () => {
      let duration = this.audioRef.current.duration
      this.setState({ duration })
    })
  }

  play = () => {
    this.audioRef.current.play()
  }

  stop = () => {
    this.audioRef.current.pause()
  }

  again = () => {
    this.audioRef.current.currentTime = 0
  }

  setCurrent = event => {
    this.setState({
      currentTime: event.target.value
    })
    this.audioRef.current.currentTime = event.target.value
  }

  render() {
    return <div className='App'>
      <audio
        ref={this.audioRef}
        src={this.state.src}
      />

      <Player
        stop={this.stop}
        play={this.play}
        again={this.again}
        currentTime={this.state.currentTime}
        duration={this.state.duration}
        setCurrent={this.setCurrent}
      />

      <Loader
        uploadFile={this.uploadFile}
      />
    </div>
  }
}

export default App