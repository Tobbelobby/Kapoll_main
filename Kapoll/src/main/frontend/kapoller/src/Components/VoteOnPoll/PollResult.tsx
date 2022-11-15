import React, { useState, useEffect, ChangeEvent } from "react";
import "../../styles/PollOnline.css"
import PollOnlineProps from "../../types/PollOnlineProps";
import PollResultData from "../../types/PollResult";
import ResultChart from "../Poll/ResultChart";
import {useLocation, useParams} from "react-router-dom";
import {Bar, BarChart, ResponsiveContainer, YAxis} from "recharts";



const PollResult: React.FC = () => {
    const location = useLocation();
    const {id} = useParams();
    const [question, setQuestion] = useState<string>("");
    const data = {
        pollId : id? id : '',
        pollQuestion : location.state.pollQuestion,
        yesVotes: location.state.yesVotes,
        noVotes :location.state.noVotes
    }
    const green='#ACC779';
    const red='#DB6C79';
    const chartData = [
        {name:"yes",
            votes: data.yesVotes,
            fill:green,},
        {name:"no",
            votes: data.noVotes,
            fill: red,},];
    console.log(location.state)
    return (
        <div id = "container" className={"PollOnlineContainer"}>
            <div id={"question"} className={"pollOnlineQuestion"} >{data.pollQuestion}</div>
            <div><BarChart   layout="vertical" data={chartData}
                             margin={{top: 5, right: 30, left: 20, bottom: 5}}>

                <YAxis axisLine={false} tickLine={false} width={400} dataKey="name" type="category">
                </YAxis>
                <Bar dataKey="valuePre" fill="#00a0dc" label={'yes'}/>
                <Bar dataKey="valuePost" fill="#c7c7c7"  label={'no'}/>

            </BarChart></div>

        </div>
    )
}

export default PollResult;