<template>
  <div class="swiper">
    <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
      <van-swipe-item><img src="@/assets/img/example/banner1.jpg" alt=""></van-swipe-item>
      <van-swipe-item><img src="@/assets/img/example/banner2.jpg" alt=""></van-swipe-item>
      <van-swipe-item><img src="@/assets/img/example/banner3.jpg" alt=""></van-swipe-item>
      <van-swipe-item><img src="@/assets/img/example/banner4.jpg" alt=""></van-swipe-item>
    </van-swipe>
  </div>
</template>
<script>
</script>
<style scoped>
.my-swipe .van-swipe-item {
    color: #fff;
    font-size: 20px;
    text-align: center;
    background-color: #39a9ed;
    height: 150px;
  }
  .img{
    /* height: 160px; */
    height: 100%;
    width: 100%;
  }
</style>
