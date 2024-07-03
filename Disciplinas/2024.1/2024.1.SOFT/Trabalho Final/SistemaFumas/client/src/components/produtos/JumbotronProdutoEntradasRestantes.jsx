import { useContext, useEffect, useState } from "react";


import { GeneralContext } from "../../contexts/GeneralContext";

const JumbotronProdutoEntradasRestantes = ( { produto }) => {
  const { estoques, getEstoques, numEntradasMes, getEntradasMes } = useContext(GeneralContext);

  useEffect(() => {
    if (produto == undefined) {
      return
    } 


    getEstoques( produto.id_produto );
  }, [produto]);

  useEffect(() => {
    getEntradasMes();
  }, [estoques]);

  return (
    <div className="p-5 bg-body-tertiary rounded-3">
      <div className="container-fluid">
        <h1 className="fw-bold text-center">
          Previsão de Entradas Restantes Nesse Mês 
        </h1>
        <h1 className="text-center">
          {
            produto == undefined || estoques == undefined || estoques.lenght == 0
            ?
              ""
            :
              numEntradasMes
          }
        </h1>
      </div>
    </div>
  );
}

export default JumbotronProdutoEntradasRestantes