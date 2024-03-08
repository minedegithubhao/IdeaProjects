package org.example.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cxd
 * WeatherData对象
 */
public class WeatherData implements MySubject{

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

    private List<MyObserver> observers = new ArrayList<>();

    /**
     * 测量数据变化是调用，由气象站调用
     */
    public void measurementsChanged(int temperature, int humidity, int pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        // 当气象站数据变化后，通知所有的观察者
        notifyObserver(observers);
    }

    @Override
    public void registerObserver(MyObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(MyObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(List<MyObserver> observers) {
        for (MyObserver observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }
}
