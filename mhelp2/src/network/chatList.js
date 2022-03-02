import { saveHistory,ChatSnapShot} from "./app";
//获取聊天人员列表
export function getChatList() {
    var data = localStorage.getItem('chatList');
    if (data !== null) {
        return JSON.parse(data);
    }
    else {
        return [];
    }
}
//增加聊天人员列表
export function setChatList(data) {
    var list = getChatList();
    list.push(data);
    localStorage.setItem('chatList', JSON.stringify(list));
}
//删除聊天人员列表
export function removeChatList(index) {
    var list = getChatList();
    list.splice(index, 1);
    localStorage.setItem('chatList', JSON.stringify(list));
}
//获取聊天消息
export function getmsgContent(myId, friendId) {
    var chatKey = "chat-" + myId + "-" + friendId;
    var data = localStorage.getItem(chatKey);
    if (data !== null) {
        return JSON.parse(data);
    }
    else {
        return [];
    }
}
//保存聊天消息
export function setmsgContent(myId, friendId, msg, flag) {
    var singleMsg = new saveHistory(myId, friendId, msg, flag)
    var list = getmsgContent(myId, friendId);
    var chatKey = "chat-" + myId + "-" + friendId;
    list.push(singleMsg);
    localStorage.setItem(chatKey, JSON.stringify(list));
}
//获取聊天快照
export function getChatSnapShot(myId) {
    var chatKey = "chatSnapShot-" + myId;
    var data = localStorage.getItem(chatKey);
    if (data !== null) {
        return JSON.parse(data)
    }
    else {

        return [];
    }
}
//保存聊天快照
export function setChatSnapShot(myId, friendId, msg, isRead) {
    var chatKey = "chatSnapShot-" + myId;
    var data = localStorage.getItem(chatKey);
    var chatSnapShotList;
    if (data !== null) {
        chatSnapShotList = JSON.parse(data);
        for (var i=0; i < chatSnapShotList.length; i++) {
            if (chatSnapShotList[i].friendId == friendId) {
                chatSnapShotList.splice(i, 1);
                console.log( chatSnapShotList);
                break;
            }
        }
    }
    else {
        chatSnapShotList = [];
    }
    var singleMsg=new ChatSnapShot(myId, friendId, msg, isRead);
    chatSnapShotList.unshift(singleMsg);
    localStorage.setItem(chatKey,JSON.stringify(chatSnapShotList));
}
//保存未接受消息模型
// export function setNoReadMsg(myId,friendId){
//     var chatKey = "noSend-" + myId + "-" + friendId;
//     console.log(chatKey)
//     var data = localStorage.getItem(chatKey);
//     var noReadMsgList;
//     if (data !== null) {
//         noReadMsgList .push(chatKey,JSON.parse(data));
//     }                                                                                                                    
//     else {
//         noReadMsgList = [];
//     }
// }