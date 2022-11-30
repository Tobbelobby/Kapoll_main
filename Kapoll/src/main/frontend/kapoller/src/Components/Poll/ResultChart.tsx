import React from 'react';
import {BarChart,Bar,ResponsiveContainer}from'recharts';
import {useLocation, useParams} from "react-router-dom";

const green='#ACC779';
const red='#DB6C79';

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