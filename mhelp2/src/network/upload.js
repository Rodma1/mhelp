import { request } from "network/request";
export function uploadAvatar(token, avatar) {
    console.log(token, avatar)
    return request({
        headers: { 'Authorization': token },
        url: "/users/upload/image",
        method: 'post',
        data: {
            "file": avatar
        }
    })
}
export function uploadImage(token, images) {
    return request({
        headers: {
            'Authorization': token,
            'processData': false,
            // 'contentType': false,
        },
        url: '/upload',
        method: 'post',
        // contentType:false,
        data: {
            images: images
        }
    })
}
