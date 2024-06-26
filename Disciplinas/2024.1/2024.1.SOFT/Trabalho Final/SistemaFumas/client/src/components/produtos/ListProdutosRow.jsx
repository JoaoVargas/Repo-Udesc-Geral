const ListProdutosRow = ( { produto } ) => {

  return (
    <tr>
      <th scope="row">{ produto.id_produto }</th>
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