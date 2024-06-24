import { Fragment, useContext } from "react";

import { GeneralContext } from "../contexts/GeneralContext";

import Navbar from "../components/bootstrap-components/Navbar";

const Homepage = () => {
  // const { teste } = useContext(GeneralContext);

  return (
    <Fragment>
      <Navbar />
    </Fragment>
  );
}

export default Homepage