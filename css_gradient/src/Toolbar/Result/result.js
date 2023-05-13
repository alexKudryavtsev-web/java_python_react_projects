import React from 'react'
import CopyBtn from './copyBtn'
import './result.css'

function Result({ cssFun }) {
    let res = `background: ${cssFun().background}`

    return <div className='Result'>
        <CopyBtn value={res}/>
        <div className='Result-code'>
            { res }
        </div>
    </div>
}

export default Result