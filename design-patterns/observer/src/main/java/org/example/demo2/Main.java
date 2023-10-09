package org.example.demo2;

public class Main {

    public static void main(String[] args) {

        WeatherData weatherData = new WeatherData();
        ConcreteObserver concreteObserver = new ConcreteObserver(weatherData);
        ConcreteObserver2 concreteObserver2 = new ConcreteObserver2(weatherData);

        System.out.println("发布新数据了。。。");
        weatherData.measurementsChanged(26, 30, 1000);

        System.out.println("观察者2取消了订阅");
        concreteObserver2.remove();

        System.out.println("刚刚发布新数据了。。。");
        weatherData.measurementsChanged(27, 28, 1002);


    }
}
