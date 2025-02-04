import { defineStore } from 'pinia'; // 导入pinia
import { ref } from 'vue'; // 导入ref

//创建token存储
export const useTokenStore = defineStore('token', () => {
    // 定义token
    const token = ref('')

    // 定义修改token的方法
    const setToken = (newToken) => {
        token.value = newToken
    }

    // 定义删除token的方法
    const removeToken = () => {
        token.value = ''
    }

    return { token, setToken, removeToken }

}, {
    persist: true // Pinia持久化插件-persist持久化存储
}
)
