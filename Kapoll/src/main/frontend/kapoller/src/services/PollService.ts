import http from "../http-common";
import PollData from "../types/Poll";

const getAll = async () => {
    return await http.get<Array<PollData>>("/Poll")
};

const get = async (id: string) => {
    return await http.get<PollData>(`/Poll/${id}`);
};
const url = "http://localhost:8080/api"
const create = async (data: PollData) => {
    return await fetch(`${url}/Poll`, {
        method: "POST",
        headers: {'Content-type': 'application/json',},
        body: JSON.stringify(data)
    })

};
const update = async (id: string, data: PollData) => {
    return await fetch(`${url}/Poll/${id}`, {
        method: 'PUT',
        headers: {'Content-type': 'application/json',},
        body: JSON.stringify(data)
    })
};

const remove = async (id: string) => {
    return await http.delete<any>(`/Poll/${id}`);
};

const PollService = {
    getAll,
    get,
    create,
    update,
    remove,
};

export default PollService;