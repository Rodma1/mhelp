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
export function regist(account,nickname,password,school) {
    const data={
        account,
        nickname,
        password,
        school
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
        url:'/users/userInfo',
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
export function updateUserInfo(token,data){
    return request({
        headers: {'Authorization': token},
        url: '/users/update/userInfo',
        method:'post',
        data:{
            nickname:data.nickname,
            avatar:data.avatar,
            email:data.email,
            beforePssword:data.oldPassword,
            password:data.newPassword,
            school:data.school
        }
    })
}