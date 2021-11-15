import request from '@/request'


export function getAllCategorysDetail() {
  return request({
    url: '/categorys/detail',
    method: 'get',
  })
}
