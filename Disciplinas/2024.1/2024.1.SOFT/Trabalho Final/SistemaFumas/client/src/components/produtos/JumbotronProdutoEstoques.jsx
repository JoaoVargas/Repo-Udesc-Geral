import { useContext, useEffect, useState } from "react";

import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';

import { GeneralContext } from "../../contexts/GeneralContext";

const CustomTooltip = ({ active, payload, label }) => {
  if (active && payload && payload.length) {
    return (
      <div className="custom-tooltip bg-body-secondary rounded-3 p-2 m-0">
        <p className="label fw-bold">{label}</p>
        <p className="intro">{payload[0].value}</p>
      </div>
    );
  }

  return null;
};


const JumbotronProdutoEstoques = ( { produto }) => {
  const { estoques, getEstoques } = useContext(GeneralContext);

  useEffect(() => {
    if (produto == undefined) {
      return
    } 
    getEstoques( produto.id_produto );
    // console.log(estoques);
  }, [produto]);

  return (
    <div className="p-5 bg-body-tertiary rounded-3">
      <div className="container-fluid">
        <h1 className="fw-bold text-center">
          Historico de Estoque 
        </h1>
        <div>
          <ResponsiveContainer width="100%" height={300}>
            <LineChart 
            data={estoques}
            margin={{ top: 20, right: 20, bottom: 20, left: 20 }}>
              <XAxis dataKey="data_modificacao"/>
              <YAxis dataKey="quantidade_estoque"/>
              <CartesianGrid stroke="#ADB1B5" strokeDasharray="5 5"/>
              <Line 
              type="monotone" 
              dataKey="quantidade_estoque" 
              stroke="#0E6DFB" />
              <Tooltip content={<CustomTooltip />}/>
            </LineChart>
          </ResponsiveContainer>
          
        </div>
      </div>
    </div>
  );
}

export default JumbotronProdutoEstoques