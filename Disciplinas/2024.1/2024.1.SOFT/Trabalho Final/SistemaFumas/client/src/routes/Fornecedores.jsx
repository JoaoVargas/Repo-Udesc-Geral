import { Fragment } from "react";

import Navbar from "../components/bootstrap-components/Navbar";
import InputFornecedor from "../components/fornecedores-components/InputFornecedor";
import ListFornecedores from "../components/fornecedores-components/ListFornecedores";

const Fornecedores = () => {
  return (
    <Fragment>
      <Navbar />
      <ListFornecedores />
      <InputFornecedor />
    </Fragment>
  );
}

export default Fornecedores