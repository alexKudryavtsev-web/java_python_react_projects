import React, { useState, useEffect } from 'react'
import axios from 'axios'

import Header from './header/header'
import Calculator from './calculator/calculator'

const baseURL = 'http://data.fixer.io/api/latest?access_key=7b01c87b5a5a6c4c614f2e26fd886b6b'

function App() {
  const [base, setBase] = useState(null)
  const [rates, setRates] = useState({})

  useEffect(() => {
    if (!base)
      axios
        .get(baseURL)
        .then(({ data }) => {
          setBase(data.base)
          setRates(data.rates)
        })
        .catch(err => {

        })
  })

  return (
    <div>
      <Header base={base} rates={rates} />
      <Calculator rates={rates} />
    </div>
  )
}

export default App
