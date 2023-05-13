import React from 'react'
import './player.css'

function Toddler({ currentTime, duration, setCurrent }) {
    return <input
        className='Toddler'
        type='range'
        min={0} 
        max={duration} 
        step={1}
        value={currentTime}
        onChange={setCurrent}
    />
}

export default Toddler