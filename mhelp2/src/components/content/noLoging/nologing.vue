<template>
    <div class="noLoginList">
        <img src="@/assets/img/publish/未登录.png" alt="">
        <div class="page"><slot name="page"></slot></div>
        <div>登录</div>
    </div>
</template>
<style scoped>
.noLoginList{
  position: absolute;
  top: 0px;
  bottom: 0px;
  right: 0px;
  left: 0px;
  z-index: 15;
  /* background-color: red; */
  text-align: center;
}
.noLoginList img{
  width: 200px;
  height: 130px;
  margin-top: 20px;
}
.noLoginList div{
  width: 100px;
  height: 30px;
  background-color: #1facf8;
  line-height: 30px;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  margin-top: 30px;
}
.page{
    height: 100px;
    background-color: red;
}
</style>