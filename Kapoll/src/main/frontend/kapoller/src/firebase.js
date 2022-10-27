//Set up firebase

import firebase from "firebase/app"
import "firebase/auth"

const app = firebase.initializeApp({
    apiKey: "AIzaSyCjRtqoXz1m2_xjwI6tz5dVBTTBmTMoZSQ",
    authDomain: "kapoller-77076.firebaseapp.com",
    projectId: "kapoller-77076",
    storageBucket: "kapoller-77076.appspot.com",
    messagingSenderId: "261824646701",
    appId: "1:261824646701:web:90aa767667cba1979d32a4"
})

export const auth = app.auth()
export default app