import React from 'react'
import './toolbar.css'
import Title from './Title'
import AddBtn from './AddBtn'
import DeleteBtn from './DeleteBtn'
import Filter from './Filter'

function Toolbar() {
    return <div className='Toolbar'>
        <Title/>
        <AddBtn/>
        <DeleteBtn/>
        <Filter/>
    </div>
}

export default Toolbar