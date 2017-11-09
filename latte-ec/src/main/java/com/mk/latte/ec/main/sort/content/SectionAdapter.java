package com.mk.latte.ec.main.sort.content;


import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mk.latte.ec.R;
import com.mk.latte.util.imgload.GlideUtil;

import java.util.List;

/**
 * @author lenovo
 * @data 2017/11/9
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean,BaseViewHolder> {


    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder holder, SectionBean item) {
        holder.setText(R.id.header,item.header);
        holder.setVisible(R.id.more,item.isMore());
        holder.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder holder, SectionBean item) {
        //item.t 返回的是 SectionBean 类型
        final String thumb=item.t.getGoods_thumb();
        final String goodsName=item.t.getGoods_name();
        final int goodsId=item.t.getGoods_id();
        final SectionContentItemEntity entity=item.t;

        holder.setText(R.id.tv,goodsName);
        final AppCompatImageView goodsImageView = holder.getView(R.id.iv);

        Glide.with(mContext)
                .load(thumb)
                .apply(GlideUtil.RECYCLER_OPTIONS)
                .into(goodsImageView);

    }



}
