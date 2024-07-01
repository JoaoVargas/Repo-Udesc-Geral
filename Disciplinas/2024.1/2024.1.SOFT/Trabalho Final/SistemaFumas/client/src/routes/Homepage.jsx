import { Fragment, useContext } from "react";

import Navbar from "../components/bootstrap-components/Navbar";
import JumbotronTotalFornecedores from "../components/homepage/JumbotronTotalProdutos";
import JumbotronTotalProdutos from "../components/homepage/JumbotronTotalProdutos";

const Homepage = () => {

  return (
    <Fragment>
      <Navbar />
      <div className="row row-cols-1 row-cols-md-2 p-0 mx-4">
        <div className="col gx-5 gy-5">
          <JumbotronTotalFornecedores />
        </div>
        <div className="col gx-5 gy-5">
          <JumbotronTotalProdutos /> 
        </div>
      </div>
    </Fragment>
  );
}

export default Homepage