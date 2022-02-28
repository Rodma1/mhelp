<template>
  <div class="chatList" ref="chatList">
    <chat-item
      v-for="(item, index) in messageList"
      :key="index"
      :item="item"
    ></chat-item>
  </div>
</template>
<script>
import chatItem from "views/message/componentsChildren/chatItem.vue";

export default {
  components: {
    chatItem,
  },
  data() {
    return {
      maxScrollY: null,
    };
  },
  props: {
    messageList: {
      type: Array,
      default() {
        return [];
      },
    },
  },
  mounted() {
    this.chatListItemLoad();
  },
  updated() {},
  methods: {
    chatListItemLoad() {
      this.maxScrollY =document.getElementsByClassName("chatList")[0].clientHeight;
      console.log(document.getElementsByClassName("chatList")[0].clientHeight);
      console.log(this.maxScrollY);
    },
  },
};
</script>
<style scoped>
.chatList {
  height: 100%;
  padding: 10px 10px;
}
</style>
