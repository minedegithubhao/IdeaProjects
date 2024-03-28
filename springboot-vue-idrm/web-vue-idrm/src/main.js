import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css'
import axios from "axios";

Vue.prototype.$axios = axios //全局注册，使用方法为:this.$axios
Vue.config.productionTip = false
Vue.use(ElementUI, {size:'small'});
Vue.prototype.$httpUrl = 'http://localhost:8090'

new Vue({
    render: h => h(App),
}).$mount('#app')
