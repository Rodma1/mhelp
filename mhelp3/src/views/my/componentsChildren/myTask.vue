<template>
  <div class="myTask">
    <ul class="title">
      <li>我的任务</li>
      <li>全部</li>
    </ul>
    <ul class="content">
      <li @click.stop="nofinish">
        <img src="@/assets/img/my/nofinish.png" alt="" />
        <div>未完成</div>
      </li>
      <li @click.stop="finish">
        <img src="@/assets/img/my/finished.png" alt="" />
        <div>已完成</div>
      </li>
      <li @click.stop="collect">
        <img src="@/assets/img/my/collect.png" alt="" />
        <div>收藏</div>
      </li>
      <li @click.stop="toSign">
        <img src="@/assets/img/my/sign.png" alt="" />
        <div>签到</div>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  methods: {

    toSign() {
      this.$router.push("/signIn");
    },
    nofinish(){
      this.$router.push("/taskList/1")
      this.$bus.$emit("choosedType",1)
    },
    finish(){
      this.$router.push("/taskList/2")
      this.$bus.$emit("choosedType",2)
    },
    collect(){
      this.$router.push("/taskList/3")
      this.$bus.$emit("choosedType",3)
    }
  },
};
</script>
<style scoped>
.myTask {
  width: 95%;
  height: 100px;
  background-color: #ffffff;
  border-radius: 8px;
}
.myTask ul {
  list-style-type: none;
  display: flex;
  align-items: center;
  text-align: center;
}
.myTask ul li {
  flex: 1;
  /* height: 60px; */
}
.myTask img {
  width: 24px;
  height: 24px;
}
.title {
  height: 40px;
}
.title li:nth-child(1) {
  text-align: left;
  padding-left: 10px;
  font-weight: 600;
  font-size: 18px;
}
.title li:nth-child(2) {
  text-align: right;
  padding-right: 10px;
  font-size: 16px;
}
.content {
  height: 60px;
}
</style>
