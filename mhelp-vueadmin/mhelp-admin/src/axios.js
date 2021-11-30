import axios from "axios";
import Element from 'element-ui'
import router from "./router";

axios.defaults.baseURL = "http://101.35.145.209:8889"

//创建请求
const request = axios.create({
  timeout: 5000,
  headers: {
    'Content-Type': 'application/json; charset=utf-8'
  }
})
request.interceptors.request.use(config => {
  config.headers['Authorization'] = localStorage.getItem("token") // 请求头带上token
  return config;
})
//响应请求
request.interceptors.response.use(response => {
    let res = response.data;
    // console.log("response")
    // console.log(res)
    if (res.code == 200) {
      //返回请求到的数据
      return response;
    } else {
      //返回错误信息
      Element.Message.error(res.msg ? res.msg : '系统异常！', {duration: 3 * 1000})
      return Promise.reject(response.data.msg)
    }
  }, error => {
    console.log(error)
    //如果是数据错误
    if (error.response.data) {
      error.message = error.response.data.msg
    }
    //如果是登录状态是401错误就没有权限重新登录
    if (error.response.status === 401) {
      router.push("/login")
    }
    Element.Message.error(error.message, {duration: 3 * 1000})
    //返回错误信息
    return Promise.reject(error)
  }
)
export default request
