export  function getToken(){
    return localStorage.token;
}
export function setToken(token){
    return localStorage.token=token;
}
export function removeToken(){
    return localStorage.removeItem('token')
}
export function getCheck(){
    return localStorage.isCheck
}
export function setCheck(isCheck){
    return localStorage.isCheck=isCheck
}