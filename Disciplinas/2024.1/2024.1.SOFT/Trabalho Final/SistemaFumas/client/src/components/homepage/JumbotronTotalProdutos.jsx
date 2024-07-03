import { useContext, useEffect } from "react";
import { GeneralContext } from "../../contexts/GeneralContext";
import { useNavigate } from "react-router-dom";


const JumbotronTotalProdutos = () => {
  const { setCurrentPage, produtos, getProdutos } = useContext(GeneralContext);
  const navigate = useNavigate();

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
          <a 
        className="link cursor-pointer"
        onClick={() => {
          setCurrentPage(`/produtos/`);
          navigate(`/produtos/`);
        }}>
          { produtos.length }
        </a>
        </h1>
      </div>
    </div>
  );
}

export default JumbotronTotalProdutos