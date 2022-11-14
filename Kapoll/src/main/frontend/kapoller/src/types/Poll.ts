import PollResultData from "./PollResult";

export default interface PollData {
    id?: any |null|undefined,
    title: string,
    question: string,
    time: number,
    pollResults?: Array<PollResultData>
}