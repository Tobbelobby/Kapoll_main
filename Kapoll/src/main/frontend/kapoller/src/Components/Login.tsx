import React, {ChangeEvent, useState} from 'react';
import { useNavigate,BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "../App.css";

import {Form, Button, Card} from 'react-bootstrap'
import {auth} from "../firebase";
import firebase from "firebase/compat/app";
import PollData from "../types/Poll";
import PollService from "../services/PollService";
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
        auth.signInWithPopup(provider).then(function (result) {
            // This gives you a Google Access Token.
            //const token = result.credential.accessToken;

            // @ts-ignore
            const token = result.user.getIdTokenResult();
            console.log("TOKEN");
            console.log(token);
            // The signed-in user info.
            const user = result.user;
            console.log(user);
            newKapoller();
            routeChange()

        });
    }

    const initialKapoller = {
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

    const saveKapoller = () => {
        var data = {
            firstName: kapoller.firstName,
            lastName: kapoller.lastName,
            userName: kapoller.userName
        };

        KapollerService.create(data)
            .then((response: any) => {
                response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
                response.header("Access-Control-Allow-Origin", "*");
                response.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT,DELETE");
                setKapoller({
                    firstName: response.data.firstName,
                    lastName: response.data.lastName,
                    userName: response.data.userName
                });
                setSubmitted(true);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e)
            });
    };

    //FIX only if not exist
    const newKapoller = () => {
        setKapoller(initialKapoller);
        setSubmitted(false)
    };


    return (
        <><Card>
            <Card.Body>
                <h2 className="text-center mb-4">Welcome to Kapoll</h2>
                <Form>
                    <Button className="w-100" onClick={logInGoogle} >Login with google</Button>
                </Form>
            </Card.Body>
        </Card>
        </>
    );
}


// import React, {useRef} from 'react'
// import {Form, Button, Card} from 'react-bootstrap'
// import {SignInWithGoogle, LogOutWithGoogle, SignIn} from "../firebase";
// import "./test.css"
// import ReactDOM from "react-dom/client";
// import {render} from "react-dom";
//
// // render(
// //     <div>
// //         <h1>Hello, Welcome to React and TypeScript</h1>
// //     </div>,
// //     document.getElementById("root")
// // );
//
//
// export default function Login(){
//     const emailRef = useRef()
//     const passwordRef = useRef()
//     const passwordConfirmRef = useRef()
//
//
//
//     return(
//         <><Card>
//             <Card.Body>
//                 <div id="root"></div>
//                 <h2 className="text-center mb-4">Welcome to Kapoll</h2>
//                 <Form>
//                     <Button className="w-100" onClick={SignIn} >Login with google</Button>
//                 </Form>
//             </Card.Body>
//         </Card>
//         </>
//     )
// }
// export function Logout(){
//     return(
//         <><Card>
//             <Card.Body>
//                 <h2 className="text-center mb-4">You are logged in</h2>
//                 <Form>
//                     <Button className="w-100" onClick={LogOutWithGoogle} >Logout</Button>
//                 </Form>
//             </Card.Body>
//         </Card>
//         </>
//     )
// }


//
// class LoginControl extends React.Component {
//     constructor(props: any) {
//         super(props);
//         this.handleLoginClick = this.handleLoginClick.bind(this);
//         this.handleLogoutClick = this.handleLogoutClick.bind(this);
//         this.state = {isLoggedIn: false};
//     }
//
//     handleLoginClick() {
//         this.setState({isLoggedIn: true});
//     }
//
//     handleLogoutClick() {
//         this.setState({isLoggedIn: false});
//     }
//
//     render() {
//         // @ts-ignore
//         const isLoggedIn = this.state.isLoggedIn;
//         let button;
//
//         if (isLoggedIn) {
//             button = <LogoutButton onClick={this.handleLogoutClick} />;
//         } else {
//             button = <LoginButton onClick={this.handleLoginClick} />;
//         }
//
//
//         return (
//             <div>
//                 <Greeting isLoggedIn={isLoggedIn} />
//                 {button}
//             </div>
//         );
//     }
// }
//
// function UserGreeting(props:any) {
//     return <h1>Welcome back!</h1>;
// }
//
// function GuestGreeting(props: any) {
//     return <h1>Please sign up.</h1>;
// }
//
// function Greeting(props: { isLoggedIn: any; }) {
//     const isLoggedIn = props.isLoggedIn;
//     if (isLoggedIn) {
//         return <UserGreeting />;
//     }
//     return <GuestGreeting />;
// }
//
// function LoginButton(props:any) {
//     return (
//         <button onClick={props.onClick}>
//             Login
//         </button>
//     );
// }
//
// function LogoutButton(props:any) {
//     return (
//         <button onClick={props.onClick}>
//             Logout
//         </button>
//     );
// }
//
// // @ts-ignore
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(<LoginControl />);

export {};