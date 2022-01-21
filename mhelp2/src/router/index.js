import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const home = () => import('../views/home/home.vue');
const writeTask = () => import('../views/writeTask/writeTask.vue');
const message = () => import('../views/message/message.vue');
const my = () => import('../views/my/my.vue');
const loging=()=>import('../views/loging/loging.vue');
const school=()=>import ('../views/school/school.vue');
const signIn=()=>import ('../views/signIn/signIn.vue');
const notice=()=>import ('../views/message/messageView/notice.vue');
const information=()=>import('../views/message/messageView/information.vue');
const writeTaskView=()=>import('../views/writeTask/writeTaskView/writeTaskView.vue');
const writeTaskHistory=()=>import('../views/writeTask/writeTaskView/writeTaskHistory.vue');
const taskList=()=>import('../views/taskList/taskList.vue');
const pakege=()=>import('../views/pakege/pakege.vue');
const shoppingmall=()=>import('../views/shoppingmall/shoppingmall.vue')
const more=()=>import('../views/more/more.vue')
const publish=()=>import ('../views/publish/publish.vue')
const publishTask=()=>import ('../views/publish/publishView/publishTask.vue')
const publishHistory=()=>import('../views/publish/publishView/publishHistory.vue')
const detail=()=>import ('../views/detail/detail.vue')
const chat=()=>import('../views/message/chat.vue')
const bills=()=>import('../views/bills/bills.vue')
import {getToken} from "network/token.js";
import store from '@/store';
import {Message} from 'element-ui';
const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'Home',
    //切记此处是component，而不是components
    component: home,
    meta: {
      login_require: false
    }
  },
  {
    path: '/writeTask',
    name: 'writeTask',
    component: writeTask,
    children:[
      {
        path:'writeTaskView',
        name:'writeTaskView',
        component:writeTaskView
      },
      {
        path:'writeTaskHistory',
        name:'writeTaskHistory',
        component:writeTaskHistory
      }
    ], 
    meta: {
      login_require: true
    }
  },
  {
    path: '/message',
    name: 'Message',
    component: message,
    children:[
      // {
      //   path: '/',
      //   redirect: '/information'
      // },
      {
        path:'information',
        name:'information',
        component:information,
      },
      {
        path:'notice',
        name:'notice',
        component:notice
      },
      
    ],
    meta: {
      login_require: false
    }
  },
  {
    path: '/my',
    name: 'My',
    component: my,
    meta: {
      login_require: false
    }
  },
  {
    path:'/loging',
    name:'Loging',
    component:loging
  },
  {
    path:'/school',
    name:'school',
    component: school,
    meta: {
      login_require: true
    }
  },
  {
    path:'/signIn',
    name:'signIn',
    component:signIn,
    meta: {
      login_require: false
    }
  },
  {
    path:'/taskList/:id',
    name:'taskList',
    component:taskList,
    meta: {
      login_require: true
    }
  },
  {
    path:'/pakege',
    name:'pakege',
    component:pakege,
    meta:{
      login_require: true
    }
  },
  {
    path:'/shoppingmall',
    name:'packge',
    component:shoppingmall,
    meta:{
      login_require: false
    }
  },
  {
    path:'/more',
    name:'more',
    component:more,
    meta:{
      login_require: true
    }
  },
  {
    path:'/bills',
    name:'bills',
    component:bills,
    meta:{
      login_require:true
    }
  },
  {
    path:'/publish',
    name:'publish',
    component:publish,
    children:[
      {
        path:'publishTask',
        name:'publishTask',
        component:publishTask,
      },
      {
        path:'publishHistory',
        name:'publishHistory',
        component:publishHistory
      },
    ],
    meta:{
      login_require: false
    }
  },
  {
    path:'/detail',
    name:'detail',
    component:detail,
    meta:{
      login_require: false
    }
  },
  {
    path:'/chat',
    name:'chat',
    component:chat,
    meta:{
      login_require:true
    }
  },
]
const router = new VueRouter({
  routes
})
router.beforeEach((to,from,next)=>{
  if(getToken()){
    // if(to.path=='/loging'){
    //   next('/loging')
      
    // }
    // else{
      if(store.state.account.length==0){
        store.dispatch('getUserInfo').then(()=>{
          next()
          // console.log(store.state.account)
        })
        .catch(()=>{
          // next('/loging');
          Message({
            type: 'warning',
            showClose: true,
            message: '登录已过期'
          })
          next('/loging');
        })
      }
      else{
        next()
      }
    // }
  }
  else{
    if (to.matched.some(function (item) {
      return item.meta.login_require
    })) {
      next('/loging')
    } 
    else 
    {
      next()
    }
      
  }
})
export default router
