package com.wzdq.fengcai.Obserable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PengSong on 18/11/26.
 */

public class Observable implements IObservable {

    private List<IObserver> mObserverList;

    public Observable() {
        mObserverList = new ArrayList<>();
    }


    @Override
    public void registerObserver(IObserver observer) {
        if (!mObserverList.contains(observer)){
            mObserverList.add(observer);
        }
    }

    @Override
    public void unRegisterObserver(IObserver observer) {
        if (mObserverList.contains(observer)){
            mObserverList.remove(observer);
        }
    }

    /**
     * 通知
     * @param params
     */
    public void dispatchObserver(Object ... params){
        if (mObserverList == null) {
            return;
        }

        for (IObserver observer : mObserverList){
            observer.subscribe(params);
        }
    }
}
