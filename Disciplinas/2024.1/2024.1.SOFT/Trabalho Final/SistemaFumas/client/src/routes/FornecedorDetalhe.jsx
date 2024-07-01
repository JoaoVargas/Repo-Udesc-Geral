import { Fragment, useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import Navbar from "../components/bootstrap-components/Navbar";
import { GeneralContext } from "../contexts/GeneralContext";
import JumbotronFornecedorDetalhe from "../components/fornecedores-components/JumbotronFornecedorDetalhe";


const FornecedorDetalhe = () => {
  const { cnpj_fornecedor } = useParams();
  const { fornecedor, getFornecedor, getFornecedores, getProdutos } = useContext(GeneralContext);

  useEffect(() => {
    getProdutos();
    getFornecedores();
    getFornecedor( cnpj_fornecedor );
  }, [])


  return (
    <Fragment>
      <Navbar />
      <div className="mx-5 mt-5">
        <JumbotronFornecedorDetalhe fornecedor={ fornecedor } />
      </div>
      <div className="row row-cols-1 row-cols-xl-2 p-0 mx-4">
        <div className="col gx-5 gy-5">
          {/* <JumbotronProdutoHistorico produto={ produto } />  */}
        </div>
        <div className="col gx-5 gy-5">
          {/* <JumbotronProdutoHistorico produto={ produto } />  */}
        </div>
      </div>
    </Fragment>
  );
}

export default FornecedorDetalhe