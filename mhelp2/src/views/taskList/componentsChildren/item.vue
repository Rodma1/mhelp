<template>
  <div class="item">
    <div class="top">
      <img src="@/assets/img/example/头像.jpg" alt="" />
      <div class="name">吼吼哈嘿</div>
      <div class="status">已完成</div>
    </div>
    <div class="center">
      <div class="content">
        <div class="categoryAndTags">
          <div>分类：零食带饭</div>
          <div>标签：食堂</div>
        </div>
        <div class="main">
          帮我在后稷2食堂带个饭吧，要麻辣的鱼粉，多加香菜谢谢
        </div>
        <div class="image">
          <img src="@/assets/img/example/图片1.jpg" alt="">
          <img src="@/assets/img/example/图片2.jpg" alt="">
          <img src="@/assets/img/example/图片1.jpg" alt="">
          <img src="@/assets/img/example/图片2.jpg" alt="">
          <img src="@/assets/img/example/图片1.jpg" alt="">
          <img src="@/assets/img/example/图片2.jpg" alt="">
        </div>
      </div>
      <div class="others">
        <div class="time">接任务时间:2021年3月21日</div>
        <div class="price">
          <span>合计:</span>
          <span>5金币</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
</script>
<style scoped>
.item {
  background-color: white;
  height: 170px;
  border-radius: 10px;
  margin-bottom: 10px;
  padding: 10px 10px;
}
.top {
  height: 30px;
  /* background-color: red; */
  display: flex;
  align-items: center;
  border-bottom: 1px solid #f2f2f2;
}
.top img {
  width: 28px;
  height: 28px;
  border-radius: 50%;
}
.name {
  flex: 1;
  font-size: 20px;
  font-weight: 500;
}
.status {
  width: 60px;
  color: #616161;
  font-size: 13px;
  text-align: right;
}
.center {
  height: 140px;
  /* background-color: green; */
}
.center .content {
  height: 120px;
}
.others {
  height: 20px;
  /* background: pink; */
  font-size: 13px;
  display: flex;
  border-top: 1px solid #f2f2f2;
}
.time {
  color: #666666;
  line-height: 30px;
  flex: 1;
}
.price {
  width: 110px;
  /* background: gray; */
  text-align: right;
  line-height: 30px;
}
.price span:nth-child(1) {
  color: #666666;
  font-size: 15px;
}
.price span:nth-child(2) {
  font-size: 20px;
  color: rgb(99, 129, 212);
}
.categoryAndTags{
  height: 15px;
  display: flex;
  font-size: 8px;
  color: #5b8099;
}
.categoryAndTags div{
  width: 120px;
  height: 15px;
  /* border: 1px solid red; */
}
.main{
  /* height: 20px; */
  /* background: red; */
  margin-top: 10px;
  font-size: 15px;
  color: #585757;
}
.image{
  margin-top: 5px;
  width: 100%;
  height: 50px;
  display: flex;
}
.image img{
 height: 50px;
 width: 50px;
 margin-right: 5px;
}
</style>
