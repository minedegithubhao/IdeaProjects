package org.example.demo;

public class Main {

    public static void main(String[] args) {

        // 具体的主题
        ConcreteSubject subject = new ConcreteSubject();
        // 观察者1
        ConcreteObserver observer = new ConcreteObserver(subject);
        // 观察者2
        ConcreteObeser2 observer1 = new ConcreteObeser2(subject);

        // 主题状态发生变化
        subject.changeSubject(5);

        // 观察者2取消订阅
        observer1.remove();
        // 主题状态发生变化，观察者2将不再收到消息
        subject.changeSubject(3);

    }
}
