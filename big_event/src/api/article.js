//导入request.js中的请求工具
import request from '@/utils/request'

//注册接口函数
export const articleCategoryListService = () => {
    // 会以json的形式传递参数
    return request.get('/article')
}