// 导入vue-roter
import { createRouter, createWebHistory } from 'vue-router'

// 导入组件
import LoginVue from '@/views/Login.vue';
import Layout from '@/views/Layout.vue';

//定义路由关系
const routes = [
    {
        path: '/login',
        component: LoginVue
    },
    {
        path: '/',
        component: Layout
    }
]

// 创建路由实例
const router = createRouter({
    history: createWebHistory(),
    routes:routes
})

//导出路由
export default router;
