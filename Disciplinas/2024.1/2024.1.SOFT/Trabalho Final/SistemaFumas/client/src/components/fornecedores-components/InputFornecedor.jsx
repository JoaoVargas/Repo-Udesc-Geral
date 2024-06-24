import React, { Fragment, useState } from "react";

const InputFornecedor = () => {
  const [cnpj, setCnpj] = useState("");
  const [nome, setNome] = useState("");
  const [cep, setCep] = useState("");
  const [numero, setNumero] = useState("");

  const onSubmitForm = async (e) => {
    e.preventDefault();
    
    try {
      const body = { cnpj, nome, cep, numero };
      const response = await fetch("http://localhost:3000/fornecedores", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body)
      })

      console.log(response);
    } catch (error) {
      console.error(error.message);
    }
  }


  return (
    <Fragment>
      <h1 className="text-center mt-5">
        Adicionar Fornecedor
      </h1>
      <form onSubmit={ onSubmitForm }>
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
        <button 
        type="submit" 
        className="btn btn-primary">
          Submit
        </button>
      </form>
    </Fragment>
  );
}

export default InputFornecedor