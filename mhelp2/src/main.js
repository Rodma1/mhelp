import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import {pinyin} from "pinyin-pro"
import { IndexBar, IndexAnchor,Cell,Swipe, SwipeItem, Uploader,Toast,button} from 'vant';
Vue.use(IndexBar );
Vue.use(IndexAnchor);
Vue.use(Swipe);
Vue.use(SwipeItem);
Vue.use(Cell);
Vue.use( Uploader);
Vue.use(Toast);
Vue.use(button);
Vue.config.productionTip = false;
Vue.prototype.$bus=new Vue();
Vue.use(pinyin)
// Vue.prototype.app=app;
new Vue({
  router,
  store,

  render: h => h(App)
}).$mount('#app')
