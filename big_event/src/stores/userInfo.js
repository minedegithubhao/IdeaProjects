import { defineStore } from 'pinia'; // 导入pinia
import { ref } from 'vue';//导入ref

// 定义userInfoStore
const useUserInfoStore = defineStore('userInfo', () => {
    // 定义userInfo
    const info = ref({})

    // 定义修改userInfo的方法
    const setUserInfo = (newUserInfo) => {
        info.value = newUserInfo
    }

    // 定义删除userInfo的方法
    const removeUserInfo = () => {
        info.value = {}
    }

    return { info, setUserInfo, removeUserInfo }
}, {
    persist: true // Pinia持久化插件-persist持久化存储
})

export default useUserInfoStore;
