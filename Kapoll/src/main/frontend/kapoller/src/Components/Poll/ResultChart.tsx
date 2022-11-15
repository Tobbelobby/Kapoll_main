import React, {useState} from 'react';
import {BarChart,Bar,ResponsiveContainer}from'recharts';
import PollOnlineProps from"../../types/PollOnlineProps";
import PollResultData from"../../types/PollResult";
import {useParams} from "react-router-dom";
import Poll from "./Poll";
import PollResult from "../VoteOnPoll/PollResult";

const green='#ACC779';
const red='#DB6C79';


const ResultCharts: (props: { yesVotes: number; noVotes: number }) => JSX.Element = (props: {yesVotes: number, noVotes: number}) => {
    const {id} = useParams();
    const d = {
        pollId: id? id : '',
        noVotes : props.noVotes,
        yesVotes : props.yesVotes,
    }

    const data = [
        {name:"yes",
        votes: 12,
        fill:green,},
        {name:"no",
        votes: 4,
        fill: red,},];

    return (
        <ResponsiveContainer width="100%" height="100%">
            <BarChart width={150} height={40} data={data}>
                <Bar dataKey="Votes"/>
            </BarChart>
        </ResponsiveContainer>
    )
}

export default ResultCharts;