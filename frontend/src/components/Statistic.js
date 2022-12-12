import React, { useEffect, useState } from "react";
//import axios from "axios";
import Loader from "react-loader-spinner";
import { Link } from "react-router-dom";
import "./All.module.css";

const Statistic = () => {
    const [loading, setloading] = useState(false);
    const [records, setRecords] = useState();

    const BASE_URL = "/api/v1/url/statistic";
    const DELETE_URL = "/api/v1/url"

    useEffect(() => {
        let isComponentMounted = true;
        setloading(true);
        const fetchData = async () => {
            //const response = await fetch('https://jsonplaceholder.typicode.com/todos/1');
            const res2 = await fetch(BASE_URL);
            //const newData = await response.json();
            const newData2 = await res2.json();
            if (isComponentMounted) {
                //setTodo(newData);
                //console.log("newData:" + newData);
                setRecords(newData2);
            }
        };
        fetchData();
        setloading(false);
        return () => {
            isComponentMounted = false;
        }
    }, []);

    const onDelete = (id) => {
        console.log("onDelete, id:" + id);
        fetch(DELETE_URL + "/" + id, {
            method: 'DELETE'
        }).then(() => {
            console.log('removed');
        }).catch(err => {
            console.error(err)
        });

        let filteredArray = records.filter(item => item.id !== id);
        console.log("filteredArray:" + filteredArray);
        setRecords(filteredArray);
    }

    const formatDate = (string) => {
        var options = { year: 'numeric', month: 'long', day: 'numeric' };
        return new Date(string).toLocaleDateString([], options);
    }


    return (
        <div className="bigcontainer">
            <div className="container">
                <nav>
                    <a href="/">
                        <button className="title">Shortener Link</button>
                    </a>
                </nav>
                <br />
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
                ) :
                    <table cellSpacing="0">
                        <thead>
                            <tr>
                                <th align="left">Called</th>
                                <th align="left">Key</th>
                                <th align="left">longUrl</th>
                                <th align="left">CreatedDate</th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody>
                            {records && records.map((rec) => (
                                <tr key={rec.id}>
                                    <td>{rec.viewed}</td>
                                    <td>{rec.key}</td>
                                    <td>{rec.longUrl}</td>
                                    <td>{formatDate(rec.createdDate)}</td>
                                    <td className="table__action-buttons">
                                        <button onClick={() => onDelete(rec.id)}>Delete</button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                }
                <div className="links">
                    <Link to="/all">All Records</Link>
                    <Link to="/statistic">Statistics</Link>
                </div>
            </div>
        </div>
    );
};

export default Statistic;
