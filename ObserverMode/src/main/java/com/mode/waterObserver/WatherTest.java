package com.mode.waterObserver;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */
public class WatherTest {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        station.onCreate();
        station.start();
        station.onDestroy();
    }


}
