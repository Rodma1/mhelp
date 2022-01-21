import axios from 'axios'
import store from '@/store'
import {Message} from 'element-ui'
import {getToken} from "network/token.js"
export function request(config) {
  // 1.创建axios的实例
  const instance = axios.create({
    baseURL: 'http://101.35.145.209:8888/',
    timeout: 5000
  })

  // 2.axios的拦截器
  // 2.1.请求拦截的作用
  instance.interceptors.request.use(config => {
    if (store.state.token) {
      config.headers['Oauth-Token'] = getToken()
    }
    return config
  }, error => {
    Promise.reject(error)
  })
  // respone拦截器
  instance.interceptors.response.use(
    response => {
      //全局统一处理 Session超时
      if (response.headers['session_time_out'] === 'timeout') {
        store.dispatch('fedLogOut')
      }

      const res = response.data;
      // console.log(res)
      //0 为成功状态
      if (res.code !== 200) {

        //90001 Session超时
        if (res.code === 90001) {
          return Promise.reject('error');
        }
 
        //20001 用户未登录
        if (res.code === 90002) {

          Message({
            type: 'warning',
            showClose: true,
            message: '未登录或登录超时，请重新登录哦'
          })

          return Promise.reject('error');
        }

        //70001 权限认证错误
        if (res.code === 70001) {
          console.info("权限认证错误")
          Message({
            type: 'warning',
            showClose: true,
            message: '你没有权限访问哦'
          })
          return Promise.reject('error');
        }

        return Promise.reject(res.msg);
      } else {
        return response.data;
      }
    },
    error => {
      Message({
        type: 'warning',
        showClose: true,
        message: '连接超时'
      })
      return Promise.reject(error)
    })
  // 3.发送真正的网络请求
  return instance(config)
}
