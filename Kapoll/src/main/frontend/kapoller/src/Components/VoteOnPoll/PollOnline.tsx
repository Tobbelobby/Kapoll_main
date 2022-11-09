import React, { useState, useEffect, ChangeEvent } from "react";
import "../../styles/PollOnline.css"
import Timer from "./Timer";
import PollOnlineProps from "../../types/PollOnlineProps";
import PollResultData from "../../types/PollResult";
import PollService from "../../services/PollService";
import PostToDweet from "./SendToDweet";
import PollData from "../../types/Poll";



const PollOnline: React.FC<PollOnlineProps> = (props: PollOnlineProps) => {
    const [yes, setYes] = useState<number>(0)
    const [no, setNo] = useState<number>(0)
    const [pollResult, setPollResult] = useState<PollResultData>()
    const [submitted, setSubmitted] = useState<boolean>(false);
    const data = {
        pollId : props.pollId,
        pollQuestion : props.pollQuestion,
        noVotes : props.noVotes,
        yesVotes : props.yesVotes,
        timePublished: props.timePublished
    }
    const result:PollOnlineProps = {
        pollId : props.pollId,
        pollQuestion : props.pollQuestion,
        noVotes : no+props.noVotes,
        yesVotes : yes+props.yesVotes,
        timePublished: props.timePublished
    }
    const SendResults = async (props: PollOnlineProps) => {
        const now: number = Date.now()
        const resultData: PollResultData = {
            noVote:props.noVotes,
            yesVote:props.yesVotes,
            utilDate: now as unknown as Date
        }
        const data:Array<PollResultData> = [resultData]

        PollService.updateResults(props.pollId, data).then((response: any) => {
            setPollResult({
                pollId: response.data.pollId,
                noVote: response.data.noVote,
                yesVote: response.data.yesVote,
                utilDate: response.data.utilDate
            });
            setSubmitted(true);
        })
            .catch((e: Error) => {
                console.log(e)
            });;
        //PostToDweet.sendResults(props)
    }
    return (
        <div id = "container" className={"PollOnlineContainer"}>
            <div id={"resultsAndTimeLeft"} className={"resultsAndTimeContainer"}>
                <div className={"flex flex-column "} id={"results"}>
                    <div className={"flex align-items-center font"}><div className={"yesButton resultDisplayButton"} ></div>{data.yesVotes+yes}</div>
                    <div className="flex align-items-center font"><div className={"noButton resultDisplayButton"}></div>{data.noVotes+no}</div>
                </div>
                <Timer/>
            </div>
            <div id={"question"} className={"pollOnlineQuestion"} >{data.pollQuestion}</div>
            <div id={"votingButtons"} className={'pollOnlineButtonContainer'}>
                <button id={"reset"} className={"resetAndSendButton"}>RESET</button>
                <button id={"yes"} className={"yesButton"} onClick={() => setYes(yes+1)}>YES</button>
                <button id={"no"} className={"noButton"} onClick={() => setNo(no+1)}>NO</button>
                <button id={"send"} className={"resetAndSendButton"} onClick={(e:any) => SendResults(result)}>SEND</button>
            </div>

        </div>
    )
}

export default PollOnline;