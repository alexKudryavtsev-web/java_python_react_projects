import React from 'react'
import './player.css'

function format(sec) {
    let sc = Math.floor(sec % 60)
    let mn = Math.floor(sec / 60)

    if (sc < 10) sc = `0${sc}`
    if (mn < 10) mn = `0${mn}`

    return `${mn}:${sc}`
}

function Time({ currentTime, duration }) {
    return <div className='Time'>
        {format(currentTime)} / {format(duration)}
    </div>
}

export default Time