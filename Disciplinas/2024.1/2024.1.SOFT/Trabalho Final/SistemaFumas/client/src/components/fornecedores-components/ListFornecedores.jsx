import { Fragment, useContext, useEffect, useState } from "react";

import { GeneralContext } from "../../contexts/GeneralContext";

const ListFornecedores = () => {

  const { fornecedores, getFornecedores, deleteFornecedor } = useContext(GeneralContext);

  const handleDeleteClick = (id) => {
    deleteFornecedor(id);
  }

  useEffect(() => {
    getFornecedores();
  }, [])

  return (
    <Fragment>
      <h1 className="text-center m-5">
        Fornecedores
      </h1>
      <div className="m-5 border border rounded-2 overflow-hidden">
        <table className="my-0 table table-striped table-hover table-borderless">
          <thead >
            <tr>
              <th scope="col">ID</th>
              <th scope="col">CNPJ</th>
              <th scope="col">Nome Fantasia</th>
              <th scope="col">CEP</th>
              <th scope="col">Número</th>
            </tr>
          </thead>
          <tbody>
            { 
            fornecedores.length == 0 
              ? 
                <tr>
                  <th colSpan="5" scope="row" className="text-center">Não há fornecedores cadastrados</th>
                </tr>
              : 
                fornecedores.map(fornecedor => (
                  <tr key={fornecedor.id_fornecedor}>
                    <th scope="row"> {fornecedor.id_fornecedor} </th>
                    <td>{ fornecedor.cnpj_fornecedor }</td>
                    <td>{ fornecedor.nome_fornecedor }</td>
                    <td>{ fornecedor.cep_fornecedor }</td>
                    <td>{ fornecedor.numero_fornecedor }</td>
                    <td> 
                      <i 
                      className="bi bi-pencil-square cursor-pointer"
                      onClick={() => {console.log("pes")}}></i> 
                    </td>
                    <td>
                      <i 
                      className="bi bi-trash3 text-danger cursor-pointer"
                      data-bs-toggle="modal" 
                      data-bs-target={"#" + fornecedor.id_fornecedor}>
                      </i>   
                    </td>
                  </tr>
                ))
            }
          </tbody>
        </table>
      </div>
      {
        fornecedores.length == 0 
        ?
          <div></div>
        :
          fornecedores.map( fornecedor => (
            <div 
            className="modal fade" 
            id={fornecedor.id_fornecedor}
            key={fornecedor.id_fornecedor}
            tabIndex="-1" 
            aria-labelledby={"Fornecedor"+ fornecedor.id_fornecedor + "Lable"}
            aria-hidden="true">
              <div className="modal-dialog">
                <div className="modal-content">
                  <div className="modal-header">
                    <h1 
                    className="modal-title fs-5" 
                    id={"Fornecedor"+ fornecedor.id_fornecedor + "Lable"}>
                      Deletar {fornecedor.nome_fornecedor} 
                    </h1>
                    <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div className="modal-body">
                    Ao deletar o fornecedor {fornecedor.nome_fornecedor} todos os seus produtos tambem serão deletados.
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
                    onClick={() => {handleDeleteClick(fornecedor.id_fornecedor)}}>
                      Deletar Fornecedor
                    </button>
                  </div>
                </div>
              </div>
            </div>
          ))
      }
    </Fragment>
  );
}

export default ListFornecedores