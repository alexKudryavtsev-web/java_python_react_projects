import React from 'react'
import Title from './title'
import Result from './Result/result'
import Rotate from './rotate'
import Point from './Point/point'

function Toolbar({ color, part, setColor, setPart, rotate, setRotate, cssFun }) {
    return <div className='Toolbar'>
        <Title />
        <Result
            cssFun={cssFun} />
        <Rotate
            rotate={rotate}
            setRotate={setRotate} />
        <Point
            color={color}
            part={part}
            setColor={setColor}
            setPart={setPart} />
    </div>
}

export default Toolbar