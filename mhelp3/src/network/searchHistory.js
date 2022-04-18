import {unique} from "../common/utils.js"
export function getSchoolSearchHistory() {
    var data = localStorage.getItem('schoolSearchHistory');
    if (data !== null) {
        return JSON.parse(data);
    }
    else {
        return [];
    }
}
export function setSchoolSearchHistory(data){
    var list = getSchoolSearchHistory();
    list=unique(list);
    list.push(data);
    localStorage.setItem('schoolSearchHistory', JSON.stringify(list));
}
export function removeSchoolSearchHistory() {
    var list = getSchoolSearchHistory();
    list=[];
    localStorage.setItem('schoolSearchHistory', JSON.stringify(list));
}
export function getHomeSearchHistory() {
    var data = localStorage.getItem('homeSearchHistory');
    if (data !== null) {
        return JSON.parse(data);
    }
    else {
        return [];
    }
}
export function setHomeSearchHistory(data){
    var list = getHomeSearchHistory();
    list=unique(list);
    list.push(data);
    localStorage.setItem('homeSearchHistory', JSON.stringify(list));
}
export function removeHomeSearchHistory() {
    var list = getHomeSearchHistory();
    list=[];
    localStorage.setItem('homeSearchHistory', JSON.stringify(list));
}
export function getTaskListSearchHistory() {
    var data = localStorage.getItem('taskListSearchHistory');
    if (data !== null) {
        return JSON.parse(data);
    }
    else {
        return [];
    }
}
export function setTaskListSearchHistory(data){
    var list = getTaskListSearchHistory();
    list=unique(list);
    list.push(data);
    localStorage.setItem('taskListSearchHistory', JSON.stringify(list));
}
export function removeTaskListSearchHistory() {
    var list = getTaskListSearchHistory();
    list=[];
    localStorage.setItem('taskListSearchHistory', JSON.stringify(list));
}