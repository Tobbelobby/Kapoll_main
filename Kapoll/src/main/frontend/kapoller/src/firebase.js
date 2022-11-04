

import firebase from 'firebase/compat/app';
import 'firebase/compat/auth';


const config = {
    apiKey: "AIzaSyCjRtqoXz1m2_xjwI6tz5dVBTTBmTMoZSQ",
    authDomain: "kapoller-77076.firebaseapp.com",
    projectId: "kapoller-77076",
    storageBucket: "kapoller-77076.appspot.com",
    messagingSenderId: "261824646701",
    appId: "1:261824646701:web:90aa767667cba1979d32a4"
};

firebase.initializeApp(config);
export const auth = firebase.auth();

const provider = new firebase.auth.GoogleAuthProvider();
export const signInWithGoogle = async () => {
    console.log("signedin");
    await auth.signInWithPopup(provider);
}

export {}