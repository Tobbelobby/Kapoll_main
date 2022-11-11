import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "../App.css";

import AddPoll from "./Poll/createPoll";
import Poll from "./Poll/Poll";
import PollsList from "./Poll/PollsList";
import PollOnline from "./VoteOnPoll/PollOnline"
import StartPoll from "./Poll/startPoll";
import Login from "./Login";
import UserProfile from "./UserProfile";

const App: React.FC = () => {
    return (
        <div className={'background'}>

            <nav className="navbar navbar-expand navbar-dark bg-dark">
                <a href="/Poll" className="navbar-brand">
                    Kapoll!
                </a>
                <div className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link to={"/Poll"} className="nav-link">
                            Polls
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to={"/add"} className="nav-link">
                            Add a poll
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to={"/myProfile"} className="nav-link">
                            My user-profile
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to={"/login"} className="nav-link">
                            Login
                        </Link>
                    </li>
                </div>
            </nav>

            <div className="flex space_around fill">
                <Routes>
                    <Route path="/" element={<PollsList/>} />
                    <Route path="/Poll" element={<PollsList/>} />
                    <Route path="/add" element={<AddPoll/>} />
                    <Route path="/Poll/:id" element={<Poll/>} />
                    <Route path={"/:id"} element={<StartPoll/>}/>
                    <Route path={"/myProfile"} element={<UserProfile/>}/>
                    <Route path={"/login"} element={<Login/>}/>
                </Routes>
            </div>
        </div>
    );
}

export default App;