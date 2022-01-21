<template>
  <div class="school">
    <nav-bar class="schoolNav">
      <div slot="left" class="left" @click="goback">
        <img src="@/assets/img/example/返回.png" alt="">
      </div>
      <div slot="center" class="center">
        <input type="text" name="" id="" />
      </div>
    </nav-bar>
    <school-name></school-name>
  </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";
import schoolName from "views/school/childrenComponents/schoolName.vue"
export default {
  components: {
    navBar,
    schoolName
  },
  methods:{
    goback(){
      this.$router.back()
    }
  }
};
</script>
<style scoped>
.school {
  height: 100vh;
}
.left img{
  width: 30px;
  height: 30px;
  margin-top: 10px;
}
.center input {
  width: 250px;
  height: 25px;
  border-radius: 10px;
  outline: none;
  border: 0px;
  background-color: white;
  
}
.schoolNav {
  background: rgb(154, 171, 248);
}
</style>
