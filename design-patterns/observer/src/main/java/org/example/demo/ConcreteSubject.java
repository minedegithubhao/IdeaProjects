package org.example.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题MySubject的实现类，
 */
public class ConcreteSubject implements MySubject{
    /**
     * 这个是主题的状态，观察者们总是对主题的一个或多个状态感兴趣
     */
    private int i;
    List<MyObserver> observers = new ArrayList<>();
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
            observer.update(i);
        }
    }

    public void changeSubject(int i){
        this.i = i;
        notifyObserver(observers);
    }
}
