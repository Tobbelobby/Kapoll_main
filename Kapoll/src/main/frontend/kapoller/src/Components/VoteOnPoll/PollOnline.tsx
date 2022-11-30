import React, {useState, useEffect, ChangeEvent} from "react";
import "../../styles/PollOnline.css"
import Timer from "./Timer";
import PollOnlineProps from "../../types/PollOnlineProps";
import PollResultData from "../../types/PollResult";
import PollService from "../../services/PollService";
import {useNavigate} from "react-router-dom";


const PollOnline: React.FC<PollOnlineProps> = (props: PollOnlineProps) => {
    let navigate = useNavigate();
    const [yes, setYes] = useState<number>(0)
    const [no, setNo] = useState<number>(0)
    const [pollResult, setPollResult] = useState<PollResultData>()
    const [submitted, setSubmitted] = useState<boolean>(false);
    let result: PollOnlineProps = {
        pollId: props.pollId,
        pollQuestion: props.pollQuestion,
        noVotes: no,
        yesVotes: yes,
        timePublished: props.timePublished
    }
    useEffect(() => {
        window.onbeforeunload = () => {
            setYes(0);
            setNo(0);
        }

    })
    const SendResults = async (props: PollOnlineProps) => {
        const now: number = Date.now()
        const resultData: PollResultData = {
            noVote: props.noVotes,
            yesVote: props.yesVotes,
            utilDate: now as unknown as Date
        }
        const data: Array<PollResultData> = [resultData]

        PollService.updateResults(props.pollId, data).then((response: any) => {
            setSubmitted(true);
        })
            .catch((e: Error) => {
                console.log(e)
            });

    }
    return (
        <div id="container" className={"PollOnlineContainer"}>
            <div id={"resultsAndTimeLeft"} className={"resultsAndTimeContainer"}>
                <div className={"flex flex-column "} id={"results"}>
                    <div className={"flex align-items-center font"}>
                        <div className={"yesButton resultDisplayButton"}></div>
                        {props.yesVotes + yes}</div>
                    <div className="flex align-items-center font">
                        <div className={"noButton resultDisplayButton"}></div>
                        {props.noVotes + no}</div>
                </div>
                <Timer/>
            </div>
            <div id={"question"} className={"pollOnlineQuestion"}>{props.pollQuestion}</div>
            <div id={"votingButtons"} className={'pollOnlineButtonContainer'}>
                <button id={"reset"} className={"resetAndSendButton"} onClick={() => {
                    setYes(0);
                    setNo(0)
                }}>RESET
                </button>
                <button id={"yes"} className={"yesButton"} onClick={() => setYes(yes + 1)}>YES</button>
                <button id={"no"} className={"noButton"} onClick={() => setNo(no + 1)}>NO</button>
                <button id={"send"} className={"resetAndSendButton"} onClick={(e: any) => {
                    SendResults(result);
                    navigate(`/PollResult/${props.pollId}`, {
                        state: {
                            pollId: props.pollId,
                            pollQuestion: props.pollQuestion,
                            yesVotes: props.yesVotes + yes,
                            noVotes: props.noVotes + no
                        }
                    })
                }}>SEND
                </button>
            </div>

        </div>
    )
}

export default PollOnline;