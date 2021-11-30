<template>
  <el-container>
    <el-aside width="200px">
      <side-menu></side-menu>
    </el-aside>

    <el-container>
      <el-header>
        <Strong>多帮后台管理系统</Strong>
        <!--          头像-->
        <div class="header-avater">
          <el-avatar :src="userInfo.avatar"></el-avatar>
          <el-dropdown>
            <span class="el-dropdown-link">
                {{ userInfo.username }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item ><router-link :to="{name:'UserCenter'}">个人中心</router-link></el-dropdown-item>
              <el-dropdown-item divided @click.native="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <!--      所有连接都在home父级里面进行   margin: 0 15px：上下为零，左右偏移15-->
      <el-main >
<!--        使用组件-->
        <Tabs></Tabs>
        <div style="margin: 0 15px">
          <router-view></router-view>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideMenu from "./inc/sideMenu";
import Tabs from "./inc/Tabs";
export default {
  name: "Home.vue",
  components: {SideMenu,Tabs},
  data() {
    return {
      userInfo: {
        id: '-1',
        username: 'admin',
        avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
      }
    }
  },
//  进入页面就刷新
  created(){
    this.getUserInfo()
  },
  methods:{
    //获取用户信息:目前还没写
    getUserInfo(){
      this.$axios.get("/sys/user/info/1").then(res=>{
        this.userInfo=res.data.data;
      })
    },
  //  退出登录
    logout(){
      this.$axios.post("/logout").then(res=>{
        console.log(res.data.data)
        localStorage.clear();
        sessionStorage.clear()
      //  清空
        this.$store.commit("resetState")
      //  跳转到登录页
        this.$router.push("/login")
      })
    }
  }


}
</script>

<style scoped>
.el-container {
  padding: 0;
  margin: 0;
  height: 100%;
}

/*下拉菜单*/
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

.el-icon-arrow-down {
  font-size: 12px;
}

/*头像和头部功能*/
.header-avater {
  float: right;
  width: 200px;
  display: flex;
  /*水平居中*/
  align-items: center;
  /* 垂直居中 */
  justify-content: space-around;

}


.el-header {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
   color: #333;
  padding: 0;
}


body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
