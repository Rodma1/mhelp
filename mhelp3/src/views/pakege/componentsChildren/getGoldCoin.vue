<template>
  <div class="getGoldCoin">
    <div>获取金币</div>
    <div class="buy">
      <div @click="pay">
        <div class="money">充10元</div>
        <div class="coin">
          20 <img src="@/assets/img/pakege/coin.png" alt="" />
        </div>
      </div>
      <div>
        <div class="money">充20元</div>
        <div class="coin">
          50 <img src="@/assets/img/pakege/coin.png" alt="" />
        </div>
      </div>
      <div>
        <div class="money">充50元</div>
        <div class="coin">
          120 <img src="@/assets/img/pakege/coin.png" alt="" />
        </div>
      </div>
      <div>
        <div class="money">充80元</div>
        <div class="coin">
          180<img src="@/assets/img/pakege/coin.png" alt="" />
        </div>
      </div>
      <div>
        <div class="money">充100元</div>
        <div class="coin">
          260 <img src="@/assets/img/pakege/coin.png" alt="" />
        </div>
      </div>
      <div>
        <div class="money">充200元</div>
        <div class="coin">
          450 <img src="@/assets/img/pakege/coin.png" alt="" />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  methods: {
    pay(){
        this.$emit("pay")
    },
  },
};
</script>

<style scoped>
.getGoldCoin {
  margin: 10px;
  /* height: 300px; */
  /* background: red; */
}
.getGoldCoin > div:nth-child(1) {
  height: 40px;
  /* background: yellow; */
  line-height: 40px;
  font-size: 18px;
  font-weight: 600;
}
.buy {
  display: flex;
  flex-wrap: wrap;
  /* padding: 10px; */
}
.buy > div {
  position: relative;
  width: 46%;
  height: 100px;
  /* background-color: purple; */
  border: 2px solid #7bc8f1;
  border-radius: 15px;
  margin-top: 10px;
  text-align: center;
}
.buy div:nth-child(2),
.buy div:nth-child(4),
.buy div:nth-child(6) {
  margin-left: 5%;
}
.money {
  width: 100%;
  font-size: 25px;
  height: 60px;
  background-color: #d6edfa;
  color: rgb(247, 128, 17);
  border-radius: 15px 15px 0px 0px;
  line-height: 60px;
}
.coin {
  width: 100%;
  height: 40px;
  font-size: 18px;
}
.coin img {
  width: 20px;
  height: 20px;
  margin-top: 10px;
}
</style>
