//导入request.js中的请求工具
import request from '@/utils/request'

//获取列表接口函数
export const articleCategoryListService = () => {
    // 会以json的形式传递参数
    return request.get('/category')
}

// 添加文章分类
export const addArticleCategoryService = (categoryModel) => {
    // 以post请求方式添加文章分类
    return request.post('/category', categoryModel)
}

// 修改文章分类
export const updateArticleCategoryService = (categoryModel) => {
    return request.put('/category', categoryModel)
}

// 删除文章分类
export const deleteArticleCategoryService = (id) => {
    return request.delete('/category?id=' + id)
}

// 文章列表数据
export const articleListService = (params) => {
    return request.get('/article', {params:params})
}