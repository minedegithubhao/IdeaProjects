import './assets/main.scss' // 引入scss
import { createApp } from 'vue' // 引入vue
import ElementPlus from 'element-plus' // 引入element-plus
import 'element-plus/dist/index.css' // 引入element-plus样式
import App from './App.vue' // 引入App.vue
import router from '@/router' // 引入router
import { createPinia } from 'pinia'// 引入pinia
import { createPersistedState } from 'pinia-persistedstate-plugin'//导入Pinia持久化插件-persist

const app = createApp(App) // 创建vue实例
const pinia = createPinia() // 创建pinia实例
const persist = createPersistedState() // 创建Pinia持久化插件-persist实例

app.use(ElementPlus) // 使用element-plus
app.use(router) // 使用router
app.use(pinia)//使用pinia
pinia.use(persist)//使用Pinia持久化插件-persist
app.mount('#app') // 挂载vue实例