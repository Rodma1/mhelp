import request from '@/request'


export function getAllTagsDetail() {
  return request({
    url: '/tags/detail',
    method: 'get',
  })
}
export function getAllTags() {
  return request({
    url: '/tags',
    method: 'get',
  })
}
