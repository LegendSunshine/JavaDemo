package com.mode.observer;

import com.mode.observer.Observer ;

/**
 * @author legend
 */
public interface Observerable {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver();
}
