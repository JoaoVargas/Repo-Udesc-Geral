import { Fragment, useContext } from "react";

import { Toaster } from 'react-hot-toast';

import Navbar from "../components/bootstrap-components/Navbar";

const Produtos = () => {
  return (
    <Fragment>
      <Toaster />
      <Navbar />
      <h1>Produtos</h1>
    </Fragment>
  );
}

export default Produtos