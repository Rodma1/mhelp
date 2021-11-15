// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
//导入element-ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';

import {formatTime} from "./utils/time";
Vue.use(ElementUI)
Vue.config.productionTip = false
Vue.use(router);
// 格式话时间
Vue.filter('format', formatTime)
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
