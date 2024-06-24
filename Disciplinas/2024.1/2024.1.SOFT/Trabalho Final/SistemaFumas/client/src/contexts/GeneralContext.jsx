import { createContext } from 'react';
import { useState, useEffect } from 'react';

import toast, { Toaster } from 'react-hot-toast';

export const GeneralContext = createContext();

export const GeneralContextProvider = (props) => {
  // const [teste, setTeste] = useState("teste")
  const [ currentPage, setCurrentPage ] = useState("/");
  const [ errorMsg, setErrorMsg ] = useState("");


  //FORNECEDORES
  const [ fornecedores, setFornecedores ] = useState([]);

  const inputFornecedor = async (body) => {
    try {
      const response = await fetch("http://localhost:3000/fornecedores", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body)
      })

      if (!response.ok) {
        const responseJson = await response.json()
        toast.error(responseJson.message);
        return;
      }

      toast.success("Fornecedor adicionado com sucesso.")
      getFornecedores();
    } catch (error) {
      console.error(error.message);
    }
  }

  const editFornecedor = async (object) => {
    const param = {
      cnpj_fornecedor: object.oldCnpj
    }
    const body = {
      cnpj: object.cnpj, 
      nome: object.nome, 
      cep: object.cep, 
      numero: object.numero
    }

    try {
      const response = await fetch(`http://localhost:3000/fornecedores/${object.oldCnpj}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body),
        param: JSON.stringify(param)
      })

      if (!response.ok) {
        const responseJson = await response.json()
        toast.error(responseJson.message);
        return;
      }

      toast.success("Fornecedor editado com sucesso.")
      getFornecedores();
    } catch (error) {
      console.error(error.message);
    }
  }

  const getFornecedores = async () => {
    try {
      const response = await fetch("http://localhost:3000/fornecedores");
      const jsonData = await response.json();

      setFornecedores(jsonData);
    } catch (error) {
      console.error(error);
    }
  }

  const deleteFornecedor = async (cnpj_fornecedor) => {
    try {
      const deleteFornecedor = await fetch(`http://localhost:3000/fornecedores/${cnpj_fornecedor}`, {
        method: "DELETE"
      });

      const responseJson = await deleteFornecedor.json();
      
      toast.success(`Fornecedor deletado com sucesso.`);
      getFornecedores();
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <GeneralContext.Provider
      value={{
        currentPage, setCurrentPage,
        fornecedores, inputFornecedor, getFornecedores, deleteFornecedor, editFornecedor,
        errorMsg, setErrorMsg,
      }}
    >
      {props.children}
      <Toaster   
      toastOptions={{
        className: 'rounded border',
      }}
      position="top-right"/>
    </GeneralContext.Provider>
  );
};
