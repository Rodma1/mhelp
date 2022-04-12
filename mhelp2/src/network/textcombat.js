import axios from 'axios'
export function textCombat(config){
    const instance = axios.create({
        baseURL: 'http://127.0.0.1:8080/student',
        timeout: 5000
      })
    return instance(config)
} 