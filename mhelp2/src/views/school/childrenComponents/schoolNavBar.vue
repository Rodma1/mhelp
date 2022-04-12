<template>
  <div class="schoolNavBar">
    <nav-bar class="schoolNav">
      <div slot="left" class="left" @click="goback">
        <img src="@/assets/img/example/返回.png" alt="" />
      </div>
      <div slot="center" class="center">
        <div>搜索你的学校</div>
      </div>
      <div slot="right" class="right">搜索</div>
    </nav-bar>
  </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";
export default {
  components: {
    navBar,
  },
  methods: {
    goback() {
      this.$router.back();
    },
  },
};
</script>
<style scoped>
.left img {
  width: 30px;
  height: 30px;
  margin-top: 10px;
}
.center div {
  width: 100%;
  height: 25px;
  border-radius: 10px;
  border: 0px;
  background-color: white;
  line-height: 25px;
  text-align: left;
  padding: 0px 5px;
  font-size: 14px;
  color: rgb(174, 174, 177);
}
.schoolNavBar {
  background: #3dbafddc;
}
.right {
  color: white;
}
</style>
