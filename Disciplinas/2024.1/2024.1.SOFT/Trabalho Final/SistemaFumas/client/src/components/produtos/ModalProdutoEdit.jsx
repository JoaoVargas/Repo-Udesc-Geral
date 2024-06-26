import { useContext, useState } from "react";

import { GeneralContext } from "../../contexts/GeneralContext";


const ModalProdutoEdit = ( { produto } ) => {
  const [id, setId] = useState(produto.id_produto);
  const [cnpjFornecedor, setCnpjFornecedor] = useState(produto.cnpj_fornecedor_produto);
  const [nome, setNome] = useState(produto.nome_produto);
  const [marca, setMarca] = useState(produto.marca_produto);
  const [tipoUnidade, setTipoUnidade] = useState(produto.tipo_unidade_produto);
  const [quantidade, setQuantidade] = useState(produto.quantidade_unidades_produto);
  const [preco, setPreco] = useState(produto.preco_produto);

  const { editProduto } = useContext(GeneralContext);


  const onClickSubmit = async (e) => {
    e.preventDefault();
    editProduto({ id, cnpjFornecedor, nome, marca, tipoUnidade, quantidade, preco });
  }


  return (
    <div 
    className="modal fade" 
    id={produto.id_produto + "EDIT"}
    tabIndex="-1" 
    aria-labelledby={"Editar produto "+ produto.id_produto}
    aria-hidden="true">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h1 
            className="modal-title fs-5" 
            id={"Editar produto "+ produto.id_produto}>
              Editar Produto {produto.id_produto} 
            </h1>

            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close">
            </button>
          </div>
          <div className="modal-body">
            <div className="mb-3">
              <label 
              htmlFor="cnpjFornecedor" 
              className="form-label">
                CNPJ do Fornecedor
              </label>
              <input 
              type="text" 
              className="form-control" 
              id="cnpjFornecedor" 
              value={ cnpjFornecedor }
              onChange={ e => setCnpjFornecedor(e.target.value) }
              />
            </div>

            <div className="mb-3">
              <label 
              htmlFor="nome" 
              className="form-label">
                Nome do Produto
              </label>
              <input 
              type="text" 
              className="form-control" 
              id="nome" 
              value={ nome }
              onChange={ e => setNome(e.target.value) }
              />
            </div>

            <div className="mb-3">
              <label 
              htmlFor="marca" 
              className="form-label">
                Marca do Produto
              </label>
              <input 
              type="text" 
              className="form-control" 
              id="marca" 
              value={ marca }
              onChange={ e => setMarca(e.target.value) }
              />
            </div>

            <div className="mb-3">
              <label 
              htmlFor="tipoUnidade" 
              className="form-label">
                Tipo de Unidade
              </label>
              <input 
              type="text" 
              className="form-control" 
              id="tipoUnidade" 
              value={ tipoUnidade }
              onChange={ e => setTipoUnidade(e.target.value) }
              />
            </div>

            <div className="mb-3">
              <label 
              htmlFor="quantidade" 
              className="form-label">
                Quantidade
              </label>
              <input 
              type="text" 
              className="form-control" 
              id="quantidade" 
              value={ quantidade }
              onChange={ e => setQuantidade(e.target.value) }
              />
            </div>

            <div className="mb-3">
              <label 
              htmlFor="preco" 
              className="form-label">
                Preço
              </label>
              <input 
              type="text" 
              className="form-control" 
              id="preco" 
              value={ preco }
              onChange={ e => setPreco(e.target.value) }
              />
            </div>
          </div>
          <div className="modal-footer">
            <button 
            type="button" 
            className="btn btn-secondary" 
            data-bs-dismiss="modal">
              Voltar
            </button>
            <button 
            type="button"   
            className="btn btn-primary"
            data-bs-dismiss="modal"
            onClick={(e) => onClickSubmit(e)}>
              Editar Produto
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ModalProdutoEdit