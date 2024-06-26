import { Fragment, useContext } from "react";
import { Toaster } from 'react-hot-toast';

import Navbar from "../components/bootstrap-components/Navbar";
import ListProdutos from "../components/produtos/ListProdutos";

const Produtos = () => {
  return (
    <Fragment>
      <Navbar />
      <ListProdutos />
    </Fragment>
  );
}

export default Produtos
