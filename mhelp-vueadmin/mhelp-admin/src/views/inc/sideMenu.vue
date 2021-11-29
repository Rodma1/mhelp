<template>
  <el-menu class="el-menu-vertical-demo" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
    <router-link to="/index">
      <el-menu-item index="Index">
        <template slot="title"><i class="el-icon-s-home"></i> <span slot="title">首页</span></template>
      </el-menu-item>
    </router-link>
    <el-submenu :index="menu.name" v-for="menu in menuList">
      <template slot="title"><i :class="menu.icon"></i> <span>{{ menu.title }}</span></template>
      <!-- 子： 响应点击链接-->
      <router-link :to="item.path" v-for="item in menu.children">
        <el-menu-item :index="item.name">
          <template slot="title"><i :class="item.icon"></i> <span slot="title">{{item.title}}</span></template>
        </el-menu-item>
      </router-link>
    </el-submenu>

  </el-menu>
</template>

<script>
export default {
  name: "sideMenu",
  data() {
    return {
      menuList: [{
        name: 'SysManga',
        title: '系统管理',
        icon: 'el-icon-s-operation',
        path: '',
        component: '',
        children: [{name: 'SysUser', title: '用户管理', icon: 'el-icon-s-custom', path: '/sys/users', children: []}]
      }, {
        name: 'SysTools',
        title: '系统工具',
        icon: 'el-icon-s-tools',
        path: '',
        children: [{name: 'SysDict', title: '数字字典', icon: 'el-icon-s-order', path: '/sys/dicts', children: []},]
      }],
    }
  }
}
</script>

<style scoped>
/*菜单栏*/
.el-menu-vertical-demo {
  height: 100%;
}
</style>
