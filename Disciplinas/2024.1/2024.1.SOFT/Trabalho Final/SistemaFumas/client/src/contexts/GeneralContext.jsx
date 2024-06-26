import { createContext } from 'react';
import { useState, useEffect } from 'react';

import toast, { Toaster } from 'react-hot-toast';

export const GeneralContext = createContext();

export const GeneralContextProvider = (props) => {
  // const [teste, setTeste] = useState("teste")
  const [ currentPage, setCurrentPage ] = useState("/");


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

  // PRODUTOS
  const [ produtos, setProdutos ] = useState([]);

  const inputProduto = async (body) => {
    try {
      const response = await fetch("http://localhost:3000/produtos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body)
      })

      if (!response.ok) {
        const responseJson = await response.json()
        toast.error(responseJson.message);
        return;
      }

      toast.success("Produto adicionado com sucesso.")
      getProdutos();
    } catch (error) {
      console.error(error.message);
    }
  }
  
  const editProduto = async (object) => {
    const param = {
      id_produto: object.id
    }
    const body = {
      cnpj_fornecedor_produto: object.cnpjFornecedor, 
      nome_produto: object.nome, 
      marca_produto: object.marca, 
      tipo_unidade_produto: object.tipoUnidade,
      quantidade_unidades_produto: object.quantidade,
      preco_produto: object.preco,
    }
    
    try {
      const response = await fetch(`http://localhost:3000/produtos/${object.id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body),
        param: JSON.stringify(param)
      })
      
      if (!response.ok) {
        const responseJson = await response.json();
        toast.error(responseJson.message);
        return;
      }
      
      toast.success("Produto editado com sucesso.")
      getProdutos();
    } catch (error) {
      console.error(error.message);
    }
  }
  
  const getProdutos = async () => {
    try {
      const response = await fetch("http://localhost:3000/produtos");

      if (!response.ok) {
        const jsonData = await response.json();
        toast.error(jsonData.message);
        return;
      }

      const jsonData = await response.json();
      setProdutos(jsonData);
    } catch (error) {
      console.error(error);
    }
  }

  const deleteProduto = async (id_produto) => {
    console.log(id_produto);

    try {
      const response = await fetch(`http://localhost:3000/produtos/${id_produto}`, {
        method: "DELETE"
      });

      if (!response.ok) {
        const jsonData = await response.json()
        toast.error(jsonData.message);
        return;
      }

      const jsonData = await response.json()
      console.log(jsonData);
      toast.success(`Produto deletado com sucesso.`);
      getProdutos();
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <GeneralContext.Provider
      value={{
        currentPage, setCurrentPage,
        fornecedores, inputFornecedor, getFornecedores, deleteFornecedor, editFornecedor,
        produtos, inputProduto, getProdutos, editProduto, deleteProduto
      }}
    >
      {props.children}
      <Toaster   
      toastOptions={{
        className: 'rounded',
            style: {
              background: '#202428',
              color: '#F7F8F9',
            },
      }}
      position="top-right"/>
    </GeneralContext.Provider>
  );
};
