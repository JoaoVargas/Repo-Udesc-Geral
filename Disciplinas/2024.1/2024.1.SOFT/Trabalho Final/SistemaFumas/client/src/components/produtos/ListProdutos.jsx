import { Fragment, useContext, useEffect } from "react";

import { GeneralContext } from "../../contexts/GeneralContext";
import ListProdutosRow from "./ListProdutosRow";
import ModalProdutoEdit from "./ModalProdutoEdit";
import ModalProdutoInput from "./ModalProdutoInput";
import ModalProdutoDelete from "./ModalProdutoDelete";

const ListProdutos = () => {
  const { produtos, getProdutos } = useContext(GeneralContext); 

  useEffect(() => {
    getProdutos();
  }, [])

  return (
    <Fragment >
      <h1 className="text-center mx-5 mt-4">
        Produtos
      </h1>

      <button 
      type="button" 
      className="btn btn-primary mx-5 mt-4"
      data-bs-toggle="modal" 
      data-bs-target={"#INPUT"}>
        Cadastrar Produto
      </button>

      <ModalProdutoInput />

      <div 
      className="mx-5 mt-2 border rounded-2"
      style={{ maxHeight: '500px', overflowY: 'auto' }}>
        <table className="my-0 table table-striped table-hover table-borderless table-responsive">
          <thead >
            <tr>
              <th scope="col">ID Produto</th> 
              <th scope="col">CNPJ Fornecedor</th> 
              <th scope="col">Nome</th> 
              <th scope="col">Marca</th> 
              <th scope="col">Tipo Unidade</th>
              <th scope="col">Quantidade</th>
              <th scope="col">Preço</th>
              <th scope="col"></th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            { 
            produtos.length == 0 
              ? 
                <tr>
                  <th colSpan="9" scope="row" className="text-center">Não há produtos cadastrados</th>
                </tr>
              : 
                produtos.map(produto => (
                  <ListProdutosRow 
                  produto={produto} 
                  key={produto.id_produto}/>
                ))
            }
          </tbody>
        </table>
      </div>

      {
        produtos.length == 0 
        ?
          <div></div>
        :
          produtos.map( produto => (
            <ModalProdutoEdit 
            key={produto.id_produto}
            produto={produto}/>
          ))
      }

      {
        produtos.length == 0 
        ?
          <div></div>
        :
          produtos.map( produto => (
            <ModalProdutoDelete 
            key={produto.id_produto}
            produto={produto}/>
          ))
      }

    </Fragment>
  );
}

export default ListProdutos