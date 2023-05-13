import React, { useContext } from 'react'
import PropTypes from 'prop-types'

import Context from '../context.js'

const style = {
    li: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        padding: '.5rem 1rem',
        border: '1px solid #ccc',
        borderRadius: 4,
        marginBottom: '.5rem'
    },
    input: {
        marginRight: 10
    }
}

function TodoItem({ todo, changeComplete }) {
    const { removeTodo } = useContext(Context)

    let classes = []
    if(todo.completed) {
        classes.push('done')
    }

    return <li style={style.li}>
        <span className={classes.join(' ')}>
            <input type="checkbox" checked={todo.completed} style={style.input}
                onChange={() => changeComplete(todo.id)} />
            {todo.title}
        </span>

        <button onClick={() => removeTodo(todo.id)}>&times;</button>
    </li>
}

TodoItem.propTypes = {
    todo: PropTypes.object.isRequired,
    changeComplete: PropTypes.func.isRequired
}

export default TodoItem