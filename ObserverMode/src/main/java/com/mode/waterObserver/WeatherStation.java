package com.mode.waterObserver;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */
public class WeatherStation {
    private Weather mWeather;
    private ConditionBoard mConditionBoard;
    private StatisticsBoard mStatisticsBoard;
    private ForecastBoard mForecastBoard;

    public WeatherStation() {
        mWeather = new Weather();
        mConditionBoard = new ConditionBoard();
        mStatisticsBoard = new StatisticsBoard();
        mForecastBoard = new ForecastBoard();
    }

    public void onCreate() {
        mWeather.registerObserver(mConditionBoard);
    }

    public void onDestroy() {
        mWeather.removeObserver(mConditionBoard);
        mWeather.removeObserver(mStatisticsBoard);
        mWeather.removeObserver(mForecastBoard);
    }

    public void addBoard(Observer o) {
        mWeather.registerObserver(o);
    }

    public void removeBoard(Observer o) {
        mWeather.removeObserver(o);
    }

    public void start() {
        System.out.println("===第1次测量===");
        mWeather.setMeasurements(80, 65, 30.4f);
        System.out.println("===第2次测量===");
        mWeather.setMeasurements(82, 70, 29.2f);
        System.out.println("===第3次测量===");
        mWeather.setMeasurements(78, 90, 29.2f);
    }
}
