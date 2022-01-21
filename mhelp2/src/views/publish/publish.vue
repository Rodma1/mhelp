<template>
  <div class="publish">
    <publish-nav-bar></publish-nav-bar>
    <keep-alive>
      <router-view ></router-view>
    </keep-alive>
    
    <!-- <fill-details></fill-details>
    <publish-btn  @click.native="change"></publish-btn>
    <publish-toast v-if="isActive" @change="change"></publish-toast> -->
  </div>
</template>
<script>
import publishNavBar from "views/publish/componentsChildren/publishNavBar.vue";
// import fillDetails from "views/publish/componentsChildren/fillDetails.vue"
// import publishToast from "views/publish/componentsChildren/publishToast.vue"
// import publishBtn from "views/publish/componentsChildren/publishBtn.vue"
export default {
  components: {
    publishNavBar,
    // fillDetails,
    // publishToast,
    // publishBtn
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

</style>

