package com.mode.waterObserver;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */
public class StatisticsBoard implements Board, Observer {
    private float mMaxTemperature = -Float.MAX_VALUE;
    private float mMinTemperature = Float.MAX_VALUE;
    private float mSumOfTemperature = 0f;
    private int mCount = 0;

    @Override
    public void display() {
        String text = "最高/最低/平均(温度)：";
        text += (mMaxTemperature + "/" + mMinTemperature + "/" + mSumOfTemperature / mCount);
        System.out.println(text);
    }

    @Override
    public void onNotify(float temperature, float humidity, float pressure) {
        if (temperature > mMaxTemperature) {
            mMaxTemperature = temperature;
        }
        if (temperature < mMinTemperature) {
            mMinTemperature = temperature;
        }
        mSumOfTemperature += temperature;
        mCount++;
        display();
    }
}