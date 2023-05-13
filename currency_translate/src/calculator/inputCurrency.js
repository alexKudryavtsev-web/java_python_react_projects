import React from 'react'
import { Select, InputNumber } from 'antd'
import './inputCurrency.css'

function InputCurrency({ rates, currency, setCurrency, value, setValue }) {

    function handleSelect(value) {
        setCurrency(value)
    }

    return <div className='InputCurrency'>
        <Select
            className='InputCurrency-select'
            value={currency}
            onChange={handleSelect}
        >
            {Object.keys(rates).map(el =>
                <Select.Option
                    key={el}
                    value={el}
                >
                    {el}
                </Select.Option>
            )}
        </Select>
        <InputNumber
            className='InputCurrency-number'
            placeholder='value'
            value={value}
            onChange={newValue => setValue(newValue, currency)}
        />
    </div>
}

export default InputCurrency