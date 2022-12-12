import React from "react";
import "./App.css";
import HomePage from "./components/HomePage";
import RedirectPage from './components/RedirectPage'
import All from './components/All'
import Statistic from "./components/Statistic";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

const App = () => {
  return (
    <Router>
      <Switch>
        <Route exact path={"/"} component={HomePage} />
        <Route exact path={"/all"} component={All} />
        <Route exact path={"/statistic"} component={Statistic} />
        <Route exact path={"/:link"} component={RedirectPage} />
      </Switch>
    </Router>
  );
};

export default App;
