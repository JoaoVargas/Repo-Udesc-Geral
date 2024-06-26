import { useEffect } from "react";


const JumbotronProdutoDetalhe = ( { produto }) => {

  useEffect(() => {
    console.log(produto);
  }, [])

  return (
    <div>
      { produto.id_produto } 
      { produto.cnpj_fornecedor_produto } 
    </div>
  );
}

export default JumbotronProdutoDetalhe