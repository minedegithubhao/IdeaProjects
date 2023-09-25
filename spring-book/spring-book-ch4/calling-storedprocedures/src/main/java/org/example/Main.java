package org.example;

import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class Main {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ch4Configuration.class);

        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

        simpleJdbcCall
                //指定存储过程的名称
                .withProcedureName("concat")
                .withoutProcedureColumnMetaDataAccess()
				// 声明输入输出参数
                .declareParameters(
                        new SqlParameter("param1", Types.VARCHAR),
                        new SqlParameter("param2", Types.VARCHAR)).
				// 返回值RowMapper映射
                returningResultSet("result", new SingleColumnRowMapper<String>(String.class));
		// 编译
        simpleJdbcCall.compile();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("param1", "hello ");
        paramMap.put("param2", "world!");
		// 执行存储过程
        Map<String, Object> resultMap = simpleJdbcCall.execute(paramMap);


        List<String> resultList = (List<String>) resultMap.get("result");
        for (String value : resultList) {
            System.out.println(value);
        }

    }
}
