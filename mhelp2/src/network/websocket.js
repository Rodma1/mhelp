class cwebsocket{
    constructor(url){
        this.socket=new WebSocket(url);
        this.waitingList=[],
        this.listenerList=[],
        this.socket.onopen=(...args)=>{
            this.checkWaitingList()
        }
        this.socket.onmessage=(...args)=>{
            this.s2cmessage(...args)
        }
    }
    //实例化后就可能会调用发送消息，但是此时连接可能未建立完成，所以用一个待发送消息列表将未发送的消息保存下来，等连接成功后发送
    checkWaitingList(){
        this.waitingList.forEach(this.c2smesssage)
    }
    c2smesssage(data){
        if(this.socket.readyState!==this.socket.OPEN){//判断是否正常状态，readyState表示socket的当前状态码
            this.waitingList.push(data);
            return
        }
        //因为收发的消息是字符串格式，所以需要用JSON.stringify转为字符串
        let msg=JSON.stringify(data);
        this.socket.send(msg)
    }
    s2cmessage(event){
        let msg=JSON.stringify(event.data)
        this.listenerList.forEach(func=>{
            func(msg)
        })
    }
    addListener(func){
        this.listenerList.push(func)
    }
    removeListener(){
        let index=this.listenerList.indexOf(func);
        index!=-1&&this.listenerList.splice(index,1);
    }
    close(){
        if(this.socket){
            this.listenerList=[];
            this.socket.onopen=null;
            this.socket.onmessage=null;
            this.socket.close()
            this.socket=null;
        }
    }
}
export class cwebsocket{}