import React, { useState } from 'react'
import { EditOutlined } from '@ant-design/icons/'
import { Button, Input } from 'antd'
import './faceList.css'

import { connect } from 'react-redux'
import PropTypes from 'prop-types'

function FaceEdit({ id, update }) {
    const [show, setShow] = useState(false)
    const [name, setName] = useState('')
    const [contact, setContact] = useState('')

    function toggle() {
        setShow(!show)
    }

    function submit(e) {
        e.preventDefault()
        update(id, { name, contact })
        toggle()
    }

    return <>
        <Button onClick={toggle}>
            <EditOutlined />
            Edit
        </Button>

        {show &&
            <div className='Modal'>
                <form
                    onSubmit={submit}
                    className='Modal-content'
                >
                    <Input
                        placeholder='input new name'
                        value={name}
                        onChange={e => setName(e.target.value)}
                    />
                    <Input

                        placeholder='input new contact'
                        value={contact}
                        onChange={e => setContact(e.target.value)}
                    />
                    <div>
                        <Button
                            type="primary"
                            htmlType="submit"
                        >
                            Save
                        </Button>
                        <Button
                            style={{ marginLeft: '0.5rem' }}
                            onClick={toggle}
                        >
                            Cancel
                        </Button>
                    </div>
                </form>
            </div>
        }
    </>
}

FaceEdit.propTypes = {
    id: PropTypes.any.isRequired
}

export default connect(
    null,
    dispatch => ({
        update(id, newVal) {
            dispatch({ type: 'UPDATE_FACE', payload: { id, newVal } })
        }
    })
)(FaceEdit)