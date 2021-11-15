<template>
  <el-aside width="300px">

    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="1">
        <span slot="title">多帮及时服务平台</span>
      </el-menu-item>
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>任务管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="1-1">已发布的任务</el-menu-item>
          <el-menu-item index="1-2">已接受的任务</el-menu-item>
          <el-menu-item index="1-2">发布新任务</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="2">
        <i class="el-icon-menu"></i>
        <span slot="title">消息通知</span>
      </el-menu-item>
      <el-menu-item index="3" disabled>
        <i class="el-icon-document"></i>
        <span slot="title">导航三</span>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-setting"></i>
        <span slot="title">导航四</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
  name: "BaseAside"
}
</script>

<style scoped>
.el-menu-vertical-demo {
  width: 299px;
  min-height: 850px;
}
</style>
