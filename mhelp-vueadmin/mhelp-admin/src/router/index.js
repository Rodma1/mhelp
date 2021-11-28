import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({
  mode:"history",
  routes: [
    {
      path:"/login",
      name:"Login",
      component:()=>import("../views/login")
    }

  ]
})

export default router
