import { Fragment, useContext } from "react";

import { Toaster } from 'react-hot-toast';

import Navbar from "../components/bootstrap-components/Navbar";

const Itens = () => {
  return (
    <Fragment>
      <Toaster />
      <Navbar />
      <h1>Itens</h1>
    </Fragment>
  );
}

export default Itens