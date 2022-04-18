<template>
  <div class="moreNavBar">
      <nav-bar> 
          <div slot="left" @click="goback" class="left">
              <img src="@/assets/img/example/back.png" alt="">
          </div>
          <div slot="center">
              个人信息
          </div>
      </nav-bar>
  </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";

export default {
  components: {
      navBar
  },
  methods:{
      goback(){
          this.$router.back()
      }
  }
};
</script>

<style scoped>
.left img{
    margin-top: 8px;
    width: 30px;
    height: 30px;
}
</style>