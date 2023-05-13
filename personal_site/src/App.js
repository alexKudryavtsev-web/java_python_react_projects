import React from 'react'
import { Switch, Route } from 'react-router-dom'
import About from './page/About'
import Blog from './page/Blog'
import Contact from './page/Contact'
import Header from './components/Header.js/Header'
import Article from './page/Article'
import Error from './page/Error'

function App() {
  return <>
    <Header />
    <main>
      <Switch>
        <Route path='/' component={About} exact />
        <Route path='/blog/:id' component={Article} />
        <Route path='/blog' component={Blog} />
        <Route path='/contact' component={Contact} />
        <Route component={Error} />
      </Switch>
    </main>
  </>
}

export default App
