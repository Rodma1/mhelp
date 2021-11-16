import request from '@/request'


export function getAllCategorysDetail() {
  return request({
    url: '/categorys/detail',
    method: 'get',
  })
}
export function getAllCategorys() {
  return request({
    url: '/categorys',
    method: 'get',
  })
}
