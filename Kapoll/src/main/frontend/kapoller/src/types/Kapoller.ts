import PollData from "./Poll";

export default interface KapollerData {
    id?: number | null,
    firstName: string,
    lastName: string,
    userName: string,
    polls?: Array<PollData>
}