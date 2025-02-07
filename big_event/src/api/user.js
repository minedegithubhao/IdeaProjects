//导入request.js中的请求工具
import request from '@/utils/request'

//注册接口函数
export const userRegisterService = (registerData) => {
    // 会以json的形式传递参数
    // return request.post('/user/register',registerData)
    // form表单借助urlSearchParam完成参数传递
    const params = new URLSearchParams();
    for (let key in registerData) {
        params.append(key, registerData[key])
    }
    return request.post('/user/register', new URLSearchParams(params))
}

// 登录接口函数
export const userLoginService = (registerData) => {
    const params = new URLSearchParams();
    for (let key in registerData) {
        params.append(key, registerData[key])
    }
    return request.post('/user/login', new URLSearchParams(params))
}

// 获取用户详细信息
export const getUserDetailService = () => {
    return request.get('/user/userInfo')
}