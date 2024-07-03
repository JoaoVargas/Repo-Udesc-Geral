import { useContext, useEffect, useState } from "react";
import { GeneralContext } from "../../contexts/GeneralContext";
import ListProdutosRow from "../produtos/ListProdutosRow";
import ModalProdutoEdit from "../produtos/ModalProdutoEdit";
import ModalProdutoDelete from "../produtos/ModalProdutoDelete";


const JumbotronListaProdutosFornecedor = ( { produtos }) => {

  // useEffect(() => {
  //   console.log(produtos, "listPro");
  // }, [produtos])

  return (
    <div className="p-5 bg-body-tertiary rounded-3">
      <div className="container-fluid">
        <h1 className="fw-bold text-center">
          Produtos desse Fornecedor
        </h1>
        <div 
        className="border rounded-2"
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
                produtos == undefined || produtos.includes(undefined)
                ?
                  <tr>
                    <th colSpan="9" scope="row" className="text-center">
                      Não há produtos cadastrados para esse Fornecedor
                    </th>
                  </tr>
                :
                  produtos.length == 0
                  ? 
                    <tr>
                      <th colSpan="9" scope="row" className="text-center">
                        Não há produtos cadastrados para esse Fornecedor
                      </th>
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
          produtos == undefined || produtos.includes(undefined)
          ?
            ""
          :
            produtos.length == 0
            ?
              ""
            :
              produtos.map( produto => (
                <ModalProdutoEdit 
                key={produto.id_produto}
                produto={produto}/>
              ))
          }

        {
          produtos == undefined || produtos.includes(undefined)
          ?
            ""
          : 
            produtos.length == 0
            ?
              ""
            :
              produtos.map( produto => (
                <ModalProdutoDelete 
                key={produto.id_produto}
                produto={produto}/>
              ))
        }

      </div>
    </div>
  );
}

export default JumbotronListaProdutosFornecedor