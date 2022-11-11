import PollData from "./Poll";

export default interface KapollerData {
    id: string | null,
    firstName?: string | null,
    lastName?: string | null,
    userName: string | null,
    polls?: Array<PollData>
}