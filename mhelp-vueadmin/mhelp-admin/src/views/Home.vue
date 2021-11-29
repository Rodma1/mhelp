<template>
  <el-container>
    <el-aside width="200px">Aside</el-aside>

    <el-container>
      <el-header>
        <Strong>多帮后台管理系统</Strong>
        <!--          头像-->
        <div class ="header-avater">
          <el-avatar :src="userInfo.avatar"></el-avatar>
          <el-dropdown>
            <span class="el-dropdown-link">
                {{userInfo.username}}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人中心</el-dropdown-item>
              <el-dropdown-item divided >退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
<!--      所有连接都在home父级里面进行   margin: 0 15px-->
      <el-main style="margin: 0 15px">
        <div >
          <router-view></router-view>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "Home.vue",
  data() {
    return {
      userInfo: {
        id: '-1',
        username: 'admin',
        avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
      }
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
.header-avater{
  float:right;
  width: 200px;
  display: flex;
  /*水平居中*/
  align-items: center;
/* 垂直居中 */
  justify-content: space-around;

}

.el-header, .el-footer {
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
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
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
