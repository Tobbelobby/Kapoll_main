import http from "../http-common";
import KapollerData from "../types/Kapoller";

const getAll = () => {
    return http.get<Array<KapollerData>>("/Kapoller")
};

const get = (id: number) => {
    return http.get<KapollerData>(`/Kapoller/${id}`);
};
const existsAccount = async (data: string | null) =>{
    return (http.get<KapollerData>(`/Kapoller/check/${data}`));
}
const create = (data: KapollerData) => {
    return http.post<KapollerData>("/Kapoller", data)
};
const update = (id: number, data: KapollerData) => {
    return http.put<any>(`/Kapoller/${id}`, data);
};

const remove = (id: number) => {
    return http.delete<any>(`/Kapoller/${id}`);
};

const KapollerService = {
    getAll,
    get,
    create,
    update,
    remove,
    existsAccount
};

export default KapollerService;