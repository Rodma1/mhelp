<template>
  <div class="chat">
    <chat-nav-bar class="navbar">
      <div slot="left" @click="goback">
        <img src="@/assets/img/example/back.png" alt="" />
      </div>
      <div slot="center">{{ this.$store.state.currentUser.nickname }}</div>
    </chat-nav-bar>
    <scroll class="contents" ref="scroll" :probeType="3">
      <chat-list
        class="chatList"
        ref="chatList"
        :messageList="messageList"
        @click.native="allHide"
      ></chat-list>
    </scroll>
    <chat-tab-bar
      class="tabBar2"
      @send="send"
      v-if="isActive || isShow"
      @showEmojis="showEmojis"
      ref="chatTabBar"
      @hide="hide"
      :isActiveEmojis="isActive"
      @showMore="showMore"
    ></chat-tab-bar>
    <chat-tab-bar
      class="tabBar"
      @send="send"
      @showEmojis="showEmojis"
      ref="chatTabBar"
      @showMore="showMore"
    ></chat-tab-bar>
    <choose-more class="chooseMore" v-if="isShow"></choose-more>
    <emojis class="emojis" ref="emojis" v-if="isActive"></emojis>
  </div>
</template>
<script>
import chatNavBar from "components/common/navbar/navbar.vue";
import chatTabBar from "views/message/componentsChildren/chatTabBar.vue";
import chatList from "views/message/componentsChildren/chatList.vue";
import emojis from "views/message/componentsChildren/emojis.vue";
import chooseMore from "views/message/componentsChildren/chooseMore.vue";
import scroll from "components/common/scroll/scroll.vue";
import { itemListenerMixin } from "mixins/mixins.js";
import {
  getmsgContent,
  setmsgContent,
  setChatSnapShot,
} from "network/chatList.js";
import { ChatMsgParam, DataContent } from "network/app.js";
import { noReadMsg,signMsg} from "network/task.js";

