import { useContext, useState } from "react";

import { GeneralContext } from "../../contexts/GeneralContext";


const EditFornecedor = ({ fornecedor }) => {
  const [cnpj, setCnpj] = useState(fornecedor.cnpj_fornecedor);
  const [nome, setNome] = useState(fornecedor.nome_fornecedor);
  const [cep, setCep] = useState(fornecedor.cep_fornecedor);
  const [numero, setNumero] = useState(fornecedor.numero_fornecedor);

  const { editFornecedor } = useContext(GeneralContext);


  const onClickSubmit = async (e) => {
    e.preventDefault();
    const oldCnpj = fornecedor.cnpj_fornecedor;
    editFornecedor({ oldCnpj, cnpj, nome, cep, numero });
  }


  return (
    <div 
    className="modal fade" 
    id={fornecedor.cnpj_fornecedor + "edit"}
    key={fornecedor.cnpj_fornecedor + "edit"}
    tabIndex="-1" 
    aria-labelledby={"Fornecedor"+ fornecedor.cnpj_fornecedor + "Lable"}
    aria-hidden="true">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h1 
            className="modal-title fs-5" 
            id={"Fornecedor"+ fornecedor.cnpj_fornecedor + "edit" + "Lable"}>
              Editar {fornecedor.nome_fornecedor} 
            </h1>
            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <form onSubmit={ () => {onSubmitForm} }>
            <div className="modal-body">
              <div className="mb-3">
                <label 
                htmlFor="cnpj_fornecedor" 
                className="form-label">
                  CNPJ
                </label>
                <input 
                type="text" 
                className="form-control" 
                id="cnpj_fornecedor" 
                value={ cnpj }
                onChange={ e => setCnpj(e.target.value) }
                />
              </div>
              <div className="mb-3">
                <label 
                htmlFor="nome_fornecedor" 
                className="form-label">
                  Nome Fantasia
                </label>
                <input 
                type="text" 
                className="form-control" 
                id="nome_fornecedor" 
                value={ nome }
                onChange={ e => setNome(e.target.value) }
                />
              </div>
              <div className="mb-3">
                <label 
                htmlFor="cep_fornecedor" 
                className="form-label">
                  CEP
                </label>
                <input 
                type="text" 
                className="form-control" 
                id="cep_fornecedor" 
                value={ cep }
                onChange={ e => setCep(e.target.value) }
                />
              </div>
              <div className="mb-3">
                <label 
                htmlFor="numero_fornecedor" 
                className="form-label">
                  Número
                </label>
                <input 
                type="text" 
                className="form-control" 
                id="numero_fornecedor" 
                value={ numero }
                onChange={ e => setNumero(e.target.value) }
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
                Editar Fornecedor
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default EditFornecedor