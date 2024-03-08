package org.example.demo2;

public class ConcreteObserver2 implements MyObserver{


    /**
     * 温度
     */
    private int temperature;

    /**
     * 湿度
     */
    private int humidity;

    /**
     * 大气压
     */
    private int pressure;

    private MySubject subject;

    public ConcreteObserver2(MySubject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(int temperature, int humidity, int pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        System.out.println("观察者2，气象站出新数据了,温度：" + temperature + ",湿度：" + humidity + ",气压：" + pressure);
    }

    public void remove(){
        subject.removeObserver(this);
    }
}