export default {
  components: {
    chatNavBar,
    chatTabBar,
    chatList,
    emojis,
    chooseMore,
    scroll,
  },
  mixins: [itemListenerMixin],
  data() {
    return {
      messageList: [],
      text: "",
      my: {
        id: this.$store.state.id,
        nickname: this.$store.state.nikname,
        avatar: "",
      },
      user: {
        userNickname: this.$store.state.currentUser.nickname,
        id: this.$store.state.currentUser.id,
        avatar: "",
      },
      websock: null,
      isActive: false,
      choosedEmoji: "",
      isShow: false,
      msgContent: {},
      isRead: false,
      nosendMsg: [],
      maxScrollY: null,
      contentsHeight: null,
    };
  },
  mounted() {
  },
  created() {
    // this.init();
    // CHAT.init();
  },
  activated() {
    // console.log(this.user.id);
    this.init();
    this.noread()
    this.isRead = true;
    this.itemImageLoad();
    // this.websock.message()
    this.websock.onmessage = this.message;
    this.messageList = getmsgContent(
      this.my.id,
      this.$store.state.currentUser.id
    );
  },
  deactivated() {
    this.isRead = false;
    console.log(22);
    this.websock.close();
  },
  destroyed() {},
  methods: {
    goback() {
      this.$router.back();
    },
    send(value) {
      console.log(value);
      //  获取当前用户id
      var myId = this.my.id;
      //接受者id
      var youId = this.user.id;
      //获取发送消息框中所输入的内容
      //构建chatMsg聊天消息
      var chatMsgParam = new ChatMsgParam(myId, youId, value, null);
      //    第一次(或重连)初始化连接
      var dataContent = new DataContent(2, chatMsgParam, null);
      this.chats(JSON.stringify(dataContent));
      // 1是我 2是朋友
      setmsgContent(myId, youId, value, 1);
      this.messageList = getmsgContent(this.my.id, this.user.id);
      console.log(this.messageList);
      //将客户输入的消息进行发送
      console.log(dataContent);

      // this.websock.send(JSON.stringify(dataContent));
      //保存聊天快照到本地缓存
      setChatSnapShot(myId, youId, value, this.isRead);
    },
    showEmojis() {
      this.isActive = !this.isActive;
      if (this.isShow) {
        this.isShow = false;
      }
    },
    showMore() {
      console.log(this.isShow);
      this.isShow = !this.isShow;
      if (this.isActive) {
        this.isActive = false;
      }
    },
    hide() {
      this.isActive = false;
    },
    allHide() {
      this.isActive = false;
      this.isShow = false;
    },
    init() {
      let url = "ws://101.35.145.209:8089/ws";
      if (window.WebSocket) {
        if (
          this.websock != null &&
          this.websock != undefined &&
          this.websock.readyState == WebSocket.OPEN
        ) {
          return false;
        }
        try {
          this.websock = new WebSocket(url);
          this.websock.onopen = this.open;
          this.websock.onmessage = this.message;
          this.websock.onerror = this.error;
          // this.websock.onclose = this.close;
        } catch (e) {
          console.log(e);
        }
      } else {
        console.log("您的版本太低暂不支持webSoket协议");
      }
    },
    open(e) {
      console.log(e);
      //    构建chatMsg聊天消息
      var chatMsgParam = new ChatMsgParam(this.my.id, null, null, null);
      //    第一次(或重连)初始化连接
      var dataContent = new DataContent(1, chatMsgParam, null);
      //    转变为string类型发送到服务器
      // console.log(chatMsgParam);
      this.chats(JSON.stringify(dataContent));
      // console.log(this.websock.readyState);
    },
    message(e) {
      console.log(1);
      console.log(e);
      console.log("接受到消息:" + e.data);
      var dataConetent = JSON.parse(e.data);
      //构建签收消息模型
      console.log(dataConetent);
      // var dataContentSign = new DataContent(
      //   3,
      //   null,
      //   dataConetent.chatMsgParam.msgId
      // );
      //构建chatMsg聊天消息
      // var chatMsgParam = new ChatMsgParam(
      //   this.my.id,
      //   this.user.id,
      //   dataConetent.chatMsgParam.msg,
      //   2
      // );
      setmsgContent(this.my.id, this.user.id, dataConetent.chatMsgParam.msg, 2);
      this.messageList = getmsgContent(this.my.id, this.user.id);
      setChatSnapShot(
        this.my.id,
        this.user.id,
        dataConetent.chatMsgParam.msg,
        this.isRead
      );
    },
    error(e) {
      console.log(e);
    },
    close(e) {
      console.log(e);
    },
    chats: function (msg) {
      //如果当前WebSocket 状态已经连接，无需重复初始化WebSocket
      if (
        this.websock != null &&
        this.websock != undefined &&
        this.websock.readyState == WebSocket.OPEN
      ) {
        //将客户输入的消息进行发送
        this.websock.send(msg);
      } else {
        this.init();
      }
    },
    itemImageLoad() {
      this.$bus.$on("itemImageLoad", () => {
        this.maxScrollY =
          document.getElementsByClassName("chatList")[0].clientHeight;
        this.contentsHeight =
          document.getElementsByClassName("contents")[0].clientHeight;
        this.$refs.scroll.scroller(0, -this.maxScrollY + this.contentsHeight);
      });
    },
    noread() {
      noReadMsg(this.$store.state.id).then((res) => {
        console.log(res)
        for (var i = 0; i < res.data.length; i++) {
          if (res.data[i].sendUserId == this.$store.state.currentUser.id) {
            console.log(res.data[i])
             signMsg(res.data[i].id).then((res)=>{
            console.log(res)
          })
            setmsgContent(this.my.id, this.user.id, res.data[i].msg, 2)
          }
        }
      });
    },
  },
};
</script>

<style scoped>
.chat {
  height: 100vh;
  width: 100%;
  position: relative;
  background-color: white;
  z-index: 10;
}
.navbar {
  background-color: #3dbafddc;
  color: white;
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
}
.tabBar {
  /* height: 53px; */
  position: absolute;
  bottom: 0px;
  left: 0px;
  right: 0px;
}
.left img {
  margin-top: 10px;
  width: 30px;
  height: 30px;
  /* background: white; */
}
.emojis,
.chooseMore {
  position: absolute;
  bottom: 0px;
  left: 0px;
  right: 0px;
}
.tabBar2 {
  position: absolute;
  bottom: 250px;
  left: 0px;
  right: 0px;
}
.contents {
  position: absolute;
  top: 44px;
  left: 0px;
  right: 0px;
  bottom: 50px;
  overflow: hidden;
}
</style>
