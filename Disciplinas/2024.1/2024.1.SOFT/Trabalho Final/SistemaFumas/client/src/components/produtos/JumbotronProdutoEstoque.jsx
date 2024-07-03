import { useContext, useEffect } from "react";

import { GeneralContext } from "../../contexts/GeneralContext";

const JumbotronProdutoEstoque = ( { produto }) => {
  const { estoque, getEstoque } = useContext(GeneralContext);

  useEffect(() => {
    if (produto == undefined) {
      return
    } 
    getEstoque( produto.id_produto );
  }, [produto]);

  return (
    <div className="p-5 bg-body-tertiary rounded-3">
      <div className="container-fluid">
        <h1 className="fw-bold text-center">
          Estoque 
        </h1>
        <h1 className="text-center">
          {estoque.estoque}
        </h1>
      </div>
    </div>
  );
}

export default JumbotronProdutoEstoque