import React from 'react'
import Headline from './Headline'
import Menu from './Menu'
import './style.css'

function Header() {
    return <header className='Header'>
        <Headline/>
        <Menu/>
    </header>
}

export default Header