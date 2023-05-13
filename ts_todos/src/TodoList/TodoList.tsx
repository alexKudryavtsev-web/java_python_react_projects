import React from 'react'
import { ITodo } from '../todo'
import TodoItem from './TodoItem'
import './style.css'

interface Props {
    todos: ITodo[],
    toggleComplete: Function
}

function TodoList({
    todos,
    toggleComplete,
}: Props) {
    return <div className='TodoList'>
        {todos.map(el =>
            <TodoItem
                toggleComplete={toggleComplete}
                todo={el}
                key={el.id}
            />
        )}
    </div>
}

export default TodoList