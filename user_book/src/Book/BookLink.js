import React, { useContext } from 'react'
import PropTypes from 'prop-types'
import Context from '../context'
import { Button } from 'antd'
import { DeleteOutlined } from '@ant-design/icons'
import './Book.css'

function BookContent({ date }) {
    let { removeRecord, selectRecord, select } = useContext(Context)
    let classes = ['Book-link']

    if (select === date) {
        classes.push('Book-link__active')
    }

    return (
        <div
            className={classes.join(' ')}>
            <span
                onClick={() => selectRecord(date)}>
                {date}
            </span>
            <Button
                className='icon'
                onClick={() => removeRecord(date)}>
                <DeleteOutlined />
            </Button>
        </div>
    )
}

BookContent.propTypes = {
    date: PropTypes.string.isRequired
}

export default BookContent