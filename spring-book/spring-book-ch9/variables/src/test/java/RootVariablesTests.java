import org.example.domain.MyBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by mertcaliskan
 * on 14/08/14.
 */
public class RootVariablesTests {

    ExpressionParser parser;

    @Before
    public void setup() {
        parser = new SpelExpressionParser();
    }

    @Test
    public void rootVariableRegisteredOK() {
        // 如果表达式中遇到了未知的方法或者属性，就会使用跟对象进行解析
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setRootObject(new MyBean());
//        assertTrue(parser.parseExpression("#root").getValue(context) instanceof MyBean);
        assertThat(parser.parseExpression("#root.property").getValue(context), is("property"));
    }
}
