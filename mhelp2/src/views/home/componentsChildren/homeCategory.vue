<template>
  <div class="homeCategory">
    <span>任务类型</span>
    <ul class="task">
      <li
        v-for="(item, index) in category"
        :key="index"
        @click="categoryTasks(index)"
      >
        {{ item.categoryName }}
      </li>
    </ul>
    <span>标签</span>
    <ul class="tag">
      <li>陪吃</li>
      <li>陪聊</li>
      <li>玩耍</li>
    </ul>
    <!-- <div class="mask"></div> -->
  </div>
</template>
<script>
export default {
  props: {
    category: {
      type: Array,
      default() {
        return [];
      },
    },
  },
  methods: {
    categoryTasks(index) {
      this.$emit("categorySearch",index)
    },
  },
};
</script>

<style scoped>
.homeCategory {
  background: #ffffff;
  color: #b8b5b5;
  z-index: 99;
  padding: 5px 10px;
}
.homeCategory ul {
  display: flex;
  list-style-type: none;
  flex-wrap: wrap;
  /* background: pink; */
  align-items: center;

  margin-top: 10px;
}
.homeCategory ul li {
  width: 23.5%;
  height: 30px;
  background: #f2f2f2;
  color: black;
  margin: 0px 2% 5px 0px;
  border-radius: 5px;
  text-align: center;
  line-height: 30px;
  font-size: 13px;
}
.homeCategory ul li:nth-child(4n) {
  margin: 0px 0px 5px 0px;
}
</style>