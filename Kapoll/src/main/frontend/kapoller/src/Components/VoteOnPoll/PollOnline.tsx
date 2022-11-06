import React, { useState, useEffect, ChangeEvent } from "react";
import "../../styles/PollOnline.css"
import Timer from "./Timer";

const PollOnline: React.FC = () => {
    return (
        <div id = "container" className={"PollOnlineContainer"}>
            <div id={"resultsAndTimeLeft"} className={"resultsAndTimeContainer"}>
                <div className={"flex flex-column "} id={"results"}>
                    <div className={"flex align-items-center font"}><div className={"yesButton resultDisplayButton"} ></div>40</div>
                    <div className="flex align-items-center font"><div className={"noButton resultDisplayButton"}></div>20</div>
                </div>
                <Timer/>
            </div>
            <div id={"question"} className={"pollOnlineQuestion"} >Is pizza good?</div>
            <div id={"votingButtons"} className={'pollOnlineButtonContainer'}>
                <button id={"reset"} className={"resetAndSendButton"}>RESET</button>
                <button id={"yes"} className={"yesButton"}>YES</button>
                <button id={"no"} className={"noButton"}>NO</button>
                <button id={"send"} className={"resetAndSendButton"}>SEND</button>
            </div>

        </div>
    )
}

export default PollOnline;