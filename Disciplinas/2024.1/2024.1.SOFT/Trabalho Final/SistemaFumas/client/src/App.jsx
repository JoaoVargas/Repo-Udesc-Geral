import React, { Fragment } from "react"
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { GeneralContextProvider } from "./contexts/GeneralContext.jsx";

import Homepage from "./routes/Homepage.jsx";
import Fornecedores from "./routes/Fornecedores.jsx";

const App = () => {
  return (
    <main className="">
      <GeneralContextProvider>
        <Router>
          <Routes>
            <Route 
              path="/"
              element={<Homepage />}
              />
            <Route 
              path="/fornecedores"
              element={<Fornecedores />}
              />
          </Routes>
        </Router>
      </GeneralContextProvider>
    </main>
  );
}

export default App