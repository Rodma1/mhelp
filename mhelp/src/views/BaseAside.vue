<template>
  <el-aside width="300px">

    <el-menu :router=true
      default-active="activeIndex"
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="/introduction">
        <span slot="title">多帮及时服务平台</span>
      </el-menu-item>
      <el-submenu index="">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>任务管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/subtasks">已发布的任务</el-menu-item>
          <el-menu-item index="/taskaccepted">已接受的任务</el-menu-item>
          <el-menu-item index="/ptask">发布新任务</el-menu-item>
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
  name: "BaseAside",
  props: {
    activeIndex: String,
  },
}
</script>

<style scoped>
.el-menu-vertical-demo {
  width: 299px;
  min-height: 850px;
}
</style>
