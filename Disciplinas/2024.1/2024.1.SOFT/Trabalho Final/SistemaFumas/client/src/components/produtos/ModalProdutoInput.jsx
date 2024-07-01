import { useContext, useEffect, useState } from "react";
import toast from 'react-hot-toast';

import { GeneralContext } from "../../contexts/GeneralContext";

const ModalProdutoInput = () => {
  const [cnpjFornecedor, setCnpjFornecedor] = useState("Escolha o Fornecedor");
  const [nome, setNome] = useState("");
  const [marca, setMarca] = useState("");
  const [tipoUnidade, setTipoUnidade] = useState("Escolha a Unidade");
  const [quantidade, setQuantidade] = useState("");
  const [preco, setPreco] = useState("");
  const unidadesPossiveis = ["UN", "CX", "KG"];

  const { inputProduto, fornecedores, getFornecedores } = useContext(GeneralContext);

  const onClickSubmit = async (e) => {
    e.preventDefault();
    if (cnpjFornecedor == "Escolha o Fornecedor") {
      toast.error(`Escolha o Fornecedor.`);
      return
    }

    if (quantidade == "Escolha a Unidade") {
      toast.error(`Escolha a Unidade.`);
      return
    }

    inputProduto({ cnpjFornecedor, nome, marca, tipoUnidade, quantidade, preco });
  }

  useEffect(() => {
    getFornecedores()
  }, [])

  return (
    <div 
    className="modal fade" 
    id={"INPUT"}
    tabIndex="-1" 
    aria-labelledby={"Cadastrar produto "}
    aria-hidden="true">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h1 
            className="modal-title fs-5" >
            {/* id={"INPUT"}> */}
              Cadastrar produto
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
              <div 
              className="dropdown"
              id="cnpjFornecedor">
                <button 
                className="btn btn-secondary dropdown-toggle" 
                type="button" 
                data-bs-toggle="dropdown" 
                aria-expanded="false">
                  { cnpjFornecedor }
                </button>
                <ul className="dropdown-menu">
                  {
                    fornecedores.length == 0
                    ?
                      <li>
                        <a className="dropdown-item cursor-pointer">
                          Não há fornecedores
                        </a>
                      </li>
                    :
                      fornecedores.map(fornecedor => (
                        <li key={fornecedor.cnpj_fornecedor}>
                          <a 
                          className="dropdown-item cursor-pointer" 
                          onClick={() => setCnpjFornecedor(fornecedor.cnpj_fornecedor)}>
                            {fornecedor.cnpj_fornecedor} - {fornecedor.nome_fornecedor}
                          </a>
                        </li>
                      ))
                  }
                </ul>
              </div>
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
              <div 
              className="dropdown"
              id="tipoUnidade">
                <button 
                className="btn btn-secondary dropdown-toggle" 
                type="button" 
                data-bs-toggle="dropdown" 
                aria-expanded="false">
                  { tipoUnidade }
                </button>
                <ul className="dropdown-menu">
                {
                  unidadesPossiveis.length == 0
                  ? 
                    <li>
                      <a className="dropdown-item cursor-pointer">
                        Erro ao carregar Unidades.
                      </a>
                    </li>
                  : 
                    unidadesPossiveis.map(unidade => (
                      <li key={unidade}>
                        <a 
                        className="dropdown-item cursor-pointer" 
                        onClick={() => setTipoUnidade(unidade)}>
                          {unidade}
                        </a>
                      </li>
                    ))
                }
                </ul>
              </div>
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
              Cadastrar Produto
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
export default ModalProdutoInput