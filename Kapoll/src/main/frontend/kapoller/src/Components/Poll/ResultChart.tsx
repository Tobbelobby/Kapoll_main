import React, {useState} from 'react';
import {BarChart,Bar,ResponsiveContainer}from'recharts';
import PollOnlineProps from"../../types/PollOnlineProps";
import PollResultData from"../../types/PollResult";
import {useParams} from "react-router-dom";

const green='#ACC779';
const red='#DB6C79';


const ResultCharts: React.FC = () => {
    const {id} = useParams();
    const [noVotes, setNoVotes] = useState<number>(0);
    const [yesVotes, setYesVotes] = useState<number>(0);
    const d = {
        pollId: id? id : '',
        noVotes : noVotes,
        yesVotes : yesVotes,
    }

    const data = [
        {name:"yes",
        votes: d.yesVotes,
        fill:green,},
        {name:"no",
        votes: d.noVotes,
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