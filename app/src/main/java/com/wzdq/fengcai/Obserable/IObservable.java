package com.wzdq.fengcai.Obserable;

/**
 * Created by PengSong on 18/11/26.
 */

public interface IObservable {

    void registerObserver(IObserver observer);
    void unRegisterObserver(IObserver observer);
}
