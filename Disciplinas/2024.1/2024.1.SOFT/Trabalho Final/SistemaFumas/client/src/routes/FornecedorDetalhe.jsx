import { Fragment, useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import Navbar from "../components/bootstrap-components/Navbar";
import { GeneralContext } from "../contexts/GeneralContext";
import JumbotronFornecedorDetalhe from "../components/fornecedores-components/JumbotronFornecedorDetalhe";
import JumbotronListaProdutosFornecedor from "../components/fornecedores-components/JumbotronListaProdutosFornecedor";


const FornecedorDetalhe = () => {
  const { cnpj_fornecedor } = useParams();
  const { fornecedor, getFornecedor, getFornecedores, produtos, getProdutos } = useContext(GeneralContext);

  const [ produtosFunc, setProdutosFunc ] = useState([]);

  useEffect(() => {
    getFornecedores();
    getProdutos();
    getFornecedor( cnpj_fornecedor );
  }, [])


  useEffect(() => {
    if (produtos == undefined || produtos.includes(undefined)) {
      return
    } 
    
    let produtosAux = [];

    produtos.map(produto => {
    if (produto.cnpj_fornecedor_produto == fornecedor.cnpj_fornecedor) {
      produtosAux.push(produto);
    }})

    setProdutosFunc(produtosAux);
  }, [produtos])


  return (
    <Fragment>
      <Navbar />
      <div className="mx-5 mt-5">
        <JumbotronFornecedorDetalhe fornecedor={ fornecedor } />
      </div>
      <div className="row row-cols-1 row-cols-xl-2 p-0 mx-4">
        <div className="col gx-5 gy-5">
          <JumbotronListaProdutosFornecedor produtos={ produtosFunc } /> 
        </div>
        <div className="col gx-5 gy-5">
          {/* <JumbotronProdutoHistorico produto={ produto } />  */}
        </div>
      </div>
    </Fragment>
  );
}

export default FornecedorDetalhe