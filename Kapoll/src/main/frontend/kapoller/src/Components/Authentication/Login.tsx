import React, {ChangeEvent, useEffect, useState} from 'react';
import { useNavigate} from "react-router-dom";
import "../../App.css";
import "../../styles/PollOnline.css"

import firebase from "firebase/compat/app";
import KapollerService from "../../services/KapollerService";
import KapollerData from "../../types/Kapoller";
import {auth} from "../../firebase";

function Login() {
    let navigate = useNavigate();
    useEffect(() => {
        let authToken = sessionStorage.getItem('Auth Token')
        if (authToken) {
            //navigating to profile because user is already logged in
            navigate('/myProfile')
        }
    },[])


    const provider = new firebase.auth.GoogleAuthProvider();
    provider.addScope('profile');
    provider.addScope('email');

    const username = "";
    const logInGoogle = () =>{
        auth.signInWithPopup(provider).then(async function (result) {

            // @ts-ignore
            const token = result.user?.refreshToken;
            if (token) {sessionStorage.setItem('Auth Token', token)};

            // The signed-in user info.
            const user = result.user;



            //nonlocal username
            if (user && user.email) {
                sessionStorage.setItem('Username', user.displayName?user.displayName:"")

                if (await KapollerService.existsAccount(user.email).then((response:any)=>response.data)) {
                    const id: any = await KapollerService.getUserByUsername(user.email).then(response => response.data.id)
                    sessionStorage.setItem('userId', id)
                }
                else {
                    saveKapoller(user)
                }
            }
            //navigate(`/myProfile`, {state:{userObj:user?.displayName}});
            navigate(`/myProfile`);
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

    //When saving firstName and lastName, we use the first and second word that google return as displayname,
    // but this should be improved because there are cases when a person have middel names
    const saveKapoller = (user: firebase.User) => {
        var data:KapollerData = {
            firstName: user.displayName?.split(" ")[0],
            lastName: user.displayName?.split(" ")[1],
            userName: user.email
        };

        KapollerService.create(data)
            .then((response: any) => (response.json()).then(async(result:any) => {
                setKapoller({
                    id: result.data.id,
                    firstName: result.data.firstName,
                    lastName: result.data.lastName,
                    userName: result.data.userName
                });
                setSubmitted(true);
                if (kapoller.userName) {
                    const id: any = await KapollerService.getUserByUsername(kapoller.userName).then((response:any)=>
                        response.json()).then((result) =>
                        (result.data.id))
                    sessionStorage.setItem('userId', id)}
            })
                .catch((e: Error) => {
                    console.log(e)
                }));
    };


    return (
        <div className={'flex flex-column centerJust'}>
            <h2 className="flex text-center mb-4 font welcomeHeader">Welcome to Kapoll</h2>
            <div>
                <button className={"flex w-100 loginButton"} onClick={logInGoogle} >Login with google</button>
            </div>
        </div>
    );
}

export default Login;