import { Fragment, useContext } from "react";

import { Toaster } from 'react-hot-toast';

import Navbar from "../components/bootstrap-components/Navbar";

const Homepage = () => {

  return (
    <Fragment>
      <Toaster />
      <Navbar />
      <h1>Home</h1>
    </Fragment>
  );
}

export default Homepage