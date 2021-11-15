import request from '@/request'


export function getAllTagsDetail() {
  return request({
    url: '/tags/detail',
    method: 'get',
  })
}
