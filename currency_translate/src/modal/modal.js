import React from 'react'
import './modal.css'
import { Button } from 'antd'
import { CloseOutlined } from '@ant-design/icons/'

function Modal({ children, title, toggle }) {
    return <div className='Modal'>
        <div className='Modal-body'>
            <div className='Modal-header'>
                <div className='Modal-title'>{title}</div>
                <Button
                    onClick={toggle}
                    shape='circle'
                    type='danger'
                    icon={<CloseOutlined />}
                />
            </div>

            <div className='Modal-content'>
                {children}
            </div>
        </div>
    </div>
}

export default Modal