import React from 'react'
import './faceList.css'
import PropTypes from 'prop-types'

function Contact({ contact }) {
    return <div className='Face-contact'>
        { contact }
    </div>
}

Contact.propTypes = {
    contact: PropTypes.string
}

export default Contact