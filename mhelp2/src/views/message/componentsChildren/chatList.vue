<template>
  <div class="chatList">
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
  props: {
    messageList: {
      type: Array,
      default() {
        return [];
      },
    },
  },
  methods: {},
};
</script>
<style scoped>
.chatList {
  padding: 10px 10px;
}
</style>
