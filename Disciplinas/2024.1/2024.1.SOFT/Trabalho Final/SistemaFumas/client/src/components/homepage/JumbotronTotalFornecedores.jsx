import { useContext, useEffect } from "react";
import { GeneralContext } from "../../contexts/GeneralContext";
import { useNavigate } from "react-router-dom";


const JumbotronTotalFornecedores = () => {
  const { setCurrentPage, fornecedores, getFornecedores } = useContext(GeneralContext);
  const navigate = useNavigate();

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
          <a 
          className="link cursor-pointer"
          onClick={() => {
            setCurrentPage(`/fornecedores/`);
            navigate(`/fornecedores/`);
          }}>
            { fornecedores.length }
          </a>
        </h1>
      </div>
    </div>
  );
}

export default JumbotronTotalFornecedores