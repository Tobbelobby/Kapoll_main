import React, {ChangeEvent, useState} from 'react';
import { useNavigate,BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "../App.css";

//import {Form, Button, Card} from 'react-bootstrap'
import "../styles/PollOnline.css"

import {auth} from "../firebase";
import firebase from "firebase/compat/app";
import KapollerService from "../services/KapollerService";
import KapollerData from "../types/Kapoller";

export default function Login() {

    const config = {
        apiKey: "AIzaSyCjRtqoXz1m2_xjwI6tz5dVBTTBmTMoZSQ",
        authDomain: "kapoller-77076.firebaseapp.com",
        projectId: "kapoller-77076",
        storageBucket: "kapoller-77076.appspot.com",
        messagingSenderId: "261824646701",
        appId: "1:261824646701:web:90aa767667cba1979d32a4"
    };

    firebase.initializeApp(config);
    const auth = firebase.auth();

    const provider = new firebase.auth.GoogleAuthProvider();
    provider.addScope('profile');
    provider.addScope('email');

    let navigate = useNavigate();
    const routeChange = () =>{
        let path = `/myProfile`;
        navigate(path);
    }
    const logInGoogle = () =>{
        auth.signInWithPopup(provider).then(async function (result) {
            // This gives you a Google Access Token.
            //const token = result.credential.accessToken;

            // @ts-ignore
            const token = result.user?.refreshToken;
            if (token) {sessionStorage.setItem('Auth Token', token)};

            console.log("TOKEN");
            console.log(token);
            // The signed-in user info.
            const user = result.user;
            console.log(user);
            if (user) {
                //FIX: does not work!!!
                if (await KapollerService.existsAccount(user.email)) {
                    saveKapoller(user);
                }
            }
            routeChange()

        });
    }

    const initialKapoller = {
        id: "",
        firstName: "",
        lastName: "",
        userName: ""
    };
    const [kapoller, setKapoller] = useState<KapollerData>(initialKapoller)
    const [submitted, setSubmitted] = useState<boolean>(false);

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        setKapoller({...kapoller, [name]: value});
    };

    const saveKapoller = (user: firebase.User) => {
        console.log("save kapoller()");
        var data:KapollerData = {
            firstName: user.displayName?.split(" ")[0],
            lastName: user.displayName?.split(" ")[1],
            userName: user.email
        };

        KapollerService.create(data)
            .then((response: any) => {
                response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
                response.header("Access-Control-Allow-Origin", "*");
                response.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT,DELETE");
                setKapoller({
                    id: response.data.id,
                    firstName: response.data.firstName,
                    lastName: response.data.lastName,
                    userName: response.data.userName
                });
                setSubmitted(true);
                console.log(response.data.id);
            })
            .catch((e: Error) => {
                console.log(e)
            });
    };

    //FIX not used
    const newKapoller = () => {
        setKapoller(initialKapoller);
        setSubmitted(false)
    };


    return (
        <div>
            <h2 className="text-center mb-4">Welcome to Kapoll</h2>
            <div>
                <button className={"w-100 myProfileButton"} onClick={logInGoogle} >Login with google</button>
            </div>

        </div>
    );
}

export {};