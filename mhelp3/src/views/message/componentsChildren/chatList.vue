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
    return {};
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

  },
  updated() {

  },
  methods: {
  },
};
</script>
<style scoped>
.chatList {
  height: 100%;
  padding: 10px 10px;
  /* position: fixed; */
  /* bottom: 0px; */
  /* right: 0px; */
}
</style>
