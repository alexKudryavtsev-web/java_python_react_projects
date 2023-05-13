import React from 'react'
import { InputNumber } from 'antd'
import './toolbar.css'

function Rotate({ rotate, setRotate }) {
    return <div className='Toolbar-rotate'>
        <InputNumber
            size="large"
            min={1}
            max={360}
            value={rotate}
            onChange={value => setRotate(value)} />
    </div>
}

export default Rotate