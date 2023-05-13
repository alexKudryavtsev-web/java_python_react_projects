import React from 'react'
import { withRouter } from 'react-router-dom'

function Article({ match }) {
    return <React.Fragment>
        <h1>Article {match.params.id}</h1>
    </React.Fragment>
}

export default withRouter(Article)