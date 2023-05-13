import React from 'react'
import ReactDOM from 'react-dom'
import App from './App'

import { BrowserRouter } from 'react-router-dom'
import { createStore, applyMiddleware } from 'redux'
import { Provider } from 'react-redux'
import { composeWithDevTools } from 'redux-devtools-extension'
import thunk from 'redux-thunk'
import reducer from './reducer/index'

const plugin = composeWithDevTools(applyMiddleware(thunk))

const store = createStore(reducer, plugin)

const app = <Provider store={store}>
  <BrowserRouter>
    <App />
  </BrowserRouter>
</Provider>


ReactDOM.render(app, document.getElementById('root'))
