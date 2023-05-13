import React from 'react'
import './item.css'

function Item({ name, number }) {
    return <div className='Item'>
        <div className='Item-name'>
            {name}
        </div>
        <div className='Item-number'>
            {new Intl.NumberFormat().format(number)}
        </div>
    </div>
}

export default Item