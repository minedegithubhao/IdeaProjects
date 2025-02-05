//导入request.js中的请求工具
import request from '@/utils/request'

//获取列表接口函数
export const articleCategoryListService = () => {
    // 会以json的形式传递参数
    return request.get('/category')
}

export const addArticleCategoryService = (categoryModel) => {
    // 以post请求方式添加文章分类
    return request.post('/category', categoryModel)
}