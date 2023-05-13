import React, { useEffect } from 'react'
import TodoList from './Todo/TodoList'
import Context from './context.js'
import TodoAdd from './Todo/TodoAdd.js'
import Loader from './Todo/Loader'
import Modal from './Modal/Modal'

let style = {
  wrapper: {
    paddingTop: '5rem',
    margin: '0 auto',
    width: 600
  }
}

export default function App() {
  const [todos, setTodos] = React.useState([])
  const [loading, setLoading] = React.useState(true)

  useEffect(() => {
    fetch('https://jsonplaceholder.typicode.com/todos?_limit=2')
      .then(response => response.json())
      .then(json => {
        setLoading(false)
        setTodos(json)
      })
  }, [])

  function toggle(id) {
    setTodos(
      todos.map(todo => {
        if (todo.id === id) {
          todo.completed = !todo.completed
        }
        return todo
      })
    )
  }

  function create(title) {
    setTodos(todos.concat([{
      title,
      completed: false,
      id: Date.now()
    }]))
  }

  function removeTodo(id) {
    setTodos(
      todos.filter(todo => todo.id !== id)
    )
  }

  return (
    <Context.Provider value={{ removeTodo }}>
      <div style={style.wrapper}>
        <Modal />
        <TodoAdd onCreate={create} />

        {loading
          ? <Loader />
          : null
        }

        {todos.length
          ? <TodoList todos={todos} onToggle={toggle} />
          : loading
            ? null
            : <p> Empty TodoList </p>
        }
      </div>
    </Context.Provider>
  )
}
