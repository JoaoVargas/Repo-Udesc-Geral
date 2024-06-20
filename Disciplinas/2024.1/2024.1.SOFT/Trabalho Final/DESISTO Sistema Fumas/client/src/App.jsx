import { useEffect, useState } from 'react';
import './assets/App.css';

function App() {
  const [data, setData] = useState(null);  
  
  useEffect(() => {
    const callBackendAPI = async () => {
      try {
        const response = await fetch("/api");

        if (!response.ok) {
          throw new Error("Failed to fetch data");
        }

        const body = await response.json();
        setData(body.message);

      } catch (error) {
        console.error(error.message);
      }
    };    

    const fetchSla = async () => {
      try {
        const response = await fetch("/api/sla");

        if (!response.ok) {
          throw new Error("Failed to fetch data");
        }
        console.log(response);

      } catch (error) {
        console.error(error.message);
      }
    };
    
    fetchSla()
    callBackendAPI();
  }, []);

      

  return (
    <div>
      {data}
    </div>
  );
}

export default App;
