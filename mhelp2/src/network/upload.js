import { request } from "network/request";
export function uploadAvatar(token,avatar){
    return request({
        headers:{'Authorization': token},
        url:"/users/upload/image",
        method: 'post',
        data:{
            avatar:avatar
        }
    })
}
export function uploadImage(token,images){
    return request({
        headers:{'Authorization': token},
        url:'/upload',
        method:'post',
        data:{
            images:images
        }
    }) 
}
