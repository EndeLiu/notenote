// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import axios from 'axios'
import VueAxios from 'vue-axios'

import store from "./store";

import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

import $ from 'jquery'


Vue.config.productionTip = false
Vue.use(ElementUI);

axios.defaults.baseURL = "/api"
axios.defaults.withCredentials = true
Vue.use(VueAxios,axios);

Vue.use(mavonEditor)



router.beforeEach((to,from,next) =>{
  if(to.meta.requireAuth){
    if(store.state.currentUser !== null && store.state.currentUser){
      next()
    }
    else{
      next({
        path:'/login',
        query: {redirect: to.fullPath}
      })
    }
  }
  else{
    next()
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
