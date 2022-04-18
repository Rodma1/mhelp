<template>
  <div class="exchangeGoods">
    <div>礼品兑换</div>
    <div class="goods">
      <div>
        <div>
          <img
            src="@/assets/img/example/one.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        酷感玫红色毛衣
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/two.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        森系绿色小裙子
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/three.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        魅惑眼影盘
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/four.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        高科技产品
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/five.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        碎花小裙裙
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/six.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        简约桌椅
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/seven.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        性感辣妹群
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/eight.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        夜店小单品
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/nine.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        超好吃冰激凌
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/ten.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        粉色鱼缸
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/eleven.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        橘色墙纸
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/twelve.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        时尚眼睛
      </div>
    </div>
  </div>
</template>
<script>
export default {
  components: {},
  methods: {
    itemImageLoad() {
      this.$bus.$emit("itemImageLoad");
    },
  },
};
</script>

<style scoped>
.exchangeGoods {
  /* background-color: orangered; */
  margin: 10px;
}
.exchangeGoods > div:nth-child(1) {
  height: 50px;
  /* background-color: yellow; */
  font-size: 18px;
  font-weight: 500;
  text-align: center;
  line-height: 50px;
}
.goods {
  display: flex;
  flex-wrap: wrap;
  /* padding: 10px; */
}
.goods >div {
  height: 220px;
  width: 47%;
  margin: 10px 1%;
  /* margin: 10px; */
  /* background-color: green; */
  border-radius: 8px;
  border: 1px solid blue;
  text-align: center;
}
.goods > div > div:nth-child(1) {
  width: 100%;
  height: 190px;
  /* background-color: blue; */
  margin: 0px;
}
.goods > div > div img {
  height: 90%;
  width: 90%;
  margin: 5%;
}
</style>