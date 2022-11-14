import React, { useState, useEffect, ChangeEvent } from "react";
import "../../styles/PollOnline.css"
import PollOnlineProps from "../../types/PollOnlineProps";
import PollResultData from "../../types/PollResult";
import ResultChart from "../Poll/ResultChart";
import {useParams} from "react-router-dom";



const PollResult: React.FC = () => {
    const {id} = useParams();
    const [question, setQuestion] = useState<string>("");
    const data = {
        pollId : id? id : '',
        pollQuestion : question,
    }
    return (
        <div id = "container" className={"PollOnlineContainer"}>
            <div id={"question"} className={"pollOnlineQuestion"} >{data.pollQuestion}</div>
            <div id={"resultChar"}>
              <ResultChart/>
            </div>

        </div>
    )
}

export default PollResult;