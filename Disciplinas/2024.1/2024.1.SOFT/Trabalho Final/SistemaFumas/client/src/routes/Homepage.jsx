import { Fragment, useContext } from "react";

import Navbar from "../components/bootstrap-components/Navbar";
import JumbotronTotalFornecedores from "../components/homepage/JumbotronTotalFornecedores";
import JumbotronTotalProdutos from "../components/homepage/JumbotronTotalProdutos";
import JumbotronTotalEstoque from "../components/homepage/JumbotronTotalEstoque";

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
      <div className="row row-cols-1 row-cols-md-2 p-0 mx-4">
        <div className="col gx-5 gy-5">
          <JumbotronTotalEstoque />
        </div>
        <div className="col gx-5 gy-5">
          {/* <JumbotronTotalProdutos />  */}
        </div>
      </div>
    </Fragment>
  );
}

export default Homepage