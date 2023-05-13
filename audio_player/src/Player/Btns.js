import React from 'react'
import './player.css'

import { FaStopCircle, FaPlayCircle, FaRetweet } from 'react-icons/fa'
function Btns({ stop, play, again }) {
    return <>
        <button className='Btn' onClick={play}>
            <FaPlayCircle /> play
        </button>
        <button className='Btn Btn__success' onClick={stop}>
            <FaStopCircle /> stop
        </button>
        <button className='Btn Btn__danger' onClick={again}>
            <FaRetweet /> again
        </button>
    </>
}

export default Btns