import Vue from 'vue'
import Router from 'vue-router'
import axios from "../axios";
import store from "../store"

Vue.use(Router)

const router = new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      name: 'Home',
      component: () => import('@/views/Home.vue'),
      children: [
        {
          path: '/index',
          name: 'Index',
          meta: {
            title: "首页"
          },
          component: () => import('@/views/index.vue')
        }

      ]
    },
    {
      path: "/login",
      name: "Login",
      component: () => import("../views/login")
    }

  ]
})
//在跳转路由之前拿到权限
router.beforeEach((to, from, next) => {
  let hasRoute = store.state.menus.hasRoutes
  // console.log(hasRoute + "这是什么")
//  获取token
  let token = localStorage.getItem("token")
  //如果连接是login就跳转到登录页面
  if (to.path == '/login') {
    next()
  }
  //如果token为空就跳转到登录页
  else if (!token) {
    next({path: '/login'})
  }
  //如果有token没有加载过菜单数据
  else if (token && !hasRoute) {
    //  获取菜单导航和权限
    axios.get("/sys/menu/nav", {
      headers: {
        Authorization: localStorage.getItem("token")
      }
    }).then(res => {
      // console.log(res.data.data)
      //  拿到菜单数据menuList
      store.commit("setMenuList", res.data.data.nav)
      //  拿到用户权限permList
      store.commit("setPermList", res.data.data.authoritys)
      hasRoute=true
      store.commit("changeRouteStatus",hasRoute)
    })
  }
  next()
})

export default router
