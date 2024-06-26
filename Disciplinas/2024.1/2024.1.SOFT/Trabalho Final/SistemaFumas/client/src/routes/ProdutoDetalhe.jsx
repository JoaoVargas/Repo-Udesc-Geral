import { Fragment, useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import Navbar from "../components/bootstrap-components/Navbar";
import JumbotronProdutoDetalhe from "../components/produtos/JumbotronProdutoDetalhe";
import { GeneralContext } from "../contexts/GeneralContext";


const ProdutoDetalhe = () => {
  const { id_produto } = useParams();
  const { produto, getProduto } = useContext(GeneralContext);

  useEffect(() => {
    getProduto( id_produto );
  }, [])


  return (
    <Fragment>
      <Navbar />
      <JumbotronProdutoDetalhe produto={ produto } />
    </Fragment>
  );
}

export default ProdutoDetalhe