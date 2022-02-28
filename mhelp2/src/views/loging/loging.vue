<template>
  <div class="loging">
    <div class="back" @click="back">
      <img src="@/assets/img/loging/返回.png" alt="" />
    </div>
    <div class="box">
      <ul class="title">
        <li :class="{ current: isActive }" @click="change1">登录</li>
        <li @click="change2" :class="{ current: !isActive }">注册</li>
      </ul>
      <form action="" class="content1" v-if="isActive" :model="userForm" :rules="rules" ref="userForm">
        <table>
          <tr>
            <td>用户名:</td>
            <td>
              <input type="text" name="" id="" v-model="userForm.account" @blur="rules"/>
            </td>
          </tr>
          <tr>
            <td>密码:</td>
            <td><input type="password" v-model="userForm.password" /></td>
          </tr>
        </table>
        <input type="button" value="登录" class="btn" @click="toLogin()" />
      </form>
      <form action="" class="content2" v-else :modle="userForm2" ref="registForm">
        <table>
          <tr>
            <td>用户名:</td>
            <td><input type="text" name="" id="" v-model="userForm2.account"/></td>
          </tr>
          <tr>
            <td>校园名:</td>
            <td><input type="text" v-model="userForm2.nickname"/> </td>
          </tr>
          <tr>
            <td>密码:</td>
            <td><input type="password" v-model="userForm2.password"/></td>
          </tr>
        </table>
        <input type="button" value="注册" class="btn" @click="toRegist()"/>
      </form>
    </div>
  </div>
</template>
<script>

export default {
  data() {
    return {
      isActive: true,
      userForm: {
        account: "ad",
        password: "admins",
      },
      userForm2: {
        account: "",
        nickname: "",
        password: "",
      },
      rules: {
        account: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {max: 10, message: '不能大于10个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {max: 10, message: '不能大于10个字符', trigger: 'blur'}
        ]
      }
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
      // alert('aaaa')
      if(this.userForm.account.length==0||this.userForm.password.length==0||this.userForm.account.length>11||this.userForm.password.length>11){
        alert('用户名密码不能为空且长度不大于11位')
      }
      this.$store.dispatch("login", this.userForm).then(() => {
        this.$router.push('/home');
      })
    }, 
    toRegist(){
      if(this.userForm2.account.length==0||this.userForm2.password.length==0||this.userForm2.account.length>11||this.userForm2.password.length>11){
        alert('用户名密码不能为空且长度不大于11位')
      }
      this.$store.dispatch("regist", this.userForm2).then(() => {
        this.isActive=true
      });
    },
   
  },
};
</script>
<style scoped>
.loging {
  position: relative;
  height: 100vh;
  background: rgba(197, 196, 196, 10);
  z-index: 100;
}
.box {
  width: 300px;
  height: 250px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.title {
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
}
</style>
