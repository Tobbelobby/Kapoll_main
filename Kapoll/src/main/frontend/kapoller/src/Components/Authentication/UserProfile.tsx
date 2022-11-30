import React, {useEffect, useState} from 'react'
import {auth} from "../firebase";
import {useNavigate, useLocation} from "react-router-dom";

import "../../App.css";
import "../../styles/PollOnline.css"

import profilePic from '../IMG/profilePic.png';
import KapollerData from "../../types/Kapoller";

const initialKapoller = {
    id: "",
    firstName: "",
    lastName: "",
    userName: ""
};
function UserProfile(){
    const [kapoller, setKapoller] = useState<KapollerData>(initialKapoller)
    let navigate = useNavigate();
    const location = useLocation();
    const displayName = location.state;

    const username = sessionStorage.getItem('Username')


    useEffect(() => {
        let authToken = sessionStorage.getItem('Auth Token')
        if (!authToken) {
            //navigating to login because authtoken is false
            navigate('/login')
        }
    },[])

    //Navigates to home-screen
    const routeChange = () =>{
        let path = `/`;
        navigate(path);
    }

    const LogOutWithGoogle = async () => {
        auth.signOut().then(() => {
            // Sign-out successful.
            sessionStorage.removeItem('Auth Token')
            sessionStorage.removeItem('userId')
            console.log("signed out OK")
            routeChange();
        }).catch((e: Error) => {
            // An error happened.
            console.log(e);
        });
    }

    return(
        <div className="flex flex-column centerJust alignJust text-center mb-4">
            <h2 className="flex text-center mb-4">{displayName ? displayName : "Welcome!"}</h2>
            <h2 className="flex text-center mb-4">{username ? username : ""}</h2>
            <img className="flex" id={"profilePicture"} src={profilePic} alt={"Profile-picture"} width="200px"/>
            <button className={"flex w-100 text-center myProfileButton"} onClick={() => navigate('/Poll')}>My Polls</button>
            <button className={"flex w-100 text-center myProfileButton"} onClick={LogOutWithGoogle}>Logout</button>
        </div>
    );
}

export default UserProfile;