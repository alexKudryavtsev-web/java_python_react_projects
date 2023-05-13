import React from 'react'
import PropTypes from 'prop-types'
import TodoItem from './TodoItem.js'

let style = {
    ul: {
        listStyle: 'none',
        margin: 0,
        padding: 0
    }
}

function TodoList(props) {
    return (
        <ul style={style.ul}>
            {props.todos.map((todo, index) => 
                <TodoItem todo={todo} index={index} key={todo.id} changeComplete={props.onToggle} />
            )}
        </ul>
    )
}

TodoList.propTypes = {
    todos: PropTypes.arrayOf(PropTypes.object).isRequired,
    onToggle: PropTypes.func.isRequired
}

export default TodoList