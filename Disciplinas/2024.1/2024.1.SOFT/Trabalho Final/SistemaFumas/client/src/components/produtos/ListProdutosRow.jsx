import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { GeneralContext } from "../../contexts/GeneralContext";

const ListProdutosRow = ( { produto } ) => {
  const navigate = useNavigate();
  const { setCurrentPage } = useContext(GeneralContext);

  return (
    <tr>
      <th scope="row">
        <a 
        className="link cursor-pointer"
        onClick={() => {
          setCurrentPage(`none`);
          navigate(`/produtos/${ produto.id_produto }`);
        }}>
          { produto.id_produto }
        </a>
      </th>
      <td>{ produto.cnpj_fornecedor_produto }</td>
      <td>{ produto.nome_produto }</td>
      <td>{ produto.marca_produto }</td>
      <td>{ produto.tipo_unidade_produto }</td>
      <td>{ produto.quantidade_unidades_produto }</td>
      <td>{ produto.preco_produto }</td>
      <td> 
        <i 
        className="bi bi-pencil-square cursor-pointer"
        data-bs-toggle="modal" 
        data-bs-target={"#" + produto.id_produto + "EDIT"}>
        </i> 
      </td>
      <td>
        <i 
        className="bi bi-trash3 text-danger cursor-pointer"
        data-bs-toggle="modal" 
        data-bs-target={"#" + produto.id_produto + "DELETE"}>
        </i>   
      </td>
    </tr>
  );
}

export default ListProdutosRow