import React from 'react'
import './header.css'
import { BankOutlined } from '@ant-design/icons'

import View from './view'

function Header({ base, rates }) {
    let now = new Date()

    return <div className='Header'>
        <div className='Header-title'>
            <BankOutlined className='Header-icon'/>
            Currency Translate
        </div>
        <div className='Header-subtitle'>
            {`${now.toLocaleDateString()}`}
        </div>
        <View base={base} rates={rates} />
    </div>
}

export default Header