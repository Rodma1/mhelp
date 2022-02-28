<template>
  <div class="searchGuess">
    <div class="title">
      <span>猜你想搜</span>
      <div @click="change">
        <img src="@/assets/img/example/显示.png" alt="" v-if="isShow" />
        <img src="@/assets/img/example/隐藏.png" alt="" v-else />
      </div>
    </div>
    <ul v-if="isShow">
      <li>取快递</li>
      <li>带饭</li>
      <li>组队</li>
      <li>蜜雪冰城</li>
    </ul>
  </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      isShow: true,
    };
  },
  methods: {
    change() {
      this.isShow = !this.isShow;
    },
  },
};
</script>
<style scoped>
.searchGuess {
  height: 150px;
  /* background-color: green; */
  padding: 10px;
}
.title {
  font-size: 18px;
  font-weight: 600;
  margin-bottom: 10px;
  position: relative;
}
ul {
  display: flex;
  flex-wrap: wrap;
}
.title img {
  width: 25px;
  height: 25px;
}
.title div {
  position: absolute;
  right: 0px;
  top: 0px;
}
li {
  min-width: 50px;
  max-width: 100%;
  background: #f7f7f7;
  overflow: hidden;
  margin-right: 10px;
  margin-bottom: 10px;
  text-align: center;
  font-size: 14px;
  line-height: 25px;
}
</style>
