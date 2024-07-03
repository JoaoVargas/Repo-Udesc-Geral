import { useContext, useEffect, useState } from "react";

import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';

import { GeneralContext } from "../../contexts/GeneralContext";

const JumbotronProdutoPevisao = ( { produto }) => {
  const { estoques, getEstoques, numBaixasMes, getNumBaixasMes } = useContext(GeneralContext);

  useEffect(() => {
    if (produto == undefined) {
      return
    } 
    
    getEstoques( produto.id_produto );
  }, [produto]);

  useEffect(() => {
    getNumBaixasMes();
  }, [estoques]);

  return (
    <div className="p-5 bg-body-tertiary rounded-3">
      <div className="container-fluid">
        <h1 className="fw-bold text-center">
          Previsão de Baixas Nesse Mês 
        </h1>
        <h1 className="text-center">
          {
            produto == undefined || estoques == undefined || estoques.lenght == 0
            ?
              ""
            :
              numBaixasMes
          }
          
          
        </h1>
      </div>
    </div>
  );
}

export default JumbotronProdutoPevisao