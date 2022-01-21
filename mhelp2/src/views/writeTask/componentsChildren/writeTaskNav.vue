<template>
  <div class="writeTask">
    <nav-bar class="writeTaskNav">
      <div slot="center" class="center">
        <div :class="{ active: isActive }" @click="change1">记录</div>
        <div :class="{ active: !isActive }" @click="change2">发任务</div>
      </div>
    </nav-bar>
  </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";

export default {
  components: {
    navBar,
  },
  data() {
    return {
      isActive: false,
    };
  },
  activated(){
    this.change2()
  },
  methods: {
    change1() {
      this.isActive = true;
      this.$router.push("/writeTask/writeTaskHistory");
    },
    change2() {
      this.isActive = false;
      this.$router.push("/writeTask/writeTaskView");
    },
  },
};
</script>
<style scoped>
.writeTaskNav {
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
.active {
  font-size: 23px;
}
</style>
