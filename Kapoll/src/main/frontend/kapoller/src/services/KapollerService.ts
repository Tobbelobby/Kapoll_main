import http from "../http-common";
import KapollerData from "../types/Kapoller";
import PollData from "../types/Poll";

const getAll = () => {
    return http.get<Array<KapollerData>>("/Kapoller")
};

const get = (id: number) => {
    return http.get<KapollerData>(`/Kapoller/${id}`);
};

const url = "http://localhost:8080/api"

const existAccount = async (data: string | null) =>{
    return (!(http.get<KapollerData>(`/Kapoller/${data}`)== null));
}
const create = async (data: { id: string; userName: string | null }) => {
    console.log('kapoller')
    return await fetch(`${url}/Kapoller`, {
        method: "POST",
        headers: {'Content-type': 'application/json', "Access-Control-Allow-Headers" : "Content-Type", 'Access-Control-Allow-Origin': '*', "Access-Control-Allow-Methods": "OPTIONS,POST, PUT,GET"},
        body: JSON.stringify(data)
    })

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
    existAccount,
};

export default KapollerService;