//导入request.js中的请求工具
import request from '@/utils/request'
import { useTokenStore } from '@/stores/token.js' //导入token

//注册接口函数
export const articleCategoryListService = () => {
    const tokenStore = useTokenStore()
    // 会以json的形式传递参数
    return request.get('/category',{headers:{'Authorization':tokenStore.token}})
}