<template>
  <div class="regist">
    <nav-bar>
      <div slot="left" class="left" @click="goback">
        <img src="@/assets/img/example/back.png" alt="" />
      </div>
      <div slot="center">注册</div>
    </nav-bar>
    <div class="content">
      <div class="logo">
        <img src="@/assets/img/loging/hand.png" alt="" />
      </div>
      <div class="box">
        <div class="item" :class="{ isActive: isTips }">
          <img src="@/assets/img/loging/name.png" alt="" />
          <input type="text" name="" id="" v-model="userForm.account" />
        </div>
        <div class="tips" v-if="isTips">
          <img src="@/assets/img/loging/tips.png" alt="" />
          字符要大于0小于10
        </div>
        <div class="item" :class="{ isActive: isTips }">
          <img src="@/assets/img/loging/nickname.png" alt="" />
          <input type="text" name="" id="" v-model="userForm.nickname" />
        </div>
        <!-- <div class="tips"></div> -->
        <div class="item">
          <img src="@/assets/img/loging/school.png" alt="" />
          <select v-model="userForm.school">
            <option v-for="(item, index) in schoolList" :key="index"  >
            {{ item.name }}
          </option>
          </select>
        </div>
        <div class="item" :class="{ isActive: isTips }">
          <img src="@/assets/img/loging/password.png" alt="" />
          <input type="password" name="" id="" v-model="userForm.password" />
        </div>
        <div class="tips" v-if="isTips2">
          <img src="@/assets/img/loging/tips.png" alt="" />
          字符要大于0小于10
        </div>
      </div>
      <div class="registBtn" :class="{ isActive2: isTips2 }" @click="toRegist">
        注册
      </div>
      <div class="toLoging">登录</div>
    </div>
  </div>
</template>
<script>
import navBar from "components/common/navbar/navbar.vue";
import { schools } from "network/task.js";
import { pinyin } from "pinyin-pro";
export default {
  components: {
    navBar, 
  },
  data() {
    return {
      isTips: false,
      isTips2: false,
      userForm: {
        account: "",
        nickname: "",
        password: "",
        school:""
      },
      schools: [],
      schoolIndexBar: [],
      schoolNames: [],
      schoolList: [],
    };
  },
  mounted(){
    this.school()
  },
  methods: {
    toRegist() {
      this.rule();
      console.log(this.userForm.school)
      this.$store.dispatch("regist", this.userForm).then(() => {
        this.isActive = true;
        this.$router.back()
      });
    },
    rule() {
      if (
        this.userForm.account.length == 0 ||
        this.userForm.password.length > 11
      ) {
        this.isTips = true;
      } else {
        this.isTips = false;
      }
      if (
        this.userForm.password.length == 0 ||
        this.userForm.password.length > 11
      ) {
        this.isTips2 = true;
      } else {
        this.isTips2 = false;
      }
    },
    goback(){
      this.$router.back()
    },
    school(){
     schools().then((res) => {
        console.log(res);
        this.schools = res.data;
        for (var i = 0; i < this.schools.length; i++) {
          this.schoolNames[i] = this.schools[i].name;
          var school = pinyin(this.schools[i].name).substr(0, 1).toUpperCase();
          this.schoolIndexBar[i] = school;
        }
        this.schoolIndexBar = this.schoolIndexBar.sort();
        this.schoolIndexBar = this.unique(this.schoolIndexBar);
        this.schoolIndexBar.forEach((item) => {
          this.schools.forEach((i) => {
            if (pinyin(i.name).substr(0, 1).toUpperCase() == item) {
              this.schoolList.push(i);
            }
          });
        });
       console.log(this.schoolList)
      });
    },
    unique(origin) {
      const result = [];
      const set = new Set();
      for (const i of origin) {
        if (!set.has(i)) {
          result.push(i);
          set.add(i);
        }
      }
      return result;
    },
  },
};
</script>

<style scoped>
.regist {
  position: relative;
  height: 100vh;
  z-index: 15;
  background-color: white;
}
.left img {
  width: 25px;
  height: 25px;
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
  transform: translate(0, -50%);
}
.item {
  height: 50px;
  display: flex;
  margin-bottom: 20px;
  margin-right: 10px;
}
.box .item:nth-child(4) {
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
.registBtn {
  height: 40px;
  background-color: #1296db;
  text-align: center;
  line-height: 40px;
  margin: 30px 0px 8px 10px;
  color: white;
  font-size: 18px;
}
.toLoging {
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
.box .isActive {
  margin-bottom: 0px;
}
.content .isActive2 {
  margin-top: 10px;
}
.item select{
  width: 100%;
  border: 1px solid gray;
  outline: 1px solid #1296db;
}
</style>