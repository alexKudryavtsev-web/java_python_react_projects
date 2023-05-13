import React, { useState } from 'react'
import PropTypes from 'prop-types'

let style = {
    form: {
        marginBottom: '1rem',
    }
}

function useInputValue(defaultValue='') {
    let [value, setValue] = useState(defaultValue)

    return {
        bind: {
            value,
            onChange: event => setValue(event.target.value)
        },
        clear: () => setValue(''),
        value: () => value
    }
} 

function TodoAdd({ onCreate }) {
    let input = useInputValue()

    function submitHandler(event) {
        event.preventDefault()

        if (input.value().trim()) {
            onCreate(input.value())
            input.clear()
        }
    }

    return (
        <form style={style.form} onSubmit={submitHandler}>
            <input {...input.bind}/>
            <button type='submit'>Add Todo</button>
        </form>
    )
}

TodoAdd.propTypes = {
    onCreate: PropTypes.func.isRequired
}

export default TodoAdd