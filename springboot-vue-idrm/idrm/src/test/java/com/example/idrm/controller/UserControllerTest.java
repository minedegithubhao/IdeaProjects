package com.example.idrm.controller;

import com.example.idrm.IdrmApplication;
import com.example.idrm.IdrmApplicationTestBase;
import com.example.idrm.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author cxdpc
 * @date 2024/4/12 09:22
 */
class UserControllerTest extends IdrmApplicationTestBase {

    /**
     * 无参Get
     *
     * @throws Exception
     */
    @Test
    void list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/list")
                        .header("Authorization", "Bearer ...")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 带一个参数的Get
     * 参数化测试
     * 参考Junit5官网文档：<a href="https://doczhcn.gitbook.io/junit5/index/index-2/parameterized-tests">...</a>
     * 一些常用的测试：<a href="https://zhuanlan.zhihu.com/p/61342833">...</a>,
     * <a https://blog.csdn.net/qq_39339588/article/details/130945684></a>
     * <a href="https://blog.csdn.net/qq_39339588/article/details/130945684">...</a>
     *
     * @param no 账户名
     * @throws Exception 接口抛出异常就会测试失败
     */
    @ParameterizedTest
    @CsvSource({"admin", "cxd", "cxdxiu12"})
    void getUserByNo(String no) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserByNo")
                        // param: 添加request的参数，例如http://localhost:8090/user/getUserByNo?no=admin
                        .param("no", no)
                        .header("Authorization", "Bearer ...")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 带多个参数的Get
     * 从CSV中读取信息
     *
     * @param first  第一列
     * @param second 第二列
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/test.csv")
    void testWithCsvFileSource(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
        System.out.printf(first + "^|" + second);
    }

    /**
     * Json的Post请求
     *
     * @throws Exception
     */
    @Test
    void save() throws Exception {
        User user = new User();
        user.setNo("test1");
        user.setName("测试用户1");
        user.setAge(19);
        user.setPassword("test1");
        String requestContent = objectMapper.writeValueAsString(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 删除
     * @throws Exception
     */
    @Test
    void delete() throws Exception {
        /*mockMvc.perform(get("/user/{id}", 1)) //restful形式
                .andExpect(model().attributeExists("user")) //验证model
                .andExpect(view().name("user/view")) //验证视图viewName
                .andExpect(forwardedUrl("/WEB-INF/jsp/user/view.jsp"))//验证视图渲染时forward到的jsp
                .andExpect(status().isOk())//验证状态码
                .andDo(print()); //输出MvcResult到控制台*/
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete")
                        .param("id", "34")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    //自定义断言
    /*MvcResult result = mockMvc.perform(get("/user/{id}", 1))//执行请求
            .andReturn(); //返回MvcResult
    Assert.assertNotNull(result.getModelAndView().getModel().get("user")); */

    //执行文件上传
    /*byte[] bytes = new byte[] {1, 2};
    mockMvc.perform(fileUpload("/user/{id}/icon", 1L).file("icon", bytes))
            .andExpect(model().attribute("icon", bytes)) //验证属性相等性
            .andExpect(view().name("success")); //验证视图*/

}