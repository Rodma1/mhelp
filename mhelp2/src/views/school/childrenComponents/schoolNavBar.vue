<template>
  <div class="schoolNavBar">
    <nav-bar class="schoolNav">
      <div slot="left" class="left" @click="goback">
        <img src="@/assets/img/example/返回.png" alt="" />
      </div>
      <div slot="center" class="center">
        <div></div>
      </div>
      <div slot="right" class="right">搜索</div>
    </nav-bar>
  </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";
export default{
    components:{
        navBar
    },
    methods:{
    goback(){
      this.$router.back()
    }
  }
}
</script>
<style scoped>
.left img{
  width: 30px;
  height: 30px;
  margin-top: 10px;
}
.center div{
width: 100%;
  height: 25px;
  border-radius: 10px;
  border: 0px;
  background-color: white;
  
}
.schoolNavBar {
  background: rgb(154, 171, 248);
}
.right{
  color: white;
}
</style>
