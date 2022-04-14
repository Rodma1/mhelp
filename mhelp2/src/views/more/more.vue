<template>
  <div class="more">
    <router-view class="routerView"></router-view>
    <more-nav-bar></more-nav-bar>
    <more-list></more-list>
  </div>
</template>
<script>
import moreNavBar from "views/more/componentsChildren/moreNavBar.vue";
import moreList from "views/more/componentsChildren/moreList.vue";
// import {getUserInfo} from "network/loging.js"
export default {
  components: {
    moreNavBar,
    moreList,
  },
  mounted() {
    
  },
};
</script>
<style scoped>
.more {
  height: 100vh;
  position: relative;
  z-index: 10;
  background-color: white;
}
.routerView {
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  z-index: 15;
}
</style>