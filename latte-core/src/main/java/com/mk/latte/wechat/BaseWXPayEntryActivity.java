package com.mk.latte.wechat;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseResp;

/**
 * @author lenovo
 * @data 2017/11/12
 */

public abstract class BaseWXPayEntryActivity extends BaseWXActivity {


    private static final int WX_PAY_SUCCESS = 0;
    private static final int WX_PAY_FAIL = -1;
    private static final int WX_PAY_CANCEL = -2;

    /**
     * 支付成功
     */
    protected abstract void onPaySuccess();

    /**
     * 支付失败
     */
    protected abstract void onPayFail();

    /**
     * 取消支付
     */
    protected abstract void onPayCancel();

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (baseResp.errCode) {
                case WX_PAY_SUCCESS:
                    onPaySuccess();
                    break;
                case WX_PAY_FAIL:
                    onPayFail();
                    break;
                case WX_PAY_CANCEL:
                    onPayCancel();
                    break;
                default:
                    break;
            }
        }
    }


}
