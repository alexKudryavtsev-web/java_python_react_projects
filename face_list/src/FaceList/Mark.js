import React from 'react'
import './faceList.css'
import { Switch } from 'antd'
import { CloseOutlined, CheckOutlined } from '@ant-design/icons/'
import { connect } from 'react-redux'
import PropTypes from 'prop-types'

function Face({ id, marked, toggleMark }) {
    return <Switch
        checkedChildren={<CheckOutlined />}
        unCheckedChildren={<CloseOutlined />}
        checked={marked}
        onChange={() => toggleMark(id)}
    />
}

Face.propTypes = {
    id: PropTypes.any.isRequired,
    marked: PropTypes.bool.isRequired
}

export default connect(
    null,
    dispatch => ({
        toggleMark(id) {
            dispatch({ type: 'TOGGLE_MARK_FACE', payload: id })
        }
    })
)(Face)