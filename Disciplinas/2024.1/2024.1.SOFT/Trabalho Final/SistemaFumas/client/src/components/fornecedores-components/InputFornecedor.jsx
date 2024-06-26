import React, { Fragment, useContext, useState } from "react";
import { GeneralContext } from "../../contexts/GeneralContext";

const InputFornecedor = () => {
  const { inputFornecedor } = useContext(GeneralContext);

  const [cnpj, setCnpj] = useState("");
  const [nome, setNome] = useState("");
  const [cep, setCep] = useState("");
  const [numero, setNumero] = useState("");

  const onClickSubmit = async (e) => {
    e.preventDefault();
    inputFornecedor({ cnpj, nome, cep, numero });
    setCnpj("");
    setNome("");
    setCep("");
    setNumero("");
  }


  return (
      <div 
      className="modal fade" 
      id={"inputFornecedor"}
      key={"inputFornecedor"}
      tabIndex="-1" 
      aria-labelledby={"Input Fornecedor"}
      aria-hidden="true">
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 
              className="modal-title fs-5" 
              id={"inputFornecedor"}>
                Cadastrar Fornecedor
              </h1>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form>
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
                  Cadastrar Fornecedor
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
  );
}

export default InputFornecedor