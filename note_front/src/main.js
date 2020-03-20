// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.config.productionTip = false
Vue.use(ElementUI);


axios.defaults.baseURL = "/api"
axios.defaults.withCredentials = true
Vue.use(VueAxios,axios);

// Vue.prototype.$axios=axios;
// // 跨域相关
// Axios.defaults.baseURL="/api"





/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
