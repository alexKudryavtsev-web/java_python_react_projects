import React from 'react'
import './style.css'

interface Props {
    clear: Function,
    remove: Function,
}

function TodoPanel({ clear, remove }: Props) {
    return <div>
        <button className='Btn' onClick={() => clear()}>
            Clear
        </button>
        <button className='Btn' onClick={() => remove()}>
            Remove
        </button>
    </div>
}

export default TodoPanel