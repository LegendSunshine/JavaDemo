package com.mode.waterObserver;

public interface Observer {
    void onNotify(float temperature, float humidity, float pressure);
}
