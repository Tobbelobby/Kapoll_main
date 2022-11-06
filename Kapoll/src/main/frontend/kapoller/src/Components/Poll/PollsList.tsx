import React, { useState, useEffect, ChangeEvent } from "react";
import PollService from "../../services/PollService";
import PollData from "../../types/Poll";
import {Link} from "react-router-dom";

const PollsList: React.FC = () => {
    const [polls, setPolls] = useState<Array<PollData>>([]);
    const [currentPoll, setCurrentPoll] = useState<PollData | null>(null);
    const [currentIndex, setCurrentIndex] = useState<number>(-1);

    useEffect(() => {
        retrievePolls();
    }, []);


    const retrievePolls = () => {
        PollService.getAll()
            .then((response: any) => {
                setPolls(response.data);
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    const refreshList = () => {
        retrievePolls();
        setCurrentPoll(null);
        setCurrentIndex(-1);
    };

    const setActivePoll = (poll: PollData, index: number) => {
        setCurrentPoll(poll);
        setCurrentIndex(index);
    };

    const removePoll = (id: string) => {
        PollService.remove(id)
            .then((response: any) => {
                console.log(response.data);
                refreshList();
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };



    return (
        <div className="list row">
            <div className="col-md-6">
                <h4>Poll List</h4>

                <ul className="list-group">
                    {polls &&
                        polls.map((poll, index) => (
                            <li
                                className={
                                    "list-group-item " + (index === currentIndex ? "active" : "")
                                }
                                onClick={() => setActivePoll(poll, index)}
                                key={index}
                            >
                                {poll.title}
                            </li>
                        ))}
                </ul>

              {/*  <button
                    className="m-3 btn btn-sm btn-danger"
                    onClick={removePoll}
                >
                    Remove All
                </button>*/}
            </div>
            <div className="col-md-6">
                {currentPoll ? (
                    <div>
                        <h4>Poll</h4>
                        <div>
                            <label>
                                <strong>Title:</strong>
                            </label>{" "}
                            {currentPoll.title}
                        </div>
                        <div>
                            <label>
                                <strong>Description:</strong>
                            </label>{" "}
                            {currentPoll.question}
                        </div>
                        <div>
                            <label>
                                <strong>Time:</strong>
                            </label>{" "}
                            {currentPoll.time}
                        </div>

                        <Link
                            to={"/Poll/" + currentPoll.id}
                            className="badge badge-warning"
                        >
                            Edit
                        </Link>
                    </div>
                ) : (
                    <div>
                        <br />
                        <p>Please click on a poll...</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default PollsList;
