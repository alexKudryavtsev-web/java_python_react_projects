import React from 'react'
import { Button } from 'antd'
import { PlusOutlined } from '@ant-design/icons/'
import { connect } from 'react-redux'

function AddBtn({ addFace }) {
    return <Button type='danger' onClick={addFace}>
        <PlusOutlined />
        Add
    </Button>
}

export default connect(
    null,
    dispatch => ({
        addFace() {
            dispatch({
                type: 'ADD_FACE',
                payload: {
                    name: 'anonymous',
                    contact: null,
                    marked: false,
                    id: new Date().toString()
                }
            })
        }
    })
)(AddBtn)