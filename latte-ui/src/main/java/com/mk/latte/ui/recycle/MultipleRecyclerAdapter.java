package com.mk.latte.ui.recycle;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mk.latte.R;
import com.mk.latte.ui.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lenovo
 * @data 2017/10/31
 */

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter
        <MultipleItemEntity, MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup, OnItemClickListener {

    /**
     * 确保初始化一次banner
     */
    private boolean mIsInitBanner = false;
    /**
     * 设置图片加载策略
     */
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        //完成初始化 itemView
        init();
    }

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter converter) {
        return new MultipleRecyclerAdapter(converter.convert());
    }

    /**
     * 数据转换
     *
     * @param holder
     * @param entity
     */
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        //getItemViewType 就是获取 init() 方法中存入的 item布局
        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = entity.getField(MultipleFields.TEXT);
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_single));

                break;
            case ItemType.TEXT_IMAGE:
                text = entity.getField(MultipleFields.TEXT);
                holder.setText(R.id.tv_multiple, text);
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_multiple));

                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerImages = entity.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id
                            .banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
                break;

            default:
                break;
        }
    }


    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

    /**
     * 初始化 设置  都是框架自带的
     */
    private void init() {
        //设置不同的item布局 该方法框架自带
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        //设置宽度的监听<按权重 配置>
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);


    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }


    @Override
    public void onItemClick(int position) {

    }


}
















