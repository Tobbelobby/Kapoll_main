import React, {useState} from 'react';
import {BarChart,Bar,ResponsiveContainer}from'recharts';
import PollOnlineProps from"../../types/PollOnlineProps";
import PollResultData from"../../types/PollResult";
import {useLocation, useParams} from "react-router-dom";
import Poll from "./Poll";
import PollResult from "../VoteOnPoll/PollResult";

const green='#ACC779';
const red='#DB6C79';

/*
const ResultCharts: (props: { yesVotes: number; noVotes: number }) => JSX.Element = (props: {yesVotes: number, noVotes: number}) => {
    const {id} = useParams();
    const d = {
        pollId: id? id : '',
        noVotes : props.noVotes,
        yesVotes : props.yesVotes,
    }*/
const ResultCharts= () => {
    const location = useLocation();
    const {id} = useParams();

    const parsedData = {
        pollId : id? id : '',
        pollQuestion : location.state.pollQuestion,
        yesVotes: location.state.yesVotes,
        noVotes :location.state.noVotes
    }
    const data = [
        {name:"yes",
        votes: parsedData.yesVotes,
        fill:green,},
        {name:"no",
        votes: parsedData.noVotes,
        fill: red,},];

    return (
        <div className={"container"}>
            <div className={"pollResultQuestion"} >{parsedData.pollQuestion}</div>
            <ResponsiveContainer className={"chartContainer"} width="60%" height="60%">
                <BarChart width={150} height={40} data={data}>
                    <Bar dataKey="votes"/>
                </BarChart>
            </ResponsiveContainer>
        </div>

    )
}

export default ResultCharts;