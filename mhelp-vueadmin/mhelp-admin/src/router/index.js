import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({
  mode:"history",
  routes: [
    {
      path:'/',
      name:'Home',
      component:()=>import('@/views/Home.vue'),
      children:[
        {
          path:'/index',
          name:'Index',
          meta:{
            title:"首页"
          },
          component:()=>import('@/views/index.vue')
        }

      ]
    },
    {
      path:"/login",
      name:"Login",
      component:()=>import("../views/login")
    }

  ]
})

export default router
