import React, { useState } from 'react'
import PropTypes from 'prop-types'
import { DatePicker } from 'antd'
import { SendOutlined } from '@ant-design/icons'
import './Book.css'

function useDateValue(defaultValue = null) {
    let [value, setValue] = useState(defaultValue)

    return {
        bind: {
            value,
            onChange: moment => setValue(moment)
        },
        clear: () => setValue(null),
        value: () => value
    }
}

function BookScriber({ createRecord }) {
    let date = useDateValue()

    function submitHandler(event) {
        event.preventDefault()

        if (date.value() !== null) {
            createRecord(date.value().format('YYYY-MM-DD'))
            date.clear()
        }
    }

    return (
        <form
            onSubmit={submitHandler}
            className='Book-scriber'>
            <DatePicker
                {...date.bind}
                className='Book-scriber__picker' />
            <button
                className='icon'
                type="submit">
                <SendOutlined />
            </button>
        </form>
    )
}

BookScriber.propTypes = {
    createRecord: PropTypes.func.isRequired
}

export default BookScriber