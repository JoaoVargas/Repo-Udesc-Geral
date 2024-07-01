const JumbotronFornecedorDetalhe = ( { fornecedor }) => {

  return (
    <div className="p-5 bg-body-tertiary rounded-3">
      <div className="container-fluid">
        <h1 className="display-5 fw-bold text-center">
          Fornecedor
        </h1>
        <div className="d-flex flex-column flex-wrap justify-content-between">
          <div className="d-flex flex-row flex-wrap justify-content-between">
            <p>
              <b>CNPJ do Fornecedor: </b> {fornecedor.cnpj_fornecedor}
            </p>
            <p>
              <b>Nome do Fornecedor:</b> {fornecedor.nome_fornecedor}
            </p>
          </div>
          <div className="d-flex flex-row flex-wrap justify-content-between text-center">
            <p>
              <b>CEP:</b> {fornecedor.cep_fornecedor}
            </p>
            <p>
              <b>Número:</b> {fornecedor.numero_fornecedor}
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default JumbotronFornecedorDetalhe