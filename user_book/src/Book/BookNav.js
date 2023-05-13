import React from 'react'
import PropTypes from 'prop-types'
import BookLink from './BookLink'
import { Switch } from 'antd'
import './Book.css'

function BookNav({ records, toggleMode }) {
    return (
        <div
            className='Book-nav'>
            <div
                className='Book-nav__switch'>
                <Switch onChange={toggleMode} />
            </div>
            {
                records.map(value =>
                    <BookLink
                        date={value.date}
                        key={value.date} />
                )
            }
        </div>
    )
}

BookNav.propTypes = {
    records: PropTypes.arrayOf(PropTypes.object).isRequired
}

export default BookNav