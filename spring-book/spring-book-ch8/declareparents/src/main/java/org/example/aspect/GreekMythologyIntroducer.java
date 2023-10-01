package org.example.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.example.bean.Bird;
import org.example.bean.IBird;
import org.springframework.stereotype.Component;

/**
 * User: mertcaliskan
 * Date: 05/07/14
 */
@Component
@Aspect
public class GreekMythologyIntroducer {
    @DeclareParents(value = "org.example.bean.Pegasus+", defaultImpl = Bird.class)
    public static IBird iBird;
}
