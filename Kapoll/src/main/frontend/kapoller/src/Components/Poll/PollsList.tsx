import React, {useState, useEffect, ChangeEvent} from "react";
import PollService from "../../services/PollService";
import PollData from "../../types/Poll";
import {Link} from "react-router-dom";
import KapollerService from "../../services/KapollerService";
import '../../styles/ListOfPolls.css'

const PollsList: React.FC = () => {
    const [polls, setPolls] = useState<Array<PollData>>([]);
    const [currentPoll, setCurrentPoll] = useState<PollData | null>(null);
    const [currentIndex, setCurrentIndex] = useState<number>(-1);

    useEffect(() => {
        retrievePolls();
    }, []);


    const retrievePolls = () => {
        let id: string | null = sessionStorage.getItem('userId')
        if (id) {
            KapollerService.get(id)
                .then((response: any) => {
                    console.log(response)
                    setPolls(response.data.polls);
                    console.log(response.data.polls);
                })
                .catch((e: Error) => {
                    console.log(e);
                });
        }
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
        <div className="flex list row max-width">
            <div className="col-md-6 pollFont half-width">
                <div className={'smallerHeaderFont'}>Your polls</div>

                <ul className="list-group pollList pollFont">
                    {polls &&
                        polls.map((poll, index) => (
                            <li
                                className={
                                    "list-group-item font pollListElement " + (index === currentIndex ? "active" : "")
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
            <div>
                {currentPoll ? (
                    <div className="marginTop half-width font">
                        <div>
                            <label>
                                <strong>Question:</strong>
                            </label>{" "}
                            {currentPoll.question}
                        </div>
                        <div>
                            <label>
                                <strong>Time:</strong>
                            </label>{" "}
                            {currentPoll.time}
                        </div>
                        <div className={'flex'}>
                            <Link
                                to={"/Poll/" + currentPoll.id}
                                className="badge badge-warning copyLinkButton"
                            >
                                Edit
                            </Link>
                            <button className={'copyLinkButton'} id={"copyLink"}
                                    onClick={() => navigator.clipboard.writeText('localhost:3000/' + currentPoll.id)}>Copy
                                Link
                            </button>
                        </div>
                    </div>
                ) : (
                    <div>
                    </div>
                )}
            </div>
        </div>
    );
};

export default PollsList;
