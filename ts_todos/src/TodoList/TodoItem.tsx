import React from 'react'
import { ITodo } from '../todo'
import './style.css'

interface Props {
    todo: ITodo,
    toggleComplete: Function
}

function TodoItem({ todo, toggleComplete }: Props) {

    let classes = ['TodoItem-text']

    if (todo.complete)
        classes.push('TodoItem-text__completed')

    return <div className='TodoItem'>
        <div className={classes.join(' ')}>
            {todo.text}
        </div>
        <input
            type='checkbox'
            className='TodoItem-complete'
            checked={todo.complete}
            onChange={() => toggleComplete(todo.id)}
        />
    </div>
}

export default TodoItem