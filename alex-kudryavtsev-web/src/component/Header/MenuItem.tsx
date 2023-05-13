import React from 'react'
import { IRoute } from './Menu'
import { NavLink } from 'react-router-dom'
import './style.css'

function MenuItem({ text, icon, to }: IRoute) {
    return <li>
        <NavLink
            className='Header-menuItem'
            activeClassName='Header-menuItem__active'
            to={to}
            exact
        >
            {icon}
            {text}
        </NavLink>
    </li>
}

export default MenuItem