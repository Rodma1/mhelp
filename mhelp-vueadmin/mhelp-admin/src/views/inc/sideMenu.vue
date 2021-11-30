<template>
  <el-menu class="el-menu-vertical-demo"
           background-color="#545c64" text-color="#fff"
           :default-active="activeMenu"
           active-text-color="#ffd04b">
    <router-link to="/index">
      <el-menu-item index="Index">
        <template slot="title"><i class="el-icon-s-home"></i> <span slot="title">首页</span></template>
      </el-menu-item>
    </router-link>
    <el-submenu :index="menu.name"  v-for="menu in menuList" :key="menu.id">
      <template slot="title"><i :class="menu.icon"></i> <span>{{ menu.title }}</span></template>
      <!-- 子： 响应点击链接-->
      <router-link :to="item.path" v-for="item in menu.children" :key="item.id">
        <el-menu-item :index="item.name" @click="selectMenu(item)">
          <template slot="title"><i :class="item.icon"></i> <span slot="title">{{ item.title }}</span></template>
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
      // //获取菜单
      // menuList: this.$store.state.menus.menuList
    }
  },
  //每次刷新都能获取
  computed:  {
    menuList: {
      get() {
        return this.$store.state.menus.menuList
      }
    },
  //  选择tab标签时候顺便也要激活当前对应的导航
    activeMenu(){
      return this.$store.state.menus.editableTabsValue
    }
  },
//  定义方法
  methods:{
    selectMenu(item){
      // console.log(item)
      //定义名字和标题
      let obj={
        name:item.name,
        title:item.title
      }
    // 提交到store的addTabs方法
      this.$store.commit("addTabs",obj)
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
