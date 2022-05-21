<template>
  <div class="pakege">
    <nav-bar>
      <div slot="left" @click="goback" class="left">
        <img src="@/assets/img/example/back.png" alt="" />
      </div>
      <div slot="center">我的钱包</div>
    </nav-bar>
    <wallet :money="userInfo.money"></wallet>
    <get-gold-coin ></get-gold-coin>
    <pakege-rules></pakege-rules>
    <router-view class="routerView"></router-view>
  </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";
import wallet from "views/pakege/componentsChildren/wallet.vue";
import getGoldCoin from "views/pakege/componentsChildren/getGoldCoin.vue";
import pakegeRules from "views/pakege/componentsChildren/pakegeRules.vue";
import {getUserInfo} from "network/loging.js"
export default {
  components: {
    navBar,
    wallet,
    getGoldCoin,
    pakegeRules,
  },
  data() {
    return {
      isPay: false,
      userInfo:null
    };
  },
  mounted(){
    this.getuserInfo()
  },
  methods: {
    goback() {
      this.$router.back();
    },
    pay() {
        this.$router.push('/pakege/payment')
    },
    getuserInfo(){
    getUserInfo(this.$store.state.token).then((res)=>{
       console.log(res)
       this.userInfo=res.data
     })
    }
  },
};
</script>

<style scoped>
.pakege {
  height: 100vh;
  position: relative;
  z-index: 10;
  background: white;
}
img {
  padding-top: 10px;
  width: 30px;
  height: 30px;
}
.routerView{
    position: absolute;
    z-index: 11;
    top: 0px;
    left: 0px;
    right: 0px;
    bottom: 0px;
    background-color: #f8f7f7;
}
</style>