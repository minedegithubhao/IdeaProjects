package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ASUS
 * @version 1.0
 * @description: @Configuration 告诉spring，这是一个配置类，该类配置了元数据，他相当于<beans><beans/>标签
 * @date 2023/9/21 6:17
 */
@Configuration
public class Ch2BeanConfiguration {

    /**
     * 每一个@Bean都对应一个<bean>标签
     * <bean id="accountService" class="com.wiley.beginningspring.ch2.AccountServiceImpl">
     * 		<property name="accountDao" ref="accountDao"/>
     * 	</bean>
     * id 对应 方法名称 accountService
     * class 对应 返回值类型 AccountService
     * @return
     */
    @Bean
    public AccountService accountService(){
        AccountServiceImpl bean = new AccountServiceImpl();
        bean.setAccountDao(accountDao());
        return bean;
    }

    @Bean
    public AccountDao accountDao(){
        AccountDaoInMemoryImpl bean = new AccountDaoInMemoryImpl();
        return bean;
    }
}
