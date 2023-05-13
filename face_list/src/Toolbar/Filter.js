import React from 'react'
import { Input } from 'antd'
import './toolbar.css'
import { connect } from 'react-redux'

function Filter({ filter, setFilter }) {
    return <Input
        className='Input'
        placeholder='filter'
        defaultValue={filter}
        onChange={e => setFilter(e.target.value)}
    />
}

export default connect(
    state => ({
        filter: state.filter
    }),
    dispatch => ({
        setFilter(payload) {
            dispatch({ type: 'SET_FILTER', payload })
        }
    })
)(Filter)