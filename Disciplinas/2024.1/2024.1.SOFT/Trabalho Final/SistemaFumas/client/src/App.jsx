import React, { Fragment } from "react"
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { GeneralContextProvider } from "./contexts/GeneralContext.jsx";

import Homepage from "./routes/Homepage.jsx";
import Fornecedores from "./routes/Fornecedores.jsx";
import Produtos from "./routes/Produtos.jsx";
import Itens from "./routes/Itens.jsx";

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
              path="/fornecedores/"
              element={<Fornecedores />}
              />
            <Route 
              path="/produtos/"
              element={<Produtos />}
              />
            <Route 
              path="/itens/"
              element={<Itens />}
              />
          </Routes>
        </Router>
      </GeneralContextProvider>
    </main>
  );
}

export default App