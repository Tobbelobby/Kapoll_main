import React, {useEffect, useRef, useState} from 'react'
import {auth} from "../../firebase";
import {useNavigate, BrowserRouter as Router, Routes, Route, useLocation, useParams} from "react-router-dom";


import "../../App.css";
import "../../styles/PollOnline.css"
//import {Form, Button, Nav, Card} from 'react-bootstrap'

import pp from '../IMG/img_1.png';
import KapollerData from "../../types/Kapoller";
import KapollerService from "../../services/KapollerService";

const initialKapoller = {
    id: "",
    firstName: "",
    lastName: "",
    userName: ""
};
function UserProfile(){
    const {id} = useParams();
    const [kapoller, setKapoller] = useState<KapollerData>(initialKapoller)
    //KapollerService.get(id?id: "");
    //setKapoller()
    let navigate = useNavigate();
    const location = useLocation();
    const displayName = location.state;
    console.log("SJEEKKE");
    //console.log(location.state);


    useEffect(() => {
        let authToken = sessionStorage.getItem('Auth Token')
        console.log('auth token is')
        if (authToken) {
            console.log('navigating to profile')
        } else {
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
            sessionStorage.removeItem('userId')
            console.log("signed out OK")
            // Sign-out successful.
            routeChange();
        }).catch((e: Error) => {
            // An error happened.
            console.log(e);
        });

    }



    return(
        <div className="flex flex-column centerJust alignJust text-center mb-4">
            <h2 className="flex text-center mb-4">{displayName ? displayName : "Tobias Sagvaag Kristensen"}{kapoller.firstName} {kapoller.lastName}</h2>
            <img className="flex" id={"profilePicture"} src={pp} alt={"Profile-picture"} width="200px"/>
            <button className={"flex w-100 text-center myProfileButton"} onClick={() => navigate('/Poll')}>My Polls</button>
            <button className={"flex w-100 text-center myProfileButton"} onClick={LogOutWithGoogle}>Logout</button>
        </div>
    );
}

export default UserProfile;