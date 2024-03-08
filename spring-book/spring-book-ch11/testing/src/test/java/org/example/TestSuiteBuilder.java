package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.Set;

public class TestSuiteBuilder {
    public static Test suite() {
        TestSuite suite = new TestSuite();
        String packageName = "com.example"; // 指定包名
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(TestCase.class)); // 过滤条件

        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(packageName);
        for (BeanDefinition beanDefinition : candidateComponents) {
            try {
                Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
                suite.addTestSuite((Class<? extends TestCase>) clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return (Test) suite;
    }
}
