package com.mk.latte.ec.main.person.list;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mk.latte.ec.R;

import java.util.List;

/**
 * @author lenovo
 * @data 2017/11/15
 */

public class ListEntityAdapter extends BaseMultiItemQuickAdapter<ListEntity,BaseViewHolder> {


    public ListEntityAdapter(List<ListEntity> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ListItemType.ITEM_AVATAR, R.layout.arrow_item_avatar);
        addItemType(ListItemType.ITEM_SWITCH,R.layout.arrow_switch_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListEntity item) {
        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:
                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_value, item.getValue());
                break;
            case ListItemType.ITEM_AVATAR:
                break;
            case ListItemType.ITEM_SWITCH:
                break;
            default:
                break;
        }
    }



}
