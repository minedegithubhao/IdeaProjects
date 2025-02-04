//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
import { ElMessage } from 'element-plus'
import { useTokenStore } from '@/stores/token.js' //导入token
import router from '@/router/index.js'

//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = '/api';
const instance = axios.create({ baseURL })

//添加请求拦截器
instance.interceptors.request.use(
    config => {
        //在请求头中添加token
        let tokenStore = useTokenStore()
        if (tokenStore.token) {
            config.headers.Authorization = tokenStore.token;
        }
        return config;
    },
    err => {
        // 请求失败
        return Promise.reject(err);
    }
)

//添加响应拦截器
instance.interceptors.response.use(
    result => {
        if (result.data.code === 0) {
            return result.data;
        }
        ElMessage.error(result.msg ? result.msg : '服务异常')
        return Promise.reject(result.data);//异步的状态转化成失败的状态
    },
    err => {
        // 如果响应码是401未授权，则跳转到登录页面
        if (err.response.status === 401) {
            //跳转到登录页面
            ElMessage.error('请先登录！')
            router.push('/login')
        } else {
            ElMessage.error('服务异常')
        }
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

export default instance;