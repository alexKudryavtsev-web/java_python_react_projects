import React from 'react'
import { NavLink } from 'react-router-dom'

function Header() {
    return <header>
        <h1>alexKudryavtsev-web</h1>
        <div>
            <NavLink to='/'>About</NavLink>
            <NavLink to='/blog'>Blog</NavLink>
            <NavLink to='/contact'>Contact</NavLink>
        </div>
    </header>
}

export default Header