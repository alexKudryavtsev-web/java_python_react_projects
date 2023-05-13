import { combineReducers } from 'redux'
import face from './face'
import filter from './filter'

const reducer = combineReducers({
    face,
    filter
})

export default reducer