<template>
  <el-container>
    <el-aside width="200px">
      <el-menu class="el-menu-vertical-demo" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
        <router-link to="/index">
          <el-menu-item index="Index">
            <template slot="title"><i class="el-icon-s-home"></i> <span slot="title">首页</span></template>
          </el-menu-item>
        </router-link>
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-s-operation"></i> <span>系统管理</span></template>
          <el-menu-item index="1-1">
            <template slot="title"><i class="el-icon-s-custom"></i> <span slot="title">用户管理</span></template>
          </el-menu-item>
          <el-menu-item index="1-2">
            <template slot="title"><i class="el-icon-rank"></i> <span slot="title">角色管理</span></template>
          </el-menu-item>
          <el-menu-item index="1-3">
            <template slot="title"><i class="el-icon-menu"></i> <span slot="title">菜单管理</span></template>
          </el-menu-item>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-s-tools"></i> <span>系统工具</span></template>
          <el-menu-item index="2-2">
            <template slot="title"><i class="el-icon-s-order"></i> <span slot="title">数字字典</span></template>
          </el-menu-item>
        </el-submenu>
      </el-menu>
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
              <el-dropdown-item>个人中心</el-dropdown-item>
              <el-dropdown-item divided>退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <!--      所有连接都在home父级里面进行   margin: 0 15px-->
      <el-main style="margin: 0 15px">
        <div>
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
.header-avater {
  float: right;
  width: 200px;
  display: flex;
  /*水平居中*/
  align-items: center;
  /* 垂直居中 */
  justify-content: space-around;

}
/*菜单栏*/
.el-menu-vertical-demo{
  height: 100%;
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
