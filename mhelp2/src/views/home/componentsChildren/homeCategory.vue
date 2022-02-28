<template>
  <div class="homeCategory">
      <span>任务类型</span>
    <ul class="task">
      <li>全部任务</li>
      <li>拿取快递</li>
      <li>求借东西</li>
      <li>食堂打包</li>
      <li>技术顾问</li>
      <li>学习帮助</li>
      <li>活动组队</li>
      <li>二手闲置</li>
      <li>寻找树洞</li>
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
<style scoped>
.homeCategory{
    background: #ffffff;
    color: #b8b5b5;
    z-index: 99;
}
.homeCategory ul {
  display: flex;
  list-style-type: none;
  flex-wrap: wrap;
  /* background: pink; */
  align-items: center;
}
.homeCategory ul li {
  width: 80px;
  height: 30px;
  background: #f2f2f2;
  color: black;
  margin: 5px;
  border-radius: 5px;
  text-align: center;
  line-height: 30px;
  font-size: 13px;
}
/* .homeCategory .mask{
  height: 300px;
  background-color: rgba(0, 0, 0, 0.1);
} */
</style>