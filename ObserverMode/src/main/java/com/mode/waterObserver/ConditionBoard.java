package com.mode.waterObserver;


/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */
public class ConditionBoard implements Board, Observer {
    private float mTemperature;
    private float mHumidity;
    private float mPressure;

    @Override
    public void display() {
        System.out.println("温度：" + mTemperature + "，湿度" + mHumidity + "，气压：" + mPressure);
    }

    @Override
    public void onNotify(float temperature, float humidity, float pressure) {
        mTemperature = temperature;
        mHumidity = humidity;
        mPressure = pressure;
        display();
    }
}
