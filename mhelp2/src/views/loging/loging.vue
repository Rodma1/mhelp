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
  </div>
</template>
<script>
import logingNavBar from "views/loging/componentsChildren/logingNavBar.vue";
export default {
  components: {
    logingNavBar,
  },
  data() {
    return {
      isActive: true,
      isTips:false,
      isTips2:false,
      userForm: {
        account: "ad",
        password: "admins",
      },
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
/* .box {
  width: 300px;
  height: 250px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
} */
/* .title {
  width: 300px;
  height: 40px;
  display: flex;
  list-style-type: none;
}
.title li {
  flex: 1;
  background: rgb(154, 171, 248);
  color: white;
  line-height: 40px;
  text-align: center;
}
form {
  width: 300px;
  height: 200px;
  background: #ffffff;
}
form input {
  width: 180px;
  height: 30px;
  outline: none;
  border: 2px solid rgb(154, 171, 248);
  margin-top: 20px;
}
.content1 {
  padding-top: 10px;
}
.content2 {
  height: 250px;
}
form {
  background: white;
}
table {
  width: 100%;
  height: 70%;
}
table tr {
  text-align: right;
  vertical-align: 10px;
}

table tr td:nth-child(2) input {
  position: relative;
  right: 30px;
}
ul .current {
  font-size: 23px;
  background-color: white;
  color: rgb(154, 171, 248);
}
.back {
  width: 50px;
  height: 50px;
  line-height: 50px;
  text-align: center;
  position: absolute;
  top: 10px;
  left: 10px;
}
.back img {
  width: 40px;
  height: 40px;
}
.btn {
  position: absolute;
  left: 50%;
  bottom: 20px;
  transform: translateX(-50%);
  width: 150px;
  height: 40px;
  border: 0px;
} */
</style>
