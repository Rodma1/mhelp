<template>
  <div id="app">
    <keep-alive>
      <router-view exclude="taskList"></router-view>
    </keep-alive>
    <main-tab-bar class="mainTabBar"></main-tab-bar>
  </div>
</template>
<script>
import mainTabBar from "components/content/mainTabBar/mainTabBar.vue";

export default {
  components: {
    mainTabBar,
  },
};
</script>


<style>
@import "assets/css/common.css";
#app {
  height: 100%;
}
/* s */
</style>
