import React, {useEffect, useRef} from 'react'
import {auth} from "../firebase";
import { useNavigate,BrowserRouter as Router, Routes, Route } from "react-router-dom";

import "../App.css";
import "../styles/PollOnline.css"
//import {Form, Button, Nav, Card} from 'react-bootstrap'

import pp from './img_1.png';




export default function UserProfile(){
    let navigate = useNavigate();
    useEffect(() => {
        let authToken = sessionStorage.getItem('Auth Token')
        console.log('auth token is')
        if (authToken) {
            console.log('navigating to profile')
            navigate('/myProfile')
        }
        else {
            console.log('navigating to login because authtoken is false')
            navigate('/login')
        }
    },[])

    const routeChange = () =>{
        let path = `/`;
        navigate(path);
        console.log("to home")
    }



    const LogOutWithGoogle = async () => {
        auth.signOut().then(() => {
            sessionStorage.removeItem('Auth Token')
            console.log("signed out OK")
            //const navigate = useNavigate();
            //navigate('/Signup')
            // Sign-out successful.
            routeChange();
        }).catch((e:Error) => {
            // An error happened.
            console.log(e);
        });

    }

    return(
        <div>
            <h2 className="text-center mb-4">currentUser displayname</h2>
            <img src={pp} alt={"Profile-picture"} width="200px"></img>
            <div>
                <div><button className={"w-100 myProfileButton"}>My Polls</button></div>
                <button className={"w-100 myProfileButton"} onClick={LogOutWithGoogle}>Logout</button>
            </div>
        </div>
    );
}

export {};