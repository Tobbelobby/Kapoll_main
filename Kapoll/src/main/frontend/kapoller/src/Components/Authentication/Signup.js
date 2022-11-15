/*import React, {useRef} from 'react'
import {Form, Button, Card} from 'react-bootstrap'
import {signInWithGoogle} from "../firebase";

export default function Signup(){
    const emailRef = useRef()
    const passwordRef = useRef()
    const passwordConfirmRef = useRef()

    return(
        <><Card>
            <Card.Body>
                <h2 className="text-center mb-4">Sign Up</h2>
                <Form>
                    <Form.Group id="email">
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" ref={emailRef} />
                    </Form.Group>
                    <Form.Group id="password">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" ref={passwordRef} />
                    </Form.Group>
                    <Form.Group id="password-confirm">
                        <Form.Label>Password Confirmation</Form.Label>
                        <Form.Control type="email" ref={passwordConfirmRef} />
                    </Form.Group>
                    <Button className="w-100" onClick={signInWithGoogle} type="submit">Sign Up</Button>
                    <h1 id="t">HELLO WORLD</h1>
                </Form>
            </Card.Body>
        </Card>
            <div className="w-100 text-center mt-2">
                Already have an account? Log In
            </div>
        </>
    )
}

export {};

import React, {useRef} from 'react'
import {Form, Button, Card} from 'react-bootstrap'
import {signInWithGoogle} from "../../firebase";

export default function Signup(){
    const emailRef = useRef()
    const passwordRef = useRef()
    const passwordConfirmRef = useRef()

    return(
        <><Card>
            <Card.Body>
                <h2 className="text-center mb-4">Sign Up</h2>
                <Form>
                    <Form.Group id="email">
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" ref={emailRef} required/>
                    </Form.Group>
                    <Form.Group id="password">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" ref={passwordRef} required/>
                    </Form.Group>
                    <Form.Group id="password-confirm">
                        <Form.Label>Password Confirmation</Form.Label>
                        <Form.Control type="email" ref={passwordConfirmRef} required/>
                    </Form.Group>
                    <Button className="w-100" onClick={signInWithGoogle} >Sign Up</Button>
                    <h1 id="t">HELLO WORLD</h1>
                </Form>
            </Card.Body>
        </Card>
            <div className="w-100 text-center mt-2">
                Already have an account? Log In
            </div>
        </>
    )
}
*/
export {};