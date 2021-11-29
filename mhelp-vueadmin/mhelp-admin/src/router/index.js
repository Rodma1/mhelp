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
      // console.log(store.state.menus.menuList)


      //  我们已经从后端获取到路由path了，我们现在动态绑定他
      let newRoutes = router.options.routes//定义路由管理器
      //遍历每一个菜单
      res.data.data.nav.forEach(menu => {
        //  如果有子菜单也要遍历
        if (menu.children) {
          menu.children.forEach(m => {
            //  转成路由
            let route = menuToRoute(m)
            //  如果路由route不为null
            if (route) {
              //  把路由添加到路由管理器中,Home
              newRoutes[0].children.push(route)
            }

          })
        }
      })
      // console.log(newRoutes)
      // console.log("hh")
    //  重新添加到上面的router中
      router.addRoutes(newRoutes)
    //  运行到这里说明我们已经遍历过一遍menus菜单了，将hasRoute改为true
      hasRoute=true
      store.commit("changeRouteStatus",hasRoute)
    })
  }
  next()
})

//导航转成路由
const menuToRoute = (menu) => {
  //如果没有导航，列："sys/User"。就返回空
  if (!menu.component) {
    return null;
  }
//  设定路由
  let route = {
    name: menu.name,
    //路由
    path: menu.path,
    //参数
    meta: {
      icon: menu.icon,
      title: menu.title
    }
  }
//  路由组件
  route.component = () => import('@/views/' + menu.component + '.vue')
  return route
}
export default router
