<template>
  <div class="information">
    <scroll class="contents">
      <items
        v-for="(item, index) in this.chatList"
        :key="index"
        :item="item"
        @click.native="goChat(index)"
      ></items>
      <!-- <items @click.native="goChat"></items> -->
    </scroll>
  </div> 
</template>
<script>
import Items from "views/message/componentsChildren/informationItem.vue";
import scroll from "components/common/scroll/scroll.vue";
// import { getChatList } from "network/chatList.js";
export default {
  components: {
    Items,
    scroll, 
  },
  data() {
    return {
      chatList: this.$store.state.chatList,
      userId:""
    };
  },
  methods: {
    goChat(index) {
        this.userId=this.$store.state.chatList[index].id;
        this.$router.push("/chat/"+this.userId);
        this.$store.commit("saveCurrentUser",this.$store.state.chatList[index])
    },
  },
};
</script>

<style scoped>
.information {
  position: absolute;
  top: 44px;
  bottom: 49px;
  left: 0px;
  right: 0px;
}
.contents {
  height: 100%;
  overflow: hidden;
}
</style>