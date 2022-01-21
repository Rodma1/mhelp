<template>
  <div class="chat">
    <chat-nav-bar class="navbar">
      <div slot="left" @click="goback">
        <img src="@/assets/img/example/返回.png" alt="" />
      </div>
      <div slot="center">{{ user.userNickname }}</div>
    </chat-nav-bar>
    <scroll class="contents" @click.native="allHide">
      <chat-list
        class="chatList"
        ref="chatList"
        :messageList="messageList"
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
import scroll from "components/common/scroll/scroll.vue";
import emojis from "views/message/componentsChildren/emojis.vue";
import chooseMore from "views/message/componentsChildren/chooseMore.vue";

// import {cwebsocket} from "network/websocket.js"
export default {
  components: {
    chatNavBar,
    chatTabBar,
    chatList,
    scroll,
    emojis,
    chooseMore,
  },

  data() {
    return {
      socket: null,
      messageList: [
        {
          nickname: "张三",
          avatar: "啦啦啦",
          text: "你吃了吗",
        },
        {
          nickname: this.$store.state.nikname,
          avatar: this.$store.state.avatar,
          text: "吃了",
        },
        {
          nickname: "张三",
          avatar: "rrr",
          text: "那就好，千万别饿着",
        },
      ],
      text: "",
      // my: {
      //   myNickname: this.$store.state.nikname,
      //   avatar: "",
      // },
      user: {
        userNickname: "吼吼哈嘿",
        avatar: "",
      },
      websock: null,
      isActive: false,
      choosedEmoji: "",
      isShow: false,
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    goback() {
      this.$router.back();
    },
    send(value) {
      let params = {
        senderId: this.$store.state.id,
        receiverId:2,
        text: value,
        textId:2
      };
      this.messageList.push(params);
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
        this.websock = new WebSocket(url);
        this.websock.onopen = this.open;
        this.websock.onmessage = this.message;
        this.websock.onerror = this.error;
        this.websock.onclose = this.close;
      }
      else{
        console.log('您的版本太低暂不支持webSoket协议')
      }
    },
    open(e) {
      console.log(e);
    },
    message(e) {
      console.log(e);
    },
    error(e) {
      console.log(e);
    },
    close(e) {
      console.log(e);
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
  background-color: rgb(154, 171, 248);
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
  margin: 6px;
  width: 30px;
  height: 30px;
  /* background: white; */
}
.contents {
  position: absolute;
  left: 0px;
  right: 0px;
  bottom: 50px;
  top: 44px;
  overflow: hidden;
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
</style>
