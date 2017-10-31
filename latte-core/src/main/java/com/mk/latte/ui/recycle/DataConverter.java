package com.mk.latte.ui.recycle;

import com.mk.latte.util.str.StrUtils;

import java.util.ArrayList;

/**
 * 数据转换约束
 *
 * @author lenovo
 * @data 2017/10/31
 */

public abstract class DataConverter {
    /**
     * MultipleItemEntity 集合
     */
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    /**
     * Json 数据
     */
    private String mJsonData = null;

    /**
     * 解析JSON 数据
     * @return
     */
    public abstract ArrayList<MultipleItemEntity> convert();

    /**
     * 设置Json 数据
     * @param json
     * @return
     */
    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    /**
     * 获取Json数据
     * @return
     */
    protected String getJsonData() {
        if (StrUtils.isEmpty(mJsonData)) {
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }


}













