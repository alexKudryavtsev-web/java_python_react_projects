import React from 'react'
import './faceList.css'

import FaceEdit from './FaceEdit'
import Mark from './Mark'
import Contact from './Contact'
import Name from './Name'

import PropTypes from 'prop-types'

function Face({ name, contact, id, marked }) {
    return <div className='Face'>
        <Mark marked={marked} id={id}/>
        <FaceEdit id={id}/>
        <Name name={name}/>
        <Contact contact={contact}/>
    </div>
}

Face.propTypes = {
    name: PropTypes.string.isRequired,
    contact: PropTypes.string.isRequired,
    id: PropTypes.any.isRequired,
    marked: PropTypes.bool.isRequired
}

export default Face