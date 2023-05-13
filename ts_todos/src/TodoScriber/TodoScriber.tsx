import React, { useState } from 'react'
import './style.css'

interface Props {
    addTodo: Function
}

function TodoScriber({ addTodo }: Props) {
    const [text, setText] = useState<string>('')

    function submit(e: React.FormEvent) {
        e.preventDefault()

        if (text.trim()) {
            addTodo(text)
            setText('')
        }
    }

    return <form
        className='TodoScriber'
        onSubmit={e => submit(e)}
    >
        <input
            className='Input'
            type='text'
            value={text}
            placeholder='text for new todo'
            onChange={e => setText(e.target.value)}
        />

        <button
            type='submit'
            className='Submit'
        >
            Submit
        </button>
    </form>
}

export default TodoScriber