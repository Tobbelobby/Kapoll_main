import React, {useEffect, useRef, useState} from 'react'
import {auth} from "../../firebase";
import {useNavigate, BrowserRouter as Router, Routes, Route} from "react-router-dom";

import "../../App.css";
import "../../styles/PollOnline.css"
//import {Form, Button, Nav, Card} from 'react-bootstrap'

import pp from '../IMG/img_1.png';


function UserProfile() {
    let navigate = useNavigate();
    useEffect(() => {
        let authToken = sessionStorage.getItem('Auth Token')
        console.log('auth token is')
        if (authToken) {
            console.log('navigating to profile')
        } else {
            console.log('navigating to login because authtoken is false')
            navigate('/login')
        }
    }, [])

    const routeChange = () => {
        let path = `/`;
        navigate(path);
        console.log("to home")
    }


    const LogOutWithGoogle = async () => {
        auth.signOut().then(() => {
            sessionStorage.removeItem('Auth Token')
            sessionStorage.removeItem('userId')
            console.log("signed out OK")
            //const navigate = useNavigate();
            //navigate('/Signup')
            // Sign-out successful.
            routeChange();
        }).catch((e: Error) => {
            // An error happened.
            console.log(e);
        });

    }

    return (
        <div className="flex flex-column centerJust alignJust text-center mb-4">
            <h2 className="flex text-center mb-4">currentUser displayname</h2>
            <img className="flex  " id={"profilePicture"} src={pp} alt={"Profile-picture"} width="200px"/>
            <button className={"flex w-100 text-center myProfileButton"} onClick={() => navigate("/Poll")}>My Polls
            </button>
            <button className={"flex w-100 text-center myProfileButton"} onClick={LogOutWithGoogle}>Logout</button>

        </div>
    );
}

export default UserProfile;