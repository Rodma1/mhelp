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
      <li v-for="(item,index) in guess" :key="index" @click="goSearch(item)">{{item}}</li>
    </ul>
  </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      isShow: true,
      guess:[
        "取快递",
        "蜜雪冰城",
        "带饭",
        "后稷一"
      ]
    };
  },
  methods: {
    change() {
      this.isShow = !this.isShow;
    },
     goSearch(item){
      console.log(item);
      this.$bus.$emit("historySearch",item)
    }
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
