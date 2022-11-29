import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "../App.css";
import AddPoll from "./Poll/createPoll";
import Poll from "./Poll/Poll";
import PollsList from "./Poll/PollsList";
import PollOnline from "./VoteOnPoll/PollOnline"
import StartPoll from "./Poll/startPoll";
import Login from "./Authentication/Login";
import UserProfile from "./Authentication/UserProfile";
import ResultChart from "./Poll/ResultChart";
import PollResult from "./VoteOnPoll/PollResult";

const App: React.FC = () => {
    return (
        <div className={'background'}>

            <nav className="navbar navbar-expand navbar-dark bg-dark">
                <a href={sessionStorage.getItem('userId') ? "/Poll" : "/login"} className="navbar-brand">
                    Kapoll!
                </a>
                {!(sessionStorage.getItem('userId')) ? <div className="navbar-nav mr-auto">
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
                            My profile
                        </Link>
                    </li>
                     <li className="nav-item">
                        <Link to={"/login"} className="nav-link">
                            Login
                        </Link>
                    </li>
                </div> : <></> }
            </nav>

            <div className="flex space_around fill">
                <Routes>
                    <Route path={"/"} element={<Login/>}/>
                    <Route path={"/Poll"} element={<PollsList/>}/>
                    <Route path={"/add"} element={<AddPoll/>}/>
                    <Route path={"/Poll/:id"} element={<Poll/>}/>
                    <Route path={"/:id"} element={<StartPoll/>}/>
                    <Route path={"/myProfile"} element={<UserProfile/>}/>
                    <Route path={"/myProfile/:id"} element={<UserProfile/>}/>
                    <Route path={"/login"} element={<Login/>}/>
                    <Route path={"/PollResult/:id"} element={<ResultChart/>}/>
                </Routes>
            </div>
        </div>
    );
}

export default App;