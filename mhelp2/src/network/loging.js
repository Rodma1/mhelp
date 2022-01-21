import { request } from "network/request";
export function loging(account, password) {
    const data = {
        account,
        password
    }
    return request({
        url: '/login',
        method: 'post',
        data,
    })
}
export function regist(account,nickname,password) {
    const data={
        account,
        nickname,
        password
    }
    return request({
        url: '/register',
        method:'post',
        data
    })
}
export function getUserInfo(token){
    return request({
        headers: {'Authorization': token},
        url:'/users/currentUser',
        method:'get', 
    })
}
export function logout(token){
    return request({
        headers: {'Authorization': token},
        url: '/logout',
        method: 'get'
    })
}