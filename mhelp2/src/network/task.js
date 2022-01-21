import {request} from "network/request.js"
export function getTask(page){
    console.log(page)
    return request({
        url:"/tasks",
        method:'post',
        data: {
            page: page.pageNumber,
            pageSize: page.pageSize,
            // name: page.name,
            // sort: page.sort,
            // year: query.year,
            // month: query.month,
            // tagId: query.tagId,
            // categoryId: query.categoryId
          }

    })
}
export function getCategory(){
    return request({
        url:'/categorys',
        method:'get',
    })
}
export function getTags(){
    return request({
        url:'/tags',
        method:'get',
    })
}

export function getuaccepttasks(page){
    return request({
        url:'/tasks/uaccepttasks',
        method:'post',
        data:{
            page:page.pageNumber,
            pageSize:page.pageSize
        }
    })
}
export function publishTask(token,params){
    return request({
        headers: {'Authorization': token},
        url:'/tasks/publish',
        method:'post',
        data:{
            title:params.title,
            body:{
                content:params.content,
                contetnHtml:''
            },
            category:{id:2,avtar:'',categoryName:params.category},
            summary:params.remark,
            tags:[{id:2}]
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
export function archive(token,status){
    return request({
        headers:{'Authorization': token},
        url:'/tasks/listtasks',
        method:'post',
        data:{
            status:status
        }
    })
}
export function acceptTask(token){
    return request({
        headers:{'Authorization': token},
        url:'tasks/accepttask/1',
        method:'post',
    })
}