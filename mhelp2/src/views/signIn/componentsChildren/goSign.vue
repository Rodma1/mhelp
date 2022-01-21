<template>
  <div class="goSign">
    <div class="signBtn">签到</div>
    <span>你已经签到10天了！</span>
  </div>
</template>
<script>
</script>
<style scoped>
.goSign {
  height: 140px;
  background: #426eb7;
  position: relative;
  color: white;
}
.signBtn {
  width: 120px;
  height: 40px;
  border: 1px solid white;
  text-align: center;
  line-height: 40px;
  position: absolute;
  top: 20px;
  left: 50%;
  transform: translateX(-50%);
  border-radius: 20px;
}
.goSign span {
  position: absolute;
  top: 70px;
  left: 50%;
  transform: translateX(-50%);
}
</style>
