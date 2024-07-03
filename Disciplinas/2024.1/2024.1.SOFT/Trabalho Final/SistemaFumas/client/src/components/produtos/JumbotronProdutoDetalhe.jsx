import { useContext, useEffect } from "react";
import { GeneralContext } from "../../contexts/GeneralContext";
import { useNavigate } from "react-router-dom";


const JumbotronProdutoDetalhe = ( { produto }) => {
  const navigate = useNavigate();
  const { fornecedor, getFornecedor, setCurrentPage } = useContext(GeneralContext);

  useEffect(() => {
    if (produto == undefined) {
      return
    } 
    getFornecedor( produto.cnpj_fornecedor_produto );
  }, [produto])

  return (
    <div className="p-5 bg-body-tertiary rounded-3">
      <div className="container-fluid">
        <h1 className="display-5 fw-bold text-center">
          Produto {produto.id_produto}
        </h1>
        <div className="d-flex flex-column flex-wrap justify-content-between">
          <div className="d-flex flex-row flex-wrap justify-content-between">
            <p>
              <b>CNPJ do Fornecedor: </b> 
              <a 
              onClick={() => {
                setCurrentPage(`none`);
                navigate(`/fornecedores/${fornecedor.cnpj_fornecedor}`);
              }}
              className="link cursor-pointer">
                {fornecedor.cnpj_fornecedor}
              </a>
            </p>
            <p>
              <b>Nome do Fornecedor:</b> {fornecedor.nome_fornecedor}
            </p>
          </div>
          <div className="d-flex flex-row flex-wrap justify-content-between text-center">
            <p>
              <b>Nome do Produto:</b> {produto.nome_produto}
            </p>
            <p>
              <b>Marca do Produto:</b> {produto.marca_produto}
            </p>
            <p>
              <b>Tipo de Unidade:</b> {produto.tipo_unidade_produto}
            </p>
            <p>
              <b>Quantidade:</b> {produto.quantidade_unidades_produto}
            </p>
            <p>
              <b>Preço Atual:</b> {produto.preco_produto} R$
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default JumbotronProdutoDetalhe