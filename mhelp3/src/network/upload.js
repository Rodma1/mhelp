import { request } from "network/request";
export function uploadAvatar(token, file) {
    console.log(token, file)
    return request({
        headers: { 
            'Authorization': token,
            'Content-Type':'multipart/form-data'
         },
        url: "/users/upload/image",
        method: 'post',
        data: file
    })
}
export function uploadImage(token, images) {
    return request({
        headers: { 
            'Authorization': token,
            'Content-Type':'multipart/form-data'
         },
        url: '/upload',
        method: 'post',
        data:images
    })
}
