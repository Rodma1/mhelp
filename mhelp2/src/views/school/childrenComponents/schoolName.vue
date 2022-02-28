<template>
  <div class="schoolName">
    <van-index-bar>
  <van-index-anchor index="A" />
  <van-cell title="文本" />
  <van-cell title="文本" />
  <van-cell title="文本" />

  <van-index-anchor index="B" />
  <van-cell title="文本" />
  <van-cell title="文本" />
  <van-cell title="文本" />

  ...
</van-index-bar>
  </div>
</template>
<script>
export default {
  data() {
    return {
      foo: 'bar'
    };
  },
};
</script>

<style scoped>
.van-index-anchor {
  background-color: #00000000;
}
.schoolItem {
  background-color: white;
}
.schoolItem > div {
  height: 50px;
  margin: 0px 20px;
  line-height: 50px;
  border-bottom: 1px solid gray;
}
</style>
