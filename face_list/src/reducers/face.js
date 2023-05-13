const initState = [
    {
        name: 'Alexander',
        contact: 'alexKudryavtsev-web(github)',
        marked: false,
        id: 0
    }
]

function face(state = initState, action) {
    switch (action.type) {
        case 'ADD_FACE':
            return state.concat(action.payload)

        case 'DELETE_FACE':
            return state.filter(el => !el.marked)

        case 'TOGGLE_MARK_FACE':
            return state.map(el => ({
                ...el,
                marked: el.id === action.payload
                    ? !el.marked
                    : el.marked
            }))
        
        case 'UPDATE_FACE':
            return state.map(el => {
                if (el.id === action.payload.id)
                    return {
                        ...el,
                        ...action.payload.newVal,
                    }
                return { ...el }
            })
            
        default:
            return state
    }
}

export default face