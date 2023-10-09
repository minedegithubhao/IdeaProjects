package org.example.demo;

/**
 * 观察者2 实现MyObserver接口
 */
public class ConcreteObeser2 implements MyObserver{

    private MySubject subject;

    /**
     * 创建观察者时必须指定主题
     * @param subject 主题
     */
    public ConcreteObeser2(MySubject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(Object obj) {
        System.out.println("观察者2发现数据有变化，变化值为：" + obj);
    }

    /**
     * 取消订阅
     */
    public void remove(){
        subject.removeObserver(this);
    }
}
