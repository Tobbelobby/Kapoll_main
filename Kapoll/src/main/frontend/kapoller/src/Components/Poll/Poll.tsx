import React, { useState, useEffect, ChangeEvent } from "react";
import { useParams, useNavigate } from 'react-router-dom';

import PollService from "../../services/PollService";
import PollData from "../../types/Poll";

const Poll: React.FC = () => {
    const { id }= useParams();
    let navigate = useNavigate();

    const initialPollState = {
        id: null,
        title: "",
        question: "",
        time: 0
    };
    const [currentPoll, setCurrentPoll] = useState<PollData>(initialPollState);
    const [message, setMessage] = useState<string>("");


    const getPoll = (id: string) => {
        PollService.get(id)
            .then((response: any) => {
                setCurrentPoll(response.data);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    useEffect(() => {
        if (id)
            getPoll(id);
    }, [id]);

    const handleInputChange = (event: ChangeEvent) => {
        const target = event.target as HTMLInputElement;
        const { name, value } = target;
        setCurrentPoll({ ...currentPoll, [name]: value });
    };


    const updatePoll = () => {
        PollService.update(currentPoll.id, currentPoll)
            .then((response: any) => {
                response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
                response.header("Access-Control-Allow-Origin", "*");
                response.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT,DELETE");
                console.log(response.data);
                setMessage("The Poll was updated successfully!");
            })
            .catch((e: Error) => {
                console.log('heiii')
                console.log(e);
            });
    };

    const deletePoll = () => {
        PollService.remove(currentPoll.id)
            .then((response: any) => {
                console.log(response.data);
                navigate("/Poll");
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    return (
        <div>
            {currentPoll ? (
                <div className="edit-form">
                    <h4>Poll</h4>
                    <form>
                        <div className="form-group">
                            <label htmlFor="title">Title</label>
                            <input
                                type="text"
                                className="form-control"
                                id="title"
                                name="title"
                                value={currentPoll.title}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="question">question</label>
                            <input
                                type="text"
                                className="form-control"
                                id="question"
                                name="question"
                                value={currentPoll.question}
                                onChange={handleInputChange}
                            />
                        </div>
                        
                    </form>
                    <button className="badge badge-danger mr-2" onClick={deletePoll}>
                        Delete
                    </button>

                    <button
                        type="submit"
                        className="badge badge-success"
                        onClick={updatePoll}
                    >
                        Update
                    </button>
                    <p>{message}</p>
                </div>
            ) : (
                <div>
                    <br />
                    <p>Please click on a Poll...</p>
                </div>
            )}
        </div>
    );
};

export default Poll;