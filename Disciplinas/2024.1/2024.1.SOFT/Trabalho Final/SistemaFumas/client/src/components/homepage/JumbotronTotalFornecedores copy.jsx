import { useContext, useEffect } from "react";
import { GeneralContext } from "../../contexts/GeneralContext";


const JumbotronTotalFornecedores = () => {
  const { fornecedores, getFornecedores } = useContext(GeneralContext);

  useEffect(() => {
    getFornecedores();
  }, [])

  return (
    <div className="p-5 bg-body-tertiary rounded-3">
      <div className="container-fluid">
        <h1 className="fw-bold text-center">
          Quantidade de Fornecedores
        </h1>
        <h1 className="text-center">
          { fornecedores.length }
        </h1>
      </div>
    </div>
  );
}

export default JumbotronTotalFornecedores