package com.wzdq.fengcai.mvp.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by PengSong on 18/11/29.
 */

public class PresenterManager {

    Map<Class,BasePresenter> presenterMap;

    public PresenterManager() {
        presenterMap = new HashMap<>();
    }

    public void addPresenter(BasePresenter bPresenter){
        presenterMap.put(bPresenter.getClass(),bPresenter);
    }

    public BasePresenter getPresenter(Class<? extends BasePresenter> cla){
        BasePresenter presenter = null;
        if (presenterMap.containsKey(cla)){
            presenter = presenterMap.get(cla);
        }

        return presenter;
    }


    public void detachView(){
        Set<Class> classes = presenterMap.keySet();
        for (Class cla : classes){
            presenterMap.get(cla).detachView();
        }
        presenterMap.clear();
    }
}
