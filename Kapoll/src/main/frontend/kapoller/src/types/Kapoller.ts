import PollData from "./Poll";

export default interface KapollerData {
    id: number | null,
    firstName: string,
    lastName: string,
    userName: string,
    password: string,
    polls: Array<PollData>
}