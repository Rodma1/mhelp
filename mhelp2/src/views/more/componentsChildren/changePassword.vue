<template>
  <div class="changePassword">
    <nav-bar>
      <div slot="left" @click="goback" class="left">
        <img src="@/assets/img/example/返回.png" alt="" />
      </div>
      <div slot="center">修改密码</div>
    </nav-bar>
    <div class="change">
      <div class="box">
        <div class="left">旧密码</div>
        <div class="middle">
          <input
            type="password"
            name=""
            id=""
            v-if="isShow"
            @input="judge"
            v-model="updateMsg.oldPassword"
          />
          <input type="text" v-else @input="judge" v-model="updateMsg.oldPassword" />
        </div>
        <div class="right" @click="change">
          <img
            src="@/assets/img/example/取消.png"
            alt=""
            v-if="del"
            @click="clear"
          />
          <img src="@/assets/img/example/隐藏.png" alt="" v-if="isShow" />
          <img src="@/assets/img/example/显示.png" alt="" v-else />
        </div>
      </div>
      <div class="box">
        <div class="left">新密码</div>
        <div class="middle">
          <input
            type="password"
            v-if="isShow2"
            v-model="updateMsg.newPassword"
            @input="judge"
          />
          <input type="text" v-else v-model="updateMsg.newPassword" @input="judge" />
        </div>
        <div class="right" @click="change2">
          <img
            src="@/assets/img/example/取消.png"
            v-if="del2"
            @click="clear2"
          />
          <img src="@/assets/img/example/隐藏.png" alt="" v-if="isShow2" />
          <img src="@/assets/img/example/显示.png" alt="" v-else />
        </div>
      </div>
      <div class="tips">
        密码长度6-11位，需要包含数字字母，符号至少2种或以上元素
      </div>
      <div class="button" :class="{ active: isActive }" @click="gochange">
        确认
      </div>
    </div>
  </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";
export default {
  components: {
    navBar,
  },
  data() {
    return {
      isShow: true,
      isShow2: true,
      del: false,
      del2: false,
      isActive: false,
      updateMsg: {
        oldPassword: "",
        newPassword: "",
      },
    };
  },
  mounted() {},
  methods: {
    goback() {
      this.$router.back();
    },
    change() {
      this.isShow = !this.isShow;
    },
    change2() {
      this.isShow2 = !this.isShow2;
    },
    judge() {
      if (this.updateMsg.oldPassword) {
        this.del = true;
      } else {
        this.del = false;
      }
      if (this.updateMsg.newPassword) {
        this.del2 = true;
      } else {
        this.del2 = false;
      }
      if (this.updateMsg.oldPassword && this.updateMsg.newPassword) {
        this.isActive = true;
      } else {
        this.isActive = false;
      }
    },
    clear() {
      this.updateMsg.oldPassword = "";
      this.del = false;
      this.change();
    },
    clear2() {
      this.updateMsg.newPassword = "";
      this.del2 = false;
      this.change2();
    },
    gochange() {
      this.$store.dispatch("updateUserInfo",this.updateMsg).then(() => {
        console.log(11);
        this.exist()
      });
    },
     exist() {
      this.$store.dispatch("logout");
      setTimeout(() => {
        console.log(2)
        this.$router.push("/loging");
      }, 200);
    },
  },
};
</script>
<style scoped>
.changePassword {
  position: relative;
  z-index: 15;
  background-color: white;
  height: 100vh;
}
.left img {
  width: 30px;
  height: 30px;
}
.change {
  margin-top: 70px;
  height: 250px;
  /* background-color: red; */
  padding: 0px 35px;
}
.box {
  height: 50px;
  /* background-color: green; */
  border-bottom: 1px solid gray;
  display: flex;
  justify-content: center;
  align-items: center;
  padding-top: 20px;
}
.box .left {
  width: 70px;
}
.box .middle {
  flex: 1;
}
.box .middle input {
  width: 100%;
  height: 100%;
  outline: none;
  border: 0px;
}
.box .right {
  width: 70px;
  text-align: right;
}
.box .right img {
  width: 25px;
  height: 25px;
}
.box .right img:nth-child(1) {
  margin-right: 20px;
}
.tips {
  font-size: 13px;
  color: gray;
}
.button {
  height: 50px;
  background-color: #9ad8fa;
  margin-top: 30px;
  color: white;
  text-align: center;
  line-height: 50px;
}
.active {
  background-color: #1facf8;
}
</style>
