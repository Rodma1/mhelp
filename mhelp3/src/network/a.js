export default window.CHAT = {
    //定义一个变量
    socket: null,
    //初始化
    init: function () {
        //判断浏览器是否支持websocket
        if (window.WebSocket) {
            //如果当前websocket状态已经连接，无需重复初始化websocket
            if (CHAT.socket != null && CHAT.socket != undefined && CHAT.socket.readyState == WebSocket.OPEN) {
                return false;
            }
            //创建websocket 对象
            try {
                //创建websocket对象101.35.145.209，192.168.0.106
                CHAT.socket = new WebSocket("ws://101.35.145.209:8089/ws");
                CHAT.socket.onopen = function () {
                    console.log( CHAT.socket.readyState )
                    console.log("连接建立成功...");
                    //    获取当前用户id
                    var myId = "1456905513402937346"
                    //    构建chatMsg聊天消息
                    var chatMsgParam = new app.ChatMsgParam(myId, null, null, null);
                    //    第一次(或重连)初始化连接
                    var dataContent = new app.DataContent(app.CONNECT, chatMsgParam, null);
                    //    转变为string类型发送到服务器
                    CHAT.chats(JSON.stringify(dataContent));
                    
                },
                    CHAT.socket.onclose = function () {
                        console.log("连接关闭...");
                    },
                    CHAT.socket.onerror = function () {
                        console.log("发生错误...");
                    },
                    CHAT.socket.onmessage = function (e) {
                        console.log("接受到消息：" + e.data);
                        var dataConetent = JSON.parse(e.data);
                        //获取浏览器获取到的消息
                        var receiveMsg = document.getElementById("receiveMsg");
                        var html = receiveMsg.innerHTML;//获取本对象原有的内容
                        //嵌入新的内容
                        receiveMsg.innerHTML = html + "<br/>" + e.data;
                        //接到到消息之后，对消息进行签收
                        var dataContentSign = new app.DataContent(app.SIGNED, null, dataConetent.chatMsgParam.msgId);
                        //发送数据
                        CHAT.chats(JSON.stringify(dataContentSign));
                        console.log("消息被签收")
                    }
            } catch (e) {
                console.log(e.message);
            }
        } else {
            alert("浏览器不支持websocket协议...");
        }
    },
    //聊天
    chat: function () {
        //如果当前WebSocket 状态已经连接，无需重复初始化WebSocket
        if (CHAT.socket != null && CHAT.socket != undefined && CHAT.socket.readyState == WebSocket.OPEN) {
            //    获取当前用户id
            var myId = "1456905513402937346"
            //接受者id
            var youId = "1460082511528902657"
            //获取发送消息框中所输入的内容
            var msg = document.getElementById("msgContent");
            console.log(msg.value)
            //    构建chatMsg聊天消息
            var chatMsgParam = new ChatMsgParam(myId, youId, msg.value, null);
            //    第一次(或重连)初始化连接
            var dataContent = new DataContent(CHAT, chatMsgParam, null);
            //将客户输入的消息进行发送
            CHAT.socket.send(JSON.stringify(dataContent));
        } else {
            CHAT.init();
        }
    },
    //聊天
    chats: function (msg) {
        //如果当前WebSocket 状态已经连接，无需重复初始化WebSocket
        if (CHAT.socket != null && CHAT.socket != undefined && CHAT.socket.readyState == WebSocket.OPEN) {
            //将客户输入的消息进行发送
            CHAT.socket.send(msg);
        } else {
            CHAT.init();
        }
    }
};
//进入页面就初始化连接
// CHAT.init();