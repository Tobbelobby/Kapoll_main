import PollData from "../../types/Poll";

interface dweetResult {
    pollTitle: string,
    yesVotes: number,
    noVotes: number,
    timePublished: number
}

const sendResults = async (data: dweetResult) => {
    return await fetch(`https://dweet.io/dweet/for/Kapoll-results`, {
            method: "POST",
            body: JSON.stringify(data),
            headers: {
                'content-type': 'application/json',
                'Authorization': 'Client-ID [my-client-id]'
            }
        }
    ).then((response: any) => {
        response.header('Access-Control-Allow-Origin', 'https://dweet.io/dweet/for/Kapoll-results');
        response.header('Access-Control-Allow-Methods', 'POST,OPTIONS');
        response.header('Access-Control-Allow-Headers', 'Content-Type, Authorization, Content-Length, X-Requested-With, Accept')
        console.log(response);
    })
        .catch((e: Error) => {
            console.log(e);
        });
};

const postToDweet = {
    sendResults
};
export default postToDweet;