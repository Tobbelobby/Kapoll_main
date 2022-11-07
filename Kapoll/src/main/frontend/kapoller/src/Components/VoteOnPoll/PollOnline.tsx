import React, { useState, useEffect, ChangeEvent } from "react";
import "../../styles/PollOnline.css"
import Timer from "./Timer";
import PostToDweet from "./SendToDweet"
import dweetResult from "./SendToDweet";

const PollOnline: React.FC = () => {
    const data = {
        pollTitle : "Is pizza not good?",
        noVotes : 0,
        yesVotes : 0,
        timePublished: Date.now()
    }
    return (
        <div id = "container" className={"PollOnlineContainer"}>
            <div id={"resultsAndTimeLeft"} className={"resultsAndTimeContainer"}>
                <div className={"flex flex-column "} id={"results"}>
                    <div className={"flex align-items-center font"}><div className={"yesButton resultDisplayButton"} ></div>{data.yesVotes}</div>
                    <div className="flex align-items-center font"><div className={"noButton resultDisplayButton"}></div>{data.noVotes}</div>
                </div>
                <Timer/>
            </div>
            <div id={"question"} className={"pollOnlineQuestion"} >Is pizza not good?</div>
            <div id={"votingButtons"} className={'pollOnlineButtonContainer'}>
                <button id={"reset"} className={"resetAndSendButton"}>RESET</button>
                <button id={"yes"} className={"yesButton"} onClick={() => data.yesVotes+=1}>YES</button>
                <button id={"no"} className={"noButton"} onClick={() => data.noVotes+=1}>NO</button>
                <button id={"send"} className={"resetAndSendButton"} onClick={() => PostToDweet.sendResults(data)}>SEND</button>
            </div>

        </div>
    )
}

export default PollOnline;