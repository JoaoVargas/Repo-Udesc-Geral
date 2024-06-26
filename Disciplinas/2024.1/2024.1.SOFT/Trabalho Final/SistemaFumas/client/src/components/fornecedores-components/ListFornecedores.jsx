import { Fragment, useContext, useEffect } from "react";

import { GeneralContext } from "../../contexts/GeneralContext";
import EditFornecedor from "./EditFornecedor";

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
      <h1 className="text-center mx-5 mt-4">
        Fornecedores
      </h1>

      <button 
      type="button" 
      className="btn btn-primary mx-5 mt-4"
      data-bs-toggle="modal" 
      data-bs-target={"#inputFornecedor"}>
        Cadastrar Fornecedor
      </button>

      <div 
      className="mx-5 mt-2 border rounded-2"
      style={{ maxHeight: '500px', overflowY: 'auto' }}>
        <table className="my-0 table table-striped table-hover table-borderless table-responsive">
          <thead >
            <tr>
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
                  <tr key={fornecedor.cnpj_fornecedor}>
                    <th scope="row">{ fornecedor.cnpj_fornecedor }</th>
                    <td>{ fornecedor.nome_fornecedor }</td>
                    <td>{ fornecedor.cep_fornecedor }</td>
                    <td>{ fornecedor.numero_fornecedor }</td>
                    <td> 
                      <i 
                      className="bi bi-pencil-square cursor-pointer"
                      data-bs-toggle="modal" 
                      data-bs-target={"#" + fornecedor.cnpj_fornecedor + "edit"}>
                      </i> 
                    </td>
                    <td>
                      <i 
                      className="bi bi-trash3 text-danger cursor-pointer"
                      data-bs-toggle="modal" 
                      data-bs-target={"#" + fornecedor.cnpj_fornecedor}>
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
            <EditFornecedor 
            key={fornecedor.cnpj_fornecedor + "edit"}
            fornecedor={fornecedor}/>
          ))
      }
      {
        fornecedores.length == 0 
        ?
          <div></div>
        :
          fornecedores.map( fornecedor => (
            <div 
            className="modal fade" 
            id={fornecedor.cnpj_fornecedor}
            key={fornecedor.cnpj_fornecedor}
            tabIndex="-1" 
            aria-labelledby={"Fornecedor"+ fornecedor.cnpj_fornecedor + "Lable"}
            aria-hidden="true">
              <div className="modal-dialog">
                <div className="modal-content">
                  <div className="modal-header">
                    <h1 
                    className="modal-title fs-5" 
                    id={"Fornecedor"+ fornecedor.cnpj_fornecedor + "Lable"}>
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
                    onClick={() => {handleDeleteClick(fornecedor.cnpj_fornecedor)}}>
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