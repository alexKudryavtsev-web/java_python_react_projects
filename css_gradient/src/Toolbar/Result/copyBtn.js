import React from 'react'
import './result.css'
import { Button } from 'antd'
import { CopyOutlined } from '@ant-design/icons'

function CopyBtn({ value }) {

    function copy() {
        const el = document.createElement('textarea')
        el.value = value
        el.setAttribute('readonly', '')
        el.style.position = 'absolute'
        el.style.left = '-9999px'
        document.body.appendChild(el)
        el.select()
        document.execCommand('copy')
        document.body.removeChild(el)
    }

    return <Button
        className='Result-copyBtn'
        onClick={copy}>
        <CopyOutlined />
        Copy
    </Button>
}

export default CopyBtn