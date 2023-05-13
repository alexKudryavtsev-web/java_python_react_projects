import React, { useState } from 'react'
import './App.css'
import Toolbar from './Toolbar/toolbar'
import Content from './Content/content'

function App() {
  const [rotate, setRotate] = useState(91)
  const [color, setColor] = useState(['#3399ff', '#685cca', '#ff66cc'])
  const [part, setPart] = useState([0, 50, 100])

  function calc() {
    let [cl1, cl2, cl3] = color
    let [pr1, pr2, pr3] = part

    return {
      background:
        `linear-gradient(${rotate}deg, ${cl1} ${pr1}%, ${cl2} ${pr2}%, ${cl3} ${pr3}%)`
    }
  }

  return (
    <div className="App">
      <Toolbar
        rotate={rotate} setRotate={setRotate}
        color={color} setColor={setColor}
        part={part} setPart={setPart}
        cssFun={calc}
      />
      <Content
        css={calc()} />
    </div>
  )
}

export default App
