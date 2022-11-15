import PollData from "./Poll";

export default interface PollResultData {
    id? : any | null,
    utilDate: Date,
    yesVote: number,
    noVote: number

}