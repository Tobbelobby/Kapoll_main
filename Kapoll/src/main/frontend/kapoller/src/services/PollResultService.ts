import PollResultData from "../types/PollResult";
import http from "../http-common"
import PollData from "../types/Poll";


const getAll = async () => {
    return await http.get<Array<PollResultData>>("/PollResult");
};
const get = async (id: string) => {
    return await http.get<PollResultData>(`/PollResult/${id}`);
};
const url = "http://localhost:8080/api"
const create = async (data: PollResultData) => {
    return await fetch(`${url}/PollResult`, {
        method: "POST",
        headers: {'Content-type': 'application/json',},
        body: JSON.stringify(data)
    })
};

const update = async (id: string, data: PollResultData) => {
    return await fetch(`${url}/PollResult/${id}`, {
        method: 'PUT',
        headers: {'Content-type': 'application/json',},
        body: JSON.stringify(data)
    })
};

const remove = async (id: string) => {
    return await http.delete<any>(`/PollResult/${id}`);
};

const PollResultService = {
    getAll,
    get,
    create,
    update,
    remove,
};

export default PollResultService;