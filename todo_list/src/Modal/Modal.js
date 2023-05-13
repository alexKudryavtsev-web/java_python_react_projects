import React from 'react'
import './Modal.css'

export default class Modal extends React.Component {
    state = {
        isOpen: false
    }

    toggle = () => {
        this.setState({
            isOpen: !this.state.isOpen
        })
    }

    render() {
        return (
            <React.Fragment>                
                <h1 onClick={this.toggle} className='link'>TodoList Online</h1>
                {   this.state.isOpen
                    ? <div className='modal'>
                        <div className='modal-body'>
                            <h2>TodoList</h2>
                            <p>This project was created so that people can write their own in a convenient UI</p>
                            <button onClick={this.toggle}>Close</button>
                        </div>
                    </div>
                    : null
                }
            </React.Fragment>
        )
    }
}