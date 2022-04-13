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

export function getuaccepttasks(token, page) {
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
    console.log(params)
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
            category: params.category,
            summary: params.summary,
            tags:params.tags,
            images: params.images,
            publishSchoolName: params.school,
            money:params.price
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
export function acceptTask(token, id,money) {
    return request({
        headers: { 'Authorization': token },
        url: 'tasks/accepttask/' + id,
        method: 'post',
        data:{
            money
        }
    })
}
export function schools() {
    return request({
        url: "/schools",
        method: 'get'
    })
}
//查找任务
export function searchTasks(data) {
    return request({
        url: "/tasks/taskbynostatus",
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
export function getPublishList(token, page) {
    return request({
        headers: { 'Authorization': token },
        url: "/tasks/usertasks",
        method: "post",
        data: {
            page: page.pageNumber,
            pageSize: page.pageSize
        }
    })
}
//收藏
export function goCollect(token, id) {
    return request({
        headers: { 'Authorization': token },
        url: "/users/start/" + id,
        method: "post",
    })
}
export function getCollectList(token) {
    return request({
        headers: { 'Authorization': token },
        url: "/tasks/getStart",
        method: "get",
    })
}
//取消收藏
export function delCollect(token,id){
    return request({
        headers: { 'Authorization': token },
        url: "/tasks/delStart/"+id,
        method: "post",
    })
}
export function finishTask(token,id) {
    return request({
        headers: { 'Authorization': token },
        url: "/tasks/altertask",
        method: "post",
        data: {
            id: id,
            status: 2
        }
    })
}
export function delTask(token,id){
    return request({
        headers: { 'Authorization': token },
        url: "/tasks/delTask/"+id,
        method: "post",
    })
}
//类型筛选
export function categoryTask(params){
    return request({
        url:"/tasks",
        method:"post",
        data:{
            params
        }
    })
}
