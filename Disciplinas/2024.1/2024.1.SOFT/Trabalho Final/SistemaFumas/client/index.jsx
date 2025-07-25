import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
// import "bootstrap/dist/css/bootstrap.css"
// import "bootstrap/dist/js/bootstrap.js"
import "bootstrap-icons/font/bootstrap-icons.min.css"
import './src/assets/index.css'

import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './src/App.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)
