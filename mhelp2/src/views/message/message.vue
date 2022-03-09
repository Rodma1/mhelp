<template>
  <div class="message">
    <message-nav-bar></message-nav-bar>
    <keep-alive>
      <router-view class="routerView"></router-view>
    </keep-alive>
  </div>
</template>
<script>
import messageNavBar from "views/message/componentsChildren/messageNavBar.vue";

export default {
  components: {
    messageNavBar,
  },

};
</script>
<style scoped>
.message {
  height: 100vh;
}
.navBar {
  /* font-size: px; */
  background: rgb(154, 171, 248);
  color: white;
}
.center {
  display: flex;
}
.center div {
  flex: 1;
}
.center div:nth-child(1) {
  text-align: right;
  padding-right: 10px;
}
.center div:nth-child(2) {
  text-align: left;
  padding-left: 10px;
}
.routerView,
.isLoging {
  position: absolute;
  top: 44px;
  bottom: 49px;
  left: 0px;
  right: 0px;
}

</style>
