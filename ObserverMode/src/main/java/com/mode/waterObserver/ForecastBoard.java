package com.mode.waterObserver;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */
public class ForecastBoard implements Board,Observer{

    private float mCurrentPressure = Float.MAX_VALUE;
    private float mLastPressure = Float.MAX_VALUE;

    @Override
    public void display() {
        String message;
        if (mCurrentPressure > mLastPressure) {
            message = "气压正在升高";
        } else if (mCurrentPressure == mLastPressure) {
            message = "气压平稳";
        } else {
            message = "气压正在降低";
        }
        System.out.println(message);
    }


    @Override
    public void onNotify(float temperature, float humidity, float pressure) {
        mLastPressure = mCurrentPressure;
        mCurrentPressure = pressure;
        display();
    }
}
