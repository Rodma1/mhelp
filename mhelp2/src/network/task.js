import { request } from "network/request.js"
export function getTask(page) {
    // console.log(page)
    return request({
        url: "/tasks",
        method: 'post',
        data: {
            page: page.pageNumber,
            pageSize: page.pageSize,
        }
    })
}
export function getCategory() {
    return request({
        url: '/categorys',
        method: 'get',
    })
}
export function getTags() {
    return request({
        url: '/tags',
        method: 'get',
    })
}

export function getuaccepttasks(token,page) {
    return request({
        headers: { 'Authorization': token },
        url: '/tasks/uaccepttasks',
        method: 'post',
        data: {
            page: page.pageNumber,
            pageSize: page.pageSize
        }
    })
}
export function publishTask(token, params) {
    return request({
        headers: { 'Authorization': token },
        url: '/tasks/publish',
        method: 'post',
        data: {
            title: params.title,
            body: {
                content: "ww",
                contentHtml: "<p>ww</p >↵"
            },
            category: {
                id: 2,
                avatar: "",
                categoryName: params.category
            },
            summary: params.summary,
            tags: [
                {
                    "id": 5
                },
                {
                    "id": 6
                }
            ],
            images: params.images,
            publishSchoolName: "运城学院"
        }
    })
}

export function archive(token, status) {
    return request({
        headers: { 'Authorization': token },
        url: '/tasks/listtasks',
        method: 'post',
        data: {
            status: status
        }
    })
}
//接任务
export function acceptTask(token,id) {
    return request({
        headers: { 'Authorization': token },
        url: 'tasks/accepttask/'+id,
        method: 'post',
    })
}
export function schools() {
    return request({
        url: "/schools",
        method: 'get'
    })
}
//查找任务
export function searchTask(data) {
    return request({
        url: "/tasks/taskbykeys",
        method: 'post',
        data: {
            page: data.page,
            pageSize: data.pageSize,
            words: data.words,
            school: data.schoolid,

        }
    })
}

export function searchNoTask(data) {
    return request({
        url: 'tasks/taskbynostatus',
        method: 'post',
        data: {
            page: data.page,
            pageSize: data.pageSize,
            words: data.words,
            schoolid: data.schoolid,
        }
    })
}
//用户未读消息
export function noReadMsg(acceptUserId) {
    // console.log(acceptUserId)
    return request({
        url: "/chat/nread",
        method: 'post',
        params: {
            acceptUserId: acceptUserId
        }
    })
}
//我发的任务
export function getPublishList(token,page){
    return request({
        headers: { 'Authorization': token },
        url:"/tasks/usertasks",
        method:"post",
        data: {
            page: page.pageNumber,
            pageSize: page.pageSize
        }
    })
}
