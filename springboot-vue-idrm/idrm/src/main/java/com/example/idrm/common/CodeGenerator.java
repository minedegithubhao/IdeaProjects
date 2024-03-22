package com.example.idrm.common;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author cxdpc
 * @date 2024/3/22 15:00
 */
public class CodeGenerator {

    private static final String password = "root";
    private static final String username = "root";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/idrm?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
    private static final String finalProjectPath = Objects.requireNonNull(CodeGenerator.class.getClassLoader().getResource("")).getPath().replace("/target/classes/", "");
    private static final List<String> tableList = Arrays.asList("s_user");

    public static void main(String[] args) {

        // 自动创建tableList中写死的表
//        creteModel();

        // 手动输入（互动式）
        createSingleModel();
    }

    private static void creteModel() {
        // dataSourceConfig数据源
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("cxd") // 设置作者
                            .enableSwagger() // 开启 swagger 模式/**/
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir() //禁止打开输出目录
                            .outputDir(finalProjectPath + "/src/main/java"); // 指定输出目录
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.example.idrm")  //设置父包名
                            .controller("controller") //设置controller包名
                            .service("service") //设置service包名
                            .serviceImpl("service.impl") //设置impl包名
                            .entity("entity") //设置entity包名
                            .mapper("mapper") //设置mapper包名
                            .other("other") //其他包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, finalProjectPath + "/src/main/resources/mapper")); // 设置mapperXml生成路径

                })
                // 策略配置
                .strategyConfig(builder -> {

                    // 凡在tableList中的表，每次执行都会重新覆盖生成文件
                    if (!CollectionUtils.isEmpty(tableList)) {
                        builder.addInclude(tableList);
                    }

                    builder.addTablePrefix("s_");// 设置过滤表前缀，例如：表名s_user，生成的实体为User

                    builder.controllerBuilder() // controller配置
                            .enableRestStyle() // 开启生成@RestController控制器
                            .fileOverride(); //覆盖已有文件

                    builder.serviceBuilder() // service配置
                            .formatServiceFileName("%sService") //格式化service接口文件名称
                            .formatServiceImplFileName("%sServiceImpl") //格式化service实现类文件名称
                            .fileOverride(); //覆盖已有文件

                    builder.entityBuilder() // entity配置
                            .enableRemoveIsPrefix() // 开启Boolean类型字段移除is前缀
                            .enableTableFieldAnnotation() // 开启生成实体时生成字段注解
                            .enableLombok() // 开启lombok注释
                            .fileOverride();//覆盖已有文件

                    builder.mapperBuilder() // mapper配置
                            .enableBaseResultMap() //开启baseResultMap
                            .enableMapperAnnotation() //开启 @Mapper 注解
                            .formatMapperFileName("%sMapper") //格式化Mapper文件名称
                            .formatXmlFileName("%sMapper") //格式化Xml文件名称
                            .enableBaseColumnList() //开启baseColumnList
                            .fileOverride(); //覆盖已有文件
                })
                // 自定义模版引擎
                /*.injectionConfig(consumer -> {
                    Map<String, String> customFile = new HashMap<>();
                    customFile.put("DTO.java", "/templates/entityDTO.java.ftl"); //自定义模版引擎
                    consumer.customFile(customFile);
                })*/
                // 选择模板引擎
                .templateEngine(new VelocityTemplateEngine())
                // 执行
                .execute();
    }

    private static void createSingleModel() {
        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig((scanner, builder) ->
                        builder.author(scanner.apply("请输入作者名称？"))
                                .fileOverride()
                                .outputDir(finalProjectPath + "/src/main/java"))
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.example.idrm")  //设置父包名
                            .controller("controller") //设置controller包名
                            .service("service") //设置service包名
                            .serviceImpl("service.impl") //设置impl包名
                            .entity("entity") //设置entity包名
                            .mapper("mapper") //设置mapper包名
                            .other("other") //其他包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, finalProjectPath + "/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig((scanner, builder) ->
                        builder.addTablePrefix("s_")// 设置过滤表前缀
                                .addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                                .controllerBuilder()
                                .enableRestStyle() //开启生成@RestController控制器
                                .entityBuilder()
                                .enableLombok() // 启用lombok，添加@Getter和@Setter注解
                                .mapperBuilder()
                                .enableMapperAnnotation() //添加@Mapper注解
                                .enableBaseResultMap() //开启baseResultMap映射
                                .enableBaseColumnList() //开启baseColumnList
                                .build())
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
