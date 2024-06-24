import { createContext } from 'react';
import { useState, useEffect } from 'react';

export const GeneralContext = createContext();

export const GeneralContextProvider = (props) => {
  // const [teste, setTeste] = useState("teste")
  const [ currentPage, setCurrentPage ] = useState("/");


  //FORNECEDORES
  const [ fornecedores, setFornecedores ] = useState([]);

  const getFornecedores = async () => {
    try {
      const response = await fetch("http://localhost:3000/fornecedores");
      const jsonData = await response.json();

      setFornecedores(jsonData);
    } catch (error) {
      console.error(error);
    }
  }

  const deleteFornecedor = async (id) => {
    try {
      console.log(id);

      const deleteFornecedor = await fetch(`http://localhost:3000/fornecedores/${id}`, {
        method: "DELETE"
      });

      console.log(deleteFornecedor);
      getFornecedores();
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <GeneralContext.Provider
      value={{
        currentPage,
        setCurrentPage,
        fornecedores,
        getFornecedores,
        deleteFornecedor,
      }}
    >
      {props.children}
    </GeneralContext.Provider>
  );
};
