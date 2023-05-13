import React, { useState, useEffect } from 'react'
import { ITodo, defaultTodos } from './todo'
import TodoList from './TodoList/TodoList'
import TodoScriber from './TodoScriber/TodoScriber'
import './App.css'
import TodoPanel from './TodoPanel/TodoPanel'

function App() {
  const [todos, setTodos] = useState<ITodo[]>(defaultTodos)

  useEffect(() => {
    const saved = JSON.parse(localStorage.getItem('todos') || '[]') as ITodo[]
    setTodos(saved)
  }, [])

  useEffect(() => {
    localStorage.setItem('todos', JSON.stringify(todos))
  }, [todos])

  function add(text: string) {
    setTodos(todos.concat({
      id: Date.now(),
      text,
      complete: false
    }))
  }

  function remove() {
    setTodos(todos.filter(el => !el.complete))
  }

  function toggleComplete(id: number) {
    setTodos(
      todos.map(el => {
        if (el.id === id)
          return {
            ...el,
            complete: !el.complete
          }
        return el
      })
    )
  }

  function clear() {
    setTodos([])
  }

  return <div className='App'>
    <TodoScriber addTodo={add} />
    <TodoPanel clear={clear} remove={remove}/>
    <TodoList todos={todos} toggleComplete={toggleComplete} />
  </div>
}

export default App