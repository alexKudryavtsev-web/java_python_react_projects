import React from 'react'
import Face from './Face'
import './faceList.css'
import { connect } from 'react-redux'

function FaceList({ face }) {
    return <div className='FaceList'>
        {face.map(el =>
            <Face key={el.id} {...el}/>
        )}
    </div>
}

export default connect(
    state => ({
        face: state.face.filter(el => el.name.toLowerCase().includes(state.filter.toLowerCase()))
    })
)(FaceList)