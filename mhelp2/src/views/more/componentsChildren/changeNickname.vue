<template>
  <div class="changeNickname">
    <nav-bar>
      <div slot="left" @click="goback" class="left">
        <img src="@/assets/img/example/返回.png" alt="" />
      </div>
      <div slot="center">修改昵称</div>
    </nav-bar>
    <div class="change">
      <div class="top">用户名:</div>
      <div class="middle" @click="inputing">
        <input
          class="inputBox"
          type="text"
          v-if="isShow"
          v-model="updateMsg.nickname"
        >{{ nickname }}
      </div>
      <div class="tip">限4-11个字符，一个汉字为两个字符</div>
      <div class="button" @click="change">确认</div>
    </div>
  </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";
import {getUserInfo} from "network/loging.js"
export default {
  components: {
    navBar,
  },
  data() {
    return {
      nickname: this.$store.state.nickname,
      isShow: false,
      input:null,
      updateMsg:{
        nickname: "",
      }
    };
  },
  mounted() {
  
  },
  methods: {
    goback() {
      this.$router.back();
    },
    inputing() {
      this.isShow = true;
      this.nickname = "";
    },
    change(){
      console.log(this.newNickname)
      this.$store.dispatch('updateUserInfo',this.updateMsg).then(()=>{
        console.log(11)
        

      }).then(()=>{
        getUserInfo(this.$store.state.token).then((res)=>{
        console.log(res)
        })
      })
      
    }
  },
};
</script>

<style scoped>
.changeNickname {
  background-color: white;
  height: 100vh;
}
.left img {
  width: 30px;
  height: 30px;
}
.change {
  height: 150px;
  /* background-color: red; */
  padding: 15px;
}
.top {
  font-size: 15px;
}
.middle {
  height: 40px;
  border-bottom: 1px solid gray;
  line-height: 40px;
}
.middle input {
  width: 100%;
  height: 100%;
  outline: none;
  border: 0px;
}
.button {
  height: 30px;
  background-color: #1facf8;
  color: white;
  text-align: center;
  line-height: 30px;
}
.tip {
  font-size: 13px;
  margin: 10px 0px;
}
</style>