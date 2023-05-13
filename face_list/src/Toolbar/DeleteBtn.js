import React from 'react'
import { Button } from 'antd'
import { DeleteOutlined } from '@ant-design/icons/'
import { connect } from 'react-redux'

function DeleteBtn({ deleteFace }) {
    return <Button
        type='primary'
        onClick={deleteFace}
    >
        <DeleteOutlined />
        Delete
    </Button>
}

export default connect(
    null,
    dispatch => ({
        deleteFace() {
            dispatch({ type: 'DELETE_FACE' })
        }
    })
)(DeleteBtn)