import React from 'react'
import './faceList.css'
import PropTypes from 'prop-types'

function Name({ name }) {
    return <div className='Face-name'>
        { name }
    </div>
}

Name.propTypes = {
    name: PropTypes.string.isRequired
}

export default Name