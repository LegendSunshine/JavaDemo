package com.mode.waterObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */
public class Weather implements Observable{
    private float mTemperature;
    private float mHumidity;
    private float mPressure;

    private List<Observer> mObservers;

    public Weather() {
        mObservers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        mObservers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        mObservers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for(Observer o : mObservers) {
            o.onNotify(mTemperature, mHumidity, mPressure);
        }
    }

    // 设置天气测量结果后，携带数据通知各个被观察者
    // 比如说：嘿，布告栏哥哥姐姐们，快根据最新天气情况更新布告内容吧
    public void setMeasurements(float temperature, float humidity, float pressure) {
        mTemperature = temperature;
        mHumidity = humidity;
        mPressure = pressure;
        notifyObserver();
    }
}
