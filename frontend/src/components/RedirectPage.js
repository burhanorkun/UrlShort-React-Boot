import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

const RedirectPage = () => {
  const [redirect, setredirect] = useState("");
  const [error, seterror] = useState(false)
  const link = useParams();
  const BASE_URL = "/api/v1/url";
  useEffect(() => {
    axios
      .get(`${BASE_URL}?link=${link.link}`)
      .then((res) => setredirect(res.data.url))
      .catch(() => seterror(true))
  }, []);
  if (error === true) {
    return <div className="error">No Such Link (Böyle Bir Link Yok)</div>
  }
  if (redirect !== "") {
    window.location.assign(redirect);
  }
  return <div className="loading">Redirecting...</div>;
};

export default RedirectPage;
