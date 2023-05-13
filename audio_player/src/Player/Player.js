import React from 'react'
import Btns from './Btns'
import Toddler from './Toddler'
import './player.css'
import Time from './Time'

function Player({ play, stop, again, currentTime, duration, setCurrent }) {
    return <div className='Player'>
        <Btns
            play={play}
            stop={stop}
            again={again}
        />
        <Toddler
            currentTime={currentTime}
            duration={duration}
            setCurrent={setCurrent}
        />
        <Time
            currentTime={currentTime}
            duration={duration}
        />
    </div>
}

export default Player