import React, {useRef} from 'react'
import {Form, Button, Nav, Card} from 'react-bootstrap'
import {auth} from "../firebase";
import { useNavigate,BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "../App.css";
import pp from './img_1.png';



export default function UserProfile(){
    let navigate = useNavigate();
    const routeChange = () =>{
        let path = `/`;
        navigate(path);
        console.log("to home")

    }

    const LogOutWithGoogle = async () => {
        auth.signOut().then(() => {
            console.log("signed out OK")
            //const navigate = useNavigate();
            //navigate('/Signup')
            // Sign-out successful.
            routeChange();
        }).catch((error) => {
            // An error happened.
        });

    }

    return(
        <><Card>
            <Card.Body>
                <h2 className="text-center mb-4">currentUser email</h2>
                <h1 className="text-center mb-4 profilePicture">profile-picture</h1>
                <img src={pp}></img>
                <Form>
                    <Nav.Link><Button className="w-100 myProfileButton">My Polls</Button></Nav.Link>
                    <Button className="w-100 myProfileButton" onClick={LogOutWithGoogle}>Logout</Button>
                </Form>
            </Card.Body>
        </Card>
        </>
    )
}

export {};