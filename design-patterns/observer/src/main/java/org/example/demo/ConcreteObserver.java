package org.example.demo;

/**
 * 观察者1，实现MyObserver接口
 */
public class ConcreteObserver implements MyObserver{

    /**
     * 主题，可以用来注册、取消订阅等功能
     */
    private MySubject subject;

    /**
     * 构造器，创建观察者时必须指定主题
     * @param subject 主题
     */
    public ConcreteObserver(MySubject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(Object obj) {
        System.out.println("数据有变化！数值是" + obj);
    }
}
