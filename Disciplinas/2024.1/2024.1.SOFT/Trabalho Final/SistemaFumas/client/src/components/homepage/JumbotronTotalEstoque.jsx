import { useContext, useEffect, useState } from "react";
import { GeneralContext } from "../../contexts/GeneralContext";
import { useNavigate } from "react-router-dom";
import { Cell, Label, Legend, Pie, PieChart, ResponsiveContainer } from "recharts";


const JumbotronTotalEstoque = () => {
  const { setCurrentPage, estoqueAll, getEstoqueAll } = useContext(GeneralContext);
  const [ totalEstoque, setTotalEstoque ] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    getEstoqueAll();
  }, [])

  useEffect(() => {
    let total = 0;

    if (estoqueAll == undefined || estoqueAll[0] == undefined || estoqueAll.length == 0) {
      return
    }

    estoqueAll.map((item) => {
      total += item.estoque;
    })

    setTotalEstoque(total);
  }, [estoqueAll])

  const CustomLabel = ({ viewBox, value }) => {
    const { cx, cy } = viewBox;
    return (
      <g>
        <text
          x={cx}
          y={cy}
          className="recharts-text recharts-label"
          textAnchor="middle"
          dominantBaseline="central"
          alignmentBaseline="middle"
          fill="#DDE2E6"
          fontSize="50"
          fontWeight="500"
        >
          {value}
        </text>
      </g>
    );
  };

  const CustomizedLegend = (props) => {
    const { payload } = props;
    return (
      <div className="list-group list-group-flush list-group-horizontal-md">
        {payload.map((entry, index) => (
          <a 
          className="list-group-item list-group-item-action cursor-pointer text-start py-0 fs-4"
          key={index}
          onClick={() => {
            setCurrentPage(`none`);
            navigate(`/produtos/${entry.payload.id_produto_estoque}`);
          }}>
            <i 
            className="bi bi-record-circle-fill"
            style={{"color":entry.payload.fill}}></i>
            {entry.payload.estoque}
          </a>
        ))}
      </div>
    );
  };

  return (
    <div className="p-5 bg-body-tertiary rounded-3">
      <div className="container-fluid">
        <h1 className="fw-bold text-center">
          Estoque Total
        </h1>
        <h1 className="text-center">
          {
            estoqueAll == undefined || estoqueAll[0] == undefined || estoqueAll.length == 0
            ?
              "Não há estoque"
            :
              <ResponsiveContainer width="100%" height={300}>
                <PieChart >
                  <Pie
                  data={estoqueAll}
                  dataKey="estoque"
                  innerRadius="80%"
                  outerRadius="100%">
                    {
                      estoqueAll.map((item, index) => (
                        <Cell
                        key={index} 
                        fill={'#' + Math.floor(Math.random()*16777215).toString(16)}/>
                      ))
                    }
                    <Label
                    content={<CustomLabel value={totalEstoque} />}
                    position="center"
                    />
                  </Pie>
                  <Legend content={<CustomizedLegend />}/>
                </PieChart>
              </ResponsiveContainer>
          }
        </h1>
      </div>
    </div>
  );
}

export default JumbotronTotalEstoque