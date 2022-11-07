import PollData from "./Poll";

export default interface PollResultData {
    id? : any | null,
    utilDate: Date,
    yesVotes: number,
    noVotes: number,
    pollId?: number,

}