import { useContext } from "react";
import { GeneralContext } from "../../contexts/GeneralContext";


const ModalProdutoDelete = ( { produto } ) => {
  const { deleteProduto } = useContext(GeneralContext);

  return (
    <div 
    className="modal fade" 
    id={produto.id_produto + "DELETE"}
    tabIndex="-1" 
    aria-labelledby={"Produto "+ produto.id_produto + " DELETE"}
    aria-hidden="true">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h1 
            className="modal-title fs-5" >
              Deletar Produto {produto.id_produto} 
            </h1>
            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div className="modal-body">
            Ao deletar o produto {produto.id_produto} todo o seu histórico tambem será deletado.
            <br />
            Você tem certeza que quer fazer isso?
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
            className="btn btn-danger"
            data-bs-dismiss="modal"
            onClick={() => {deleteProduto(produto.id_produto)}}>
              Deletar Produto
            </button>
          </div>
        </div>
      </div>
    </div>
          
  );
}

export default ModalProdutoDelete