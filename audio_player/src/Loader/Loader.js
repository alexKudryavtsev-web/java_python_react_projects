import React, { useRef } from 'react'
import './loader.css'
import { FaMusic } from 'react-icons/fa'

function Loader({ uploadFile }) {
    const fileRef = useRef(null)

    function handle() {
        let file = fileRef.current.files[0]
        let url = URL.createObjectURL(file)
        uploadFile(url)
    }

    return <div>
        <label htmlFor='file-upload' className='CustomUpload'><FaMusic/></label>
        <input ref={fileRef} id='file-upload' type="file" className='Upload' onChange={handle}/>
    </div>
}

export default Loader