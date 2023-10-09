package org.example.demo2;

import java.util.List;

/**
 * 观察者接口
 * @author cxd
 */
public interface MySubject {

    /**
     * 注册
     * @param observer 观察者
     */
    void registerObserver(MyObserver observer);

    /**
     * 删除
     * @param observer 观察者
     */
    void removeObserver(MyObserver observer);

    /**
     * 通知，当主题的状态发生变化时，通知所有的观察者
     * @param observers 观察者集合
     */
    void notifyObserver(List<MyObserver> observers);
}
