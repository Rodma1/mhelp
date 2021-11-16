import request from '@/request'


export function getTasks(query, page) {
  return request({
    url: '/tasks',
    method: 'post',
    data: {
      page: page.pageNumber,
      pageSize: page.pageSize,
      name: page.name,
      sort: page.sort,
      year: query.year,
      month: query.month,
      tagId: query.tagId,
      categoryId: query.categoryId
    }
  })
}
//获取用户发布的任务
export function getUserTasks(query, page,token) {
  console.log(token)
  return request({
    headers: {'Authorization': token},
    url: '/tasks/usertasks',
    method: 'post',
    data: {
      page: page.pageNumber,
      pageSize: page.pageSize,
      name: page.name,
      sort: page.sort,
      year: query.year,
      month: query.month,
      tagId: query.tagId,
      categoryId: query.categoryId
    }
  })
}
//获取用户接受的任务
//获取用户发布的任务
export function getUserAccTasks(query, page,token) {
  console.log(token)
  return request({
    headers: {'Authorization': token},
    url: '/tasks/uaccepttasks',
    method: 'post',
    data: {
      page: page.pageNumber,
      pageSize: page.pageSize,
      name: page.name,
      sort: page.sort,
      year: query.year,
      month: query.month,
      tagId: query.tagId,
      categoryId: query.categoryId
    }
  })
}
//任务归档
export function listArchives(){
  return request({
    url:'/tasks/listtasks',
    method:'post'
  })
}

//查看任务
export function viewTask(id) {
  return request({
    url: `/tasks/view/${id}`,
    method: 'post'
  })
}
//发布任务
export function publishTask(task,token) {
  return request({
    headers: {'Authorization': token},
    url: '/tasks/publish',
    method: 'post',
    data: task
  })
}
//接受任务
//发布任务
export function acceptTask(id,token) {
  return request({
    headers: {'Authorization': token},
    url: `/tasks/accepttask/${id}`,
    method: 'post'
  })
}
