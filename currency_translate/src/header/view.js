import React, { useState } from 'react'
import { Button } from 'antd'
import Modal from '../modal/modal'
import Item from './item'

function View({ rates, base }) {
    const [mode, setMode] = useState(false)
    function toggle() {
        setMode(!mode)
    }

    return <>
        <Button onClick={toggle}>Watch Currency</Button>
        {mode &&
            <Modal title={`Currencies relative ${base}`} toggle={toggle}>
                <div className='List'>
                    {Object.keys(rates).map(key =>
                        <Item name={key} number={rates[key]} key={key} />
                    )}
                </div>
            </Modal>
        }
    </>
}

export default View