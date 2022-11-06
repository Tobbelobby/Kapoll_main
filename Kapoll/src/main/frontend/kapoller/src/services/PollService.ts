import http from "../http-common";
import PollData from "../types/Poll";

const getAll = () => {
    return http.get<Array<PollData>>("/Poll")
};

const get = (id: string) => {
    return http.get<PollData>(`/Poll/${id}`);
};

const create = (data: PollData) => {
    return http.post<PollData>("/Poll", data)
};
const update = (id: string, data: PollData) => {
    return http.put<any>(`/Poll/${id}`, data);
};

const remove = (id: string) => {
    return http.delete<any>(`/Poll/${id}`);
};

const PollService = {
    getAll,
    get,
    create,
    update,
    remove,
};

export default PollService;