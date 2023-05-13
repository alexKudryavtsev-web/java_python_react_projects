import React from 'react'

let style = {
    div: {
        display: 'flex', 
        justifyContent: 'center',
        alignItems: 'center'
    }
}

function Loader() {
    return <div style={style.div} className="lds-circle"><div/></div>
}

export default Loader