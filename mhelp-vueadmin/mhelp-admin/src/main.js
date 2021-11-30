// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
Vue.config.productionTip = false
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import request from "./axios"
import gobal from "./globalFun"
Vue.prototype.$store=store
Vue.prototype.$axios=request
Vue.use(Element);
Vue.use(router);
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
