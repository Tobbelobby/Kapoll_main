import http from "../http-common";
import KapollerData from "../types/Kapoller";
import PollData from "../types/Poll";

const getAll = () => {
    return http.get<Array<KapollerData>>("/Kapoller")
};

const get = (id: string) => {
    return http.get<KapollerData>(`/Kapoller/${id}`);
};

const getUserByUsername = async (username: string) => {
    return http.get<KapollerData>(`${url}/Kapoller/username/${username}`);
}

const existsAccount = async (data: string) =>{
    return (http.get<KapollerData>(`/Kapoller/check/${data}`));
}

const url = "http://localhost:8080/api"
const create = async (data: KapollerData) => {
    return await fetch(`${url}/Kapoller`, {
        method: "POST",
        headers: {'Content-type': 'application/json', "Access-Control-Allow-Headers" : "Content-Type", 'Access-Control-Allow-Origin': '*', "Access-Control-Allow-Methods": "OPTIONS,POST, PUT,GET"},
        body: JSON.stringify(data)
    })

};
const update = (id: number, data: KapollerData) => {
    return http.put<any>(`/Kapoller/${id}`, data);
};

const addPoll = async (id: string, data: Array<PollData>) => {
    const pollsToAdd = {
        polls : data
    }
    return await fetch(`${url}/Kapoller/${id}`, {
        method: 'PUT',
        headers: {'Content-type': 'application/json', "Access-Control-Allow-Headers" : "Content-Type", 'Access-Control-Allow-Origin': 'http://localhost:8080/', "Access-Control-Allow-Methods": "OPTIONS,POST, PUT,GET"},
        body: JSON.stringify(pollsToAdd)
    })
}

const remove = (id: number) => {
    return http.delete<any>(`/Kapoller/${id}`);
};

const KapollerService = {
    getAll,
    get,
    create,
    update,
    remove,
    existsAccount,
    addPoll,
    getUserByUsername
};

export default KapollerService;