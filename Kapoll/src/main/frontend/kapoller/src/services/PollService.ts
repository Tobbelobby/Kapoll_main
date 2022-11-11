import http from "../http-common";
import PollData from "../types/Poll";
import PollResultData from "../types/PollResult";

const getAll = async () => {
    return await http.get<Array<PollData>>("/Poll")
};

const get = async (id: string) => {
    return await http.get<PollData>(`/Poll/${id}`);
};
const axCreate = async (data: PollData) => {
    return await http.post<PollData>('/Poll', data)
}
const url = "http://localhost:8080/api"
const create = async (data: PollData) => {
    console.log('he')
    return await fetch(`${url}/Poll`, {
        method: "POST",
        headers: {'Content-type': 'application/json', "Access-Control-Allow-Headers" : "Content-Type", 'Access-Control-Allow-Origin': '*', "Access-Control-Allow-Methods": "OPTIONS,POST, PUT,GET"},
        body: JSON.stringify(data)
    })

};
const update = async (id: string, data: PollData) => {
    return await fetch(`${url}/Poll/${id}`, {
        method: 'PUT',
        headers: {'Content-type': 'application/json', "Access-Control-Allow-Headers" : "Content-Type", 'Access-Control-Allow-Origin': '*', "Access-Control-Allow-Methods": "OPTIONS,POST, PUT,GET"},
        body: JSON.stringify(data)
    })
};

const updateResults = async (id: string, data: Array<PollResultData>) => {
    const pollResults = {
        poll_results : data
    }
    console.log(JSON.stringify(pollResults))
    return await fetch(`${url}/Poll/${id}`, {
        method: 'PUT',
        headers: {'Content-type': 'application/json', "Access-Control-Allow-Headers" : "Content-Type", 'Access-Control-Allow-Origin': '*', "Access-Control-Allow-Methods": "OPTIONS,POST, PUT,GET"},
        body: JSON.stringify(pollResults)
    })
}

const remove = async (id: string) => {
    return await http.delete<any>(`/Poll/${id}`);
};

const PollService = {
    getAll,
    get,
    create,
    update,
    updateResults,
    remove,
    axCreate
};

export default PollService;