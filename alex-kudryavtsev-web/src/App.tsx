import React from 'react'
import { Route, Switch } from 'react-router-dom'
import Contact from './page/Contact'
import About from './page/About'
import Blog from './page/Blog'
import Header from './component/Header/Header'
import './globalStyle.css'

function App() {
  return <>
    <Header />
    <main>
      <Switch>
        <Route component={About} path='/' exact />
        <Route component={Blog} path='/blog' />
        <Route component={Contact} path='/contact' />
      </Switch>
    </main>
  </>
}

export default App
