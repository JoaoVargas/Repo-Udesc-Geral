import { Fragment, useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import Navbar from "../components/bootstrap-components/Navbar";
import JumbotronProdutoDetalhe from "../components/produtos/JumbotronProdutoDetalhe";
import { GeneralContext } from "../contexts/GeneralContext";
import JumbotronProdutoHistorico from "../components/produtos/JumbotronProdutoHistorico";
import JumbotronProdutoEstoque from "../components/produtos/JumbotronProdutoEstoque";
import JumbotronProdutoEstoques from "../components/produtos/JumbotronProdutoEstoques";
import JumbotronProdutoPevisao from "../components/produtos/JumbotronProdutoPevisao";
import JumbotronProdutoEntradasRestantes from "../components/produtos/JumbotronProdutoEntradasRestantes";


const ProdutoDetalhe = () => {
  const { id_produto } = useParams();
  const { produto, getProduto, getProdutos, getFornecedor, getFornecedores, getEstoque, getEstoques, getHistorico } = useContext(GeneralContext);

  useEffect(() => {
    if (id_produto == undefined  ) {
      return
    }

    getProduto( id_produto );
    getEstoque( id_produto );
    getEstoques( id_produto );
    getHistorico( id_produto );
  }, [id_produto])

  useEffect(() => {
    if (produto == undefined  ) {
      return
    }
    
    getFornecedor( produto.cnpj_fornecedor_produto );
  }, [produto])


  return (
    <Fragment>
      <Navbar />
      <div className="mx-5 mt-5">
        <JumbotronProdutoDetalhe produto={ produto } />
      </div>
      <div className="row row-cols-1 row-cols-xl-1 p-0 mx-4">
        <div className="col gx-5 gy-5 h-100">
          <JumbotronProdutoHistorico produto={ produto } /> 
        </div>
      </div>
      <div className="row row-cols-1 row-cols-xl-2 p-0 mx-4">
        <div className="col gx-5 gy-5">
          <JumbotronProdutoEstoque produto={ produto } /> 
        </div>
        <div className="col gx-5 gy-5">
          <JumbotronProdutoEstoques produto={ produto } /> 
        </div>
      </div>
      <div className="row row-cols-1 row-cols-xl-2 p-0 mx-4">
        <div className="col gx-5 gy-5">
          <JumbotronProdutoPevisao produto={ produto } /> 
        </div>
        <div className="col gx-5 gy-5">
          <JumbotronProdutoEntradasRestantes produto={ produto } /> 
        </div>
      </div>
    </Fragment>
  );
}

export default ProdutoDetalhe