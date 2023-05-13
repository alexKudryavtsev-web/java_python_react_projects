export interface ITodo {
    id: number,
    text: string,
    complete: boolean
}

export const defaultTodos: ITodo[] = [
    {
        id: Date.now(),
        text: 'Create new todo',
        complete: false
    },
    {
        id: Date.now() + 1,
        text: 'Delete this todo',
        complete: false
    }
]