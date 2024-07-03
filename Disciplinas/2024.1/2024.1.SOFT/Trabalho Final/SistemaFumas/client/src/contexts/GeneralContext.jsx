import { createContext } from 'react';
import { useState, useEffect } from 'react';

import toast, { Toaster } from 'react-hot-toast';

export const GeneralContext = createContext();

export const GeneralContextProvider = (props) => {
  // const [teste, setTeste] = useState("teste")
  const [ currentPage, setCurrentPage ] = useState("/");


  //FORNECEDORES
  const [ fornecedor, setFornecedor ] = useState({});
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

  const getFornecedor = async (cnpj_fornecedor) => {
    try {
      const response = await fetch(`http://localhost:3000/fornecedores/${cnpj_fornecedor}`);

      if (!response.ok) {
        const jsonData = await response.json();
        toast.error(jsonData.message);
        return;
      }

      const jsonData = await response.json();
      setFornecedor(await jsonData);
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
  const [ produto, setProduto ] = useState({});
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

  const getProduto = async (id_produto) => {
    try {
      const response = await fetch(`http://localhost:3000/produtos/${id_produto}`);

      if (!response.ok) {
        const jsonData = await response.json();
        toast.error(jsonData.message);
        return;
      }

      const jsonData = await response.json();
      setProduto(await jsonData);
    } catch (error) {
      console.error(error);
    }
  }

  const deleteProduto = async (id_produto) => {
    // console.log(id_produto);

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
      toast.success(`Produto deletado com sucesso.`);
      getProdutos();
    } catch (error) {
      console.error(error);
    }
  }

  // HISTORICO
  const [ historico, setHistorico ] = useState([]);

  const getHistorico = async (id_produto) => {
    try {
      const response = await fetch(`http://localhost:3000/historicos/${id_produto}`);

      if (!response.ok) {
        const jsonData = await response.json();
        toast.error(jsonData.message);
        return;
      }

      const jsonData = await response.json();

      setHistorico(await jsonData.map((item) => {
        item.data_preco = new Date(item.data_preco)
          .toLocaleString('pt-BR', {
            dateStyle: 'short',
            timeStyle: 'medium',
          });
        return item
      }));
    } catch (error) {
      console.error(error);
    }
  }

  // ESTOQUE
  const [ estoque, setEstoque ] = useState({});
  const [ estoqueAll, setEstoqueAll ] = useState();
  const [ estoques, setEstoques ] = useState([]);
  const [ numBaixasMes, setNumBaixasMes ] = useState(0);
  const [ numEntradasMes, setNumEntradasMes ] = useState(0);

  const getEstoque = async (id_produto) => {
    try {
      // console.log(id_produto, "getEstoque");
      const response = await fetch(`http://localhost:3000/estoque/${id_produto}`);

      if (!response.ok) {
        const jsonData = await response.json();
        toast.error(jsonData.message);
        return;
      }

      const jsonData = await response.json();

      setEstoque(await jsonData);
      // console.log(estoque, "estoque ");
    } catch (error) {
      console.error(error);
    }
  }

  const getEstoqueAll = async () => {
    try {
      // console.log(id_produto, "getEstoque");
      const response = await fetch(`http://localhost:3000/estoque/`);

      if (!response.ok) {
        const jsonData = await response.json();
        toast.error(jsonData.message);
        return;
      }

      const jsonData = await response.json();

      setEstoqueAll(await jsonData);
      // console.log(estoque, "estoque ");    
    } catch (error) {
      console.error(error);
    }
  }

  const getEstoques = async (id_produto) => {
    try {
      const response = await fetch(`http://localhost:3000/estoques/${id_produto}`);

      if (!response.ok) {
        const jsonData = await response.json();
        toast.error(jsonData.message);
        return;
      }

      const jsonData = await response.json();

      console.log(jsonData);

      setEstoques(await jsonData.map((item) => {
        item.data_modificacao = new Date(item.data_modificacao)
          .toLocaleString('pt-BR', {
            dateStyle: 'short',
            timeStyle: 'medium',
          });
        return item
      }));
    } catch (error) {
      console.error(error);
    }
  }

  const getNumBaixasMes = () => {
    function converterStringParaData(dataString) {
      const partes = dataString.split(/[\/, :]+/); // Divide a string em partes
      const dia = partes[0];
      const mes = partes[1] - 1; // Os meses em JavaScript vão de 0 a 11
      const ano = partes[2];
      const hora = partes[3];
      const minuto = partes[4];
      return new Date(ano, mes, dia, hora, minuto);
    }

    function calcularDiminuiçãoTotalNoMesAnterior(historico) {
      const dataAtual = new Date();
      const mesAtual = dataAtual.getMonth();
      const anoAtual = dataAtual.getFullYear();
      const mesAnterior = mesAtual === 0 ? 11 : mesAtual - 1; // Caso o mês atual seja janeiro (0), o mês anterior é dezembro (11)
      const anoMesAnterior = mesAtual === 0 ? anoAtual - 1 : anoAtual;

      // Filtra o histórico para obter apenas as entradas do mês anterior
      const historicoMesAnterior = historico.filter(entry => {
        const data = converterStringParaData(entry.data_modificacao);
        return data.getMonth() === mesAnterior && data.getFullYear() === anoMesAnterior;
      });

      // Calcula a diminuição total do estoque no mês anterior
      let diminuicaoTotal = 0;
      for (let i = 0; i < historicoMesAnterior.length - 1; i++) {
        const estoqueAtual = historicoMesAnterior[i].quantidade_estoque;
        const estoqueSeguinte = historicoMesAnterior[i + 1].quantidade_estoque;
        if (estoqueSeguinte < estoqueAtual) {
          diminuicaoTotal += (estoqueAtual - estoqueSeguinte);
        }
      }

      return diminuicaoTotal;
    }

    setNumBaixasMes(calcularDiminuiçãoTotalNoMesAnterior(estoques));
  }

  const getEntradasMes = () => {
    function converterStringParaData(dataString) {
      const partes = dataString.split(/[\/, :]+/); // Divide a string em partes
      const dia = partes[0];
      const mes = partes[1] - 1; // Os meses em JavaScript vão de 0 a 11
      const ano = partes[2];
      const hora = partes[3];
      const minuto = partes[4];
      return new Date(ano, mes, dia, hora, minuto);
    }

    function calcularAumentoTotalNoMesAtual(historico) {
      const dataAtual = new Date();
      const mesAtual = dataAtual.getMonth();
      const anoAtual = dataAtual.getFullYear();

      // Ordena o histórico pela data
      historico.sort((a, b) => {
        const dataA = converterStringParaData(a.data_modificacao);
        const dataB = converterStringParaData(b.data_modificacao);
        return dataA - dataB;
      });

      let ultimoEstoqueMesAnterior = null;
      let totalAumento = 0;
      
      // Itera sobre o histórico ordenado
      for (let i = 0; i < historico.length; i++) {
        const entry = historico[i];
        const data = converterStringParaData(entry.data_modificacao);
        const mes = data.getMonth();
        const ano = data.getFullYear();
        
        if (ano === anoAtual && mes === mesAtual) {
          if (ultimoEstoqueMesAnterior !== null) {
            const aumento = entry.quantidade_estoque - ultimoEstoqueMesAnterior;
            if (aumento > 0) {
              totalAumento += aumento;
            }
          }
          ultimoEstoqueMesAnterior = entry.quantidade_estoque;
        } else if (ano < anoAtual || (ano === anoAtual && mes < mesAtual)) {
          ultimoEstoqueMesAnterior = entry.quantidade_estoque;
        }
      }

      return totalAumento;
    }


    setNumEntradasMes(calcularAumentoTotalNoMesAtual(estoques));
  }

  return (
    <GeneralContext.Provider
      value={{
        currentPage, setCurrentPage,
        fornecedor, getFornecedor,
        fornecedores, inputFornecedor, getFornecedores, deleteFornecedor, editFornecedor,
        produto, getProduto,
        produtos, inputProduto, getProdutos, editProduto, deleteProduto,
        historico, getHistorico,
        estoque, getEstoque,
        estoques, getEstoques,
        estoqueAll, getEstoqueAll,
        numBaixasMes, getNumBaixasMes,
        numEntradasMes, getEntradasMes
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
