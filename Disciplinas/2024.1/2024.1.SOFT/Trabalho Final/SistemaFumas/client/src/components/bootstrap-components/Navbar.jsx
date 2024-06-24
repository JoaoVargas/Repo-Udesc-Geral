import { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { GeneralContext } from '../../contexts/GeneralContext';

import logo from '../../assets/logo-low.png'

const Navbar = () => {
  const navigate = useNavigate();
  const { currentPage, setCurrentPage } = useContext(GeneralContext);

  const handleNavigate = (route) => {
    setCurrentPage(route);
    navigate(route);
  }

  return (
    <nav className="navbar navbar-expand-lg bg-body-tertiary">
      <div className="container-fluid">
        <a 
        className="navbar-brand d-flex gap-3 align-items-center cursor-pointer" 
        onClick={() => handleNavigate("/")}>
          <img 
          src={logo} 
          alt="Logo" 
          width="50" 
          height="50" 
          className="d-inline-block "/>
          Sistema Fumas
        </a>
        <button 
        className="navbar-toggler" 
        type="button" 
        data-bs-toggle="collapse" 
        data-bs-target="#navbarNav" 
        aria-controls="navbarNav" 
        aria-expanded="false" 
        aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav">
            <li className="nav-item">
              <a 
              className={ 
                currentPage == '/' 
                ? "nav-link active cursor-pointer" 
                : "nav-link cursor-pointer" }
              aria-current="page" 
              onClick={() => handleNavigate("/")}>
                Geral
              </a>
            </li>
            <li className="nav-item">
              <a 
              className={ 
                currentPage == '/fornecedores/' 
                ? "nav-link active cursor-pointer" 
                : "nav-link cursor-pointer" }
              aria-current="page" 
              onClick={() => handleNavigate("/fornecedores/")}>
                Fornecedores
              </a>
            </li>
            {/* <li className="nav-item">
              <a className="nav-link" href="#">Pricing</a>
            </li>
            <li className="nav-item">
              <a className="nav-link disabled" aria-disabled="true">Disabled</a>
            </li> */}
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Navbar