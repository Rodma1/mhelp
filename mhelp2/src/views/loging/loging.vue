<template>
  <div class="loging">
    <loging-nav-bar></loging-nav-bar>
    <div class="content">
      <div class="logo">
        <img src="@/assets/img/loging/握手.png" alt="" />
      </div>
      <div class="box">
        <div class="item" :class="{isActive:isTips}">
          <img src="@/assets/img/loging/名字.png" alt="" />
          <input type="text" name="" id="" v-model="userForm.account" @blur="rule"/>
        </div>
        <div class="tips" v-if="isTips">
          <img src="@/assets/img/loging/tips.png" alt="" />
          字符要大于0小于10
        </div>
        <div class="item" :class="{isActive:isTips}">
          <img src="@/assets/img/loging/密码.png" alt="" />
          <input type="password" name="" id="" v-model="userForm.password" @blur="rule"/>
        </div>
        <div class="tips" v-if="isTips2">
          <img src="@/assets/img/loging/tips.png" alt="" />
          字符要大于0小于10
        </div>
      </div>
      <div class="logingBtn" :class="{isActive2:isTips2}" @click="toLogin">登录</div>
      <div class="toRegist" @click="toRegist">注册</div>
      
    </div>
    <!-- <toast class="toast" :toast="toast" :logingShow="wrong"></toast> -->
  </div>
</template>
<script>
import logingNavBar from "views/loging/componentsChildren/logingNavBar.vue";
// import toast from 'components/common/toast/toast.vue';
export default {
  components: {
    logingNavBar,
    // toast,
  },
  data() {
    return {
      isActive: true,
      isTips:false,
      isTips2:false,
      userForm: {
        account: "ad",
        password: "admins",
        toast:"用户名或密码不存在"
      },
      wrong:false
    };
  },
  methods: {
    back() {
      this.$router.back();
    },
    change1() {
      this.isActive = true;
    },
    change2() {
      this.isActive = false;
    },
    toLogin() {
      this.rule()
      this.$store.dispatch("login", this.userForm).then(() => {
        console.log(this.$store.state.token);
        // if(!this.$store.state.token){
        //   this.wrong=true;
        //   setTimeout(()=>{
        //     this.wrong=false
        //   },800)
        // }
        this.$store.dispatch("getUserInfo").then(() => {
          setTimeout(() => {
            console.log(this.$store.state.token);
            this.$router.push("/home");
          }, 200);
        });
      });
    },
    toRegist() {
      this.$router.push("/regist")
    },
    rule() {
      if (
        this.userForm.account.length == 0 ||
        this.userForm.password.length > 11
      ) {
        this.isTips=true
      }else{
        this.isTips=false
      }
      if (
        this.userForm.password.length == 0 ||
        this.userForm.password.length > 11
      ) {
        this.isTips2=true
      }else{
        this.isTips2=false
      }
    },
  },
};
</script>
<style scoped>
.loging {
  position: relative;
  height: 100vh;
  z-index: 15;
  background-color: white;
}
.logo {
  height: 150px;
  line-height: 50px;
  text-align: center;
}
.logo img {
  width: 176px;
  height: 120px;
}
.content {
  padding: 10px;
  position: absolute;
  top: 50%;
  left: 0px;
  right: 0px;
  transform: translate(0, -60%);
}
.item {
  height: 50px;
  display: flex;
  margin-bottom: 20px;
  margin-right: 10px;
}
.box .item:nth-child(2){
   margin-bottom: 0px;
}
.item img {
  width: 30px;
  height: 30px;
  /* background-color: red; */
  margin-right: 5px;
  margin-top: 10px;
}
.item input {
  flex: 1;
  border: 1px solid gray;
  outline: 1px solid #1296db;
}
.logingBtn {
  height: 40px;
  background-color: #1296db;
  text-align: center;
  line-height: 40px;
  margin: 30px 0px 8px 10px;
  color: white;
  font-size: 18px;
}
.toRegist {
  text-align: right;
  padding: 10px;
  color: #86ccf1;
}
.tips img {
  width: 15px;
  height: 15px;
}
.tips {
  height: 20px;
  margin-left: 40px;
  color: red;
  font-size: 13px;
}
.box .isActive{
  margin-bottom: 0px;
}
.content .isActive2{
  margin-top: 10px;
}
.toast {
  height: 40px;
  width: 80%;
  position: absolute;
  top: 200px;
}
</style>
