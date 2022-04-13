<template>
  <div class="exchangeGoods">
    <div>礼品兑换</div>
    <div class="goods">
      <div>
        <div>
          <img
            src="@/assets/img/example/商品1.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        酷感玫红色毛衣
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/商品2.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        森系绿色小裙子
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/商品3.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        魅惑眼影盘
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/商品4.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        高科技产品
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/商品5.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        碎花小裙裙
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/商品6.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        简约桌椅
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/商品7.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        性感辣妹群
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/商品8.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        夜店小单品
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/商品9.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        超好吃冰激凌
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/商品10.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        粉色鱼缸
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/商品11.jpg"
            alt=""
            @load="itemImageLoad"
          />
        </div>
        橘色墙纸
      </div>
      <div>
        <div>
          <img
            src="@/assets/img/example/商品12.jpg"
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