import React, { useState } from 'react'
import { ChromePicker } from 'react-color'
import { Range } from 'rc-slider'
import './point.css'

function Point({ color, setColor, part, setPart }) {
    const [index, setIndex] = useState(0)

    function getActive(part, newPart) {
        for (let i = 0; i < 3; i++)
            if (part[i] !== newPart[i])
                setIndex(i)

        setPart(newPart)
    }

    return <div className='Point'>
        <Range
            className='Point-range'
            min={0}
            max={100}
            count={2}
            value={part}
            pushable={true}
            onChange={value => getActive(part, value)}

        />

        <ChromePicker
            className='Point-colorPicker'
            color={color[index]}
            onChange={e => {
                let c = [...color]
                c[index] = e.hex
                
                setColor(c)
                console.log('set', color)
            }}
        />
        <p><strong>select:</strong> {index + 1} color</p>
    </div>
}

export default Point