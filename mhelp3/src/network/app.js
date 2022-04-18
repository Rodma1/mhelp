// window.app={
//     //和后段枚举一一对应
//     CONNECT: 1,    // "第一次(或重连)初始化连接"
//     CHAT:2,//聊天消息
//     SIGNED: 3,     // "消息签收"),
//     /**
//      * 和后端的ChatMsg 聊天模型对象保持一致
//      * @param {Object} senderId
//      * @param {Object} receiverId
//      * @param {Object} msg
//      * @param {Object} msgId
//      */
//     ChatMsgParam: function(senderId,receiverId,msg,msgId){
//         this.senderId = senderId;
//         this.receiverId = receiverId;
//         this.msg = msg;
//         this.msgId = msgId; 
//     },
//     /**构建消息DataContent模型对象
//      * @param {Object} action
//      * @param {Object} chatMsgParam
//      * @param {Object} extand
//      */
//     DataContent: function(action,chatMsgParam,extand){
//         this.action = action;
//         this.chatMsgParam = chatMsgParam;
//         this.extand = extand;
//     }, 

// }
//消息模型
export  class ChatMsgParam {
    constructor(senderId, receiverId, msg, msgId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.msg = msg;
        this.msgId = msgId;
    }
}
export  class DataContent {
    constructor(action, chatMsgParam, extand) {
        this.action = action;
        this.chatMsgParam = chatMsgParam;
        this.extand = extand;
    }
}
//保存聊天记录模型
export class saveHistory{
    constructor(myId,friendId,msg,flag){
        this.myId=myId;
        this.friendId=friendId;
        this.msg=msg;
        this.flag=flag
    }
}
//聊天快照
export class ChatSnapShot{
    constructor(myId,friendId,msg,isRead){
        this.myId=myId;
        this.friendId=friendId;
        this.msg=msg;
        this.isRead=isRead
    }
}
//未发送消息模型
export class noReadMsg{
    constructor(myId,friendId,msg,isRead){
        this.msgId=myId,
        this.friendId=friendId,
        this.msg=msg,
        this.isRead=isRead
    }
}