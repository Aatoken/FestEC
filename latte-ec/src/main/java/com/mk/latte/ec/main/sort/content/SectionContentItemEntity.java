package com.mk.latte.ec.main.sort.content;

/**
 * @author lenovo
 * @data 2017/11/9
 */

public class SectionContentItemEntity {

    /**
     * goods_id : 5
     * goods_name : 品牌3
     * goods_thumb : http://i8.mifile.cn/v1/a1/1ef4e430-b65a-2655-791a-680a49668005.webp?width
     * =720&height=720
     */

    private int goods_id=0;
    private String goods_name=null;
    private String goods_thumb=null;

    public SectionContentItemEntity(int goods_id, String goods_name, String goods_thumb) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.goods_thumb = goods_thumb;
    }

    public SectionContentItemEntity() {
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }
}
