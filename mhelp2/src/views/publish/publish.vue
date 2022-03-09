<template>
  <div class="publish">
    <publish-nav-bar></publish-nav-bar>
    <keep-alive>
      <router-view class="routerView"></router-view>
    </keep-alive>
  
  </div>
</template>
<script>
import publishNavBar from "views/publish/componentsChildren/publishNavBar.vue";
export default {
  components: {
    publishNavBar,

  },
  data() { 
    return {
      isActive:false
    };
  },
  methods:{ 
    change(){
      this.isActive=!this.isActive
    }
  }
};
</script>

<style scoped>
.publish {
  height: 100vh;
  position: relative;
  /* z-index: 10; */
  background-color: #f3f3f3;
 
}
.routerView{
  position: absolute;
  top: 44px;
  left: 0px;
  right: 0px;
  bottom: 49px;
}
</style>

