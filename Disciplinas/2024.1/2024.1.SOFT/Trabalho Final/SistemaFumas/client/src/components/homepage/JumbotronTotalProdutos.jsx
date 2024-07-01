import { useContext, useEffect } from "react";
import { GeneralContext } from "../../contexts/GeneralContext";


const JumbotronTotalProdutos = () => {
  const { produtos, getProdutos } = useContext(GeneralContext);

  useEffect(() => {
    getProdutos();
  }, [])

  return (
    <div className="p-5 bg-body-tertiary rounded-3">
      <div className="container-fluid">
        <h1 className="fw-bold text-center">
          Quantidade de Produtos
        </h1>
        <h1 className="text-center">
          { produtos.length }
        </h1>
      </div>
    </div>
  );
}

export default JumbotronTotalProdutos