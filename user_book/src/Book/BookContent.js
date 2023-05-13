import React from 'react'
import PropTypes from 'prop-types'
import ReactMarkdown from 'react-markdown'
import { Input } from 'antd'
import './Book.css'

const { TextArea } = Input

function BookContent({ record, mode, onChange }) {
    return (
        <div className='Book-content'>
            {
                mode
                    ? <ReactMarkdown
                        source={record.value} />
                    : <div>
                        <TextArea
                            className='Book-content__textarea'
                            defaultValue={record.value}
                            onChange={event => onChange(record.date, event.target.value)}>
                        </TextArea>
                    </div>
            }
        </div>
    )
}


BookContent.propTypes = {
    record: PropTypes.object.isRequired,
    mode: PropTypes.bool.isRequired,
    onChange: PropTypes.func.isRequired
}

export default BookContent