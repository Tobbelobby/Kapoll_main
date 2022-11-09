import React, {useEffect, useState} from "react";
import PollOnline from "../VoteOnPoll/PollOnline";
import {useNavigate, useParams} from "react-router-dom";
import PollOnlineProps from "../../types/PollOnlineProps";
import PollService from "../../services/PollService";
import PollResultService from "../../services/PollResultService";
import PollResultData from "../../types/PollResult";


const StartPoll: React.FC = () => {
    const {id} = useParams();
    let navigate = useNavigate();
    const [question, setQuestion] = useState<string>("");
    const [noVotes, setNoVotes] = useState<number>(0);
    const [yesVotes, setYesVotes] = useState<number>(0);
    const [time, setTime] = useState<number>(0);
    const [pollResults, setPollResults] = useState<Array<PollResultData>>([])
    const initialPollOnlineState = {
        pollId: id? id : '',
        pollQuestion: question,
        noVotes: noVotes,
        yesVotes: yesVotes,
        timePublished: time,
        pollResults: pollResults
    }
    const [currentPoll, setCurrentPoll] = useState<PollOnlineProps>(initialPollOnlineState);
    const getPollData = (id: string) => {
        console.log(id)
        PollService.get(id)
            .then((response: any) => {
                setQuestion(response.data.question);
                setTime(response.data.time);
                console.log(response)
                if( (response.data.poll_results)) {
                    setPollResults(response.data.poll_results)
                    setNoVotes(countVotes(response.data.poll_results, 'no'))
                    setYesVotes(countVotes(response.data.poll_results, 'yes'))}
                console.log(response.data.question);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    };

    useEffect(() => {
        if (id)
            getPollData(id);
    }, [id]);


    return <PollOnline pollId={id? id : ""} noVotes={noVotes} pollQuestion={question} timePublished={time} yesVotes={yesVotes}/>
}

const countVotes = (pollResultList: Array<PollResultData>, yesOrNo: 'yes'|'no') => {
    const votes = pollResultList.map(pollResult => (yesOrNo === 'yes' ? pollResult.yesVote: pollResult.noVote))
    const sumVotes = votes.reduce((a,b)=> a+b, 0)
    return sumVotes
}

export default StartPoll;