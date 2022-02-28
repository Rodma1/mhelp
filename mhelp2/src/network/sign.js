import { request } from "network/request";
export function listsign(token){
    return request({
        url:'sign/listsign',
        headers:{'Authorization': token},
        method:'get',
    })
}
export function sign(token){
    return request({
        url:'/sign',
        headers:{'Authorization': token},
        method:'post',
    })
} 
export function todaysign(token){
    return request({
        url:'sign/todaysign',
        headers:{'Authorization': token},
        method:'get',
    })
} 