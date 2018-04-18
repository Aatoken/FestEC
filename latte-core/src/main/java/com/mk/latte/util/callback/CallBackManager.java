package com.mk.latte.util.callback;

import java.util.WeakHashMap;

/**
 * Created by Aatoken on 2018/4/18.
 */

public class CallBackManager {

    private static final WeakHashMap<Object, IGlobalCallBack> CALLBACKS = new WeakHashMap<>();

    /**
     * 惰性单列
     */
    private static class Holder {
        private static final CallBackManager INSTANCE = new CallBackManager();
    }

    public static CallBackManager getInstance() {
        return Holder.INSTANCE;
    }

    public CallBackManager addCallBack(Object tag,IGlobalCallBack callBack)
    {
        CALLBACKS.put(tag,callBack);
        return this;
    }

    public IGlobalCallBack getCallBack(Object tag)
    {
      return CALLBACKS.get(tag);
    }



}
