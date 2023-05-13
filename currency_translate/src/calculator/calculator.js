import React, { useState } from 'react'
import InputCurrency from './inputCurrency'
import './calculator.css'

function Calculator({ rates }) {
    const [
        value,
        setValue
    ] = useState(10)

    const [
        firstCurrency,
        setFirstCurrency
    ] = useState('EUR')

    const [
        secondCurrency, 
        setSecondCurrency
    ] = useState('RUB')

    function fromCurrency(newValue, currency) {
        setValue((newValue / rates[currency]).toFixed(2))
    }

    function toCurrency(currency) {
        return (value * rates[currency]).toFixed(2)
    }

    return <div className='Calculator'>
        <InputCurrency
            rates={rates}
            value={toCurrency(firstCurrency)}
            setValue={fromCurrency}
            currency={firstCurrency}
            setCurrency={setFirstCurrency}
        />
        <InputCurrency
            rates={rates}
            value={toCurrency(secondCurrency)}
            setValue={fromCurrency}
            currency={secondCurrency}
            setCurrency={setSecondCurrency}
        />
    </div>
}

export default Calculator