import React, { useState } from 'react'
import BookNav from './Book/BookNav'
import BookContent from './Book/BookContent'
import BookScriber from './Book/BookScriber'
import Context from './context.js'
import './index.css'

function App() {
  const DEFAULT_VALUE = `# User Book Online

  ##### elements
  
    - form for creating new pages
    - navigation bar with page links and mode switch
    - in content you can read and edit the page
    - book format is Markdown
  
  #### enjoy using! `
  const DEFAULT_DATE = '2020-03-28'

  const [records, setRecords] = useState([
    { date: DEFAULT_DATE, value: DEFAULT_VALUE }
  ])
  const [select, setSelect] = useState(DEFAULT_DATE)
  const [mode, setMode] = useState(true)

  function removeRecord(date) {
    if (date === select) {
      setSelect(records[0].date)
    }

    if (records.length !== 1) {
      setRecords(
        records.filter(el => el.date !== date)
      )
    }
  }

  function toggleMode() {
    setMode(!mode)
  }

  function selectRecord(date) {
    setSelect(date)
  }

  function setRecord(date, value) {
    setRecords(
      records.map(record => {
        if (record.date === date)
          return {
            date: record.date,
            value
          }

        return record
      })
    )
  }

  function createRecord(date) {
    if (records.find(el => el.date !== date))
      setRecords(
        records.concat({
          date,
          value: `# ${date}\n` + DEFAULT_VALUE
        })
      )

  }

  return (
    <Context.Provider
      value={{ removeRecord, selectRecord, select }}>
      <div
        className='wrapper'>
        <BookScriber
          createRecord={createRecord} />
        <div
          className='main'>
          <BookContent
            record={records.find(el => el.date === select)}
            onChange={setRecord}
            mode={mode} />
          <BookNav
            records={records}
            toggleMode={toggleMode} />
        </div>
      </div>
    </Context.Provider>
  )
}

export default App
