import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
//定义存储信息的模块
export default {
  state: {
    //  菜单栏数据
    menuList: [],
    //  权限数据
    permList: [],
    //通过hasRoute来动态判断是否已经加载过菜单
    hasRoutes: false,
    editableTabsValue:'Index',
    editableTabs: [{
      title: '首页',
      name: 'Index'
    }]


  },
  //定义事件
  mutations: {
    //g改变hasRoute状态
    changeRouteStatus(state, hasRoutes) {
      state.hasRoutes = hasRoutes;
      //在session里面设置
      sessionStorage.setItem("hasRoutes", hasRoutes)
    },
    //设置菜单列表
    setMenuList(state, menus) {
      state.menuList = menus;
    },
    //  设置权限列表
    setPermList(state, authoritys) {
      state.permList = authoritys
    }

  }
}
