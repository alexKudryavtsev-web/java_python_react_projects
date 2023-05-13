import React from 'react'
import ReactDOM from 'react-dom'
import App from './App'
import { BrowserRouter } from 'react-router-dom'
import './global.css'

const app = <BrowserRouter>
  <App/>
</BrowserRouter>

ReactDOM.render(
  app,
  document.getElementById('root')
)
