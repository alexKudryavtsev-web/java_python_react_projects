import React from 'react'
import { FaAddressCard, FaBook, FaEnvelope } from 'react-icons/fa'
import MenuItem from './MenuItem'
import './style.css'

export interface IRoute {
    icon: JSX.Element,
    text: string,
    to: string
}

const routes: IRoute[] = [
    {
        icon: <FaAddressCard />,
        text: 'about',
        to: '/'
    },
    {
        icon: <FaBook />,
        text: 'blog',
        to: '/blog'
    },
    {
        icon: <FaEnvelope />,
        text: 'contact',
        to: './contact'
    }
]

function Menu() {
    return <ul className={'Header-menu'}>
        {routes.map(el =>
            <MenuItem {...el} key={el.to} />
        )}
    </ul>
}

export default Menu