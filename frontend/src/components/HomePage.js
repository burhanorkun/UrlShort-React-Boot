import React, { useState } from "react";
import axios from "axios";
import Loader from "react-loader-spinner";
import { Link } from "react-router-dom";

const HomePage = () => {
  const [link, setlink] = useState("");
  const [newlink, setnewlink] = useState("");
  const [yourlink, setyourlink] = useState("");
  const [loading, setloading] = useState(false);

  const BASE_URL = "/api/v1/url";

  const add = (e) => {
    e.preventDefault();
    setloading(true);
    //console.log("basladi");
    axios
      /*.post(BASE_URL, {
        link: link,
      }) */
      .post(BASE_URL, {
        "salt": "",
        "url": link
      })
      .then((res) => {
        console.log("res:" + res.data.url);
        setnewlink(`${res.data.url}`);
        setyourlink("Your Link is: ");
        setloading(false);
      });
    //console.log("bitti");
    setloading(false);
  };

  return (
    <div className="bigcontainer">
      <div className="container">
        <nav>
          <a href="/">
            <button className="title">Shortener Link</button>
          </a>
        </nav>

        <div className="form">
          {loading ? (
            <div className="link">
              <Loader
                type="Oval"
                color="#00BFFF"
                height={100}
                width={100}
                timeout={0}
              />
            </div>
          ) : (
            <div className="link">
              {yourlink}
              <a href={newlink} target="_blank" rel="noreferrer">
                {newlink}
              </a>
            </div>
          )}
          <form onSubmit={add}>
            <input
              type="text"
              onChange={(e) => setlink(e.target.value)}
              placeholder="Enter your link "
              pattern="http(s?)(:\/\/)((www.)?)(([^.]+)\.)([^.]+)"
              required
            />
            <button type="submit" className="btn btn-dark" onClick={add}>
              Shorten
            </button>
          </form>
        </div>
        <div className="links">
          <Link to="/all">All Records</Link>
          <Link to="/statistic">Statistics</Link>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
