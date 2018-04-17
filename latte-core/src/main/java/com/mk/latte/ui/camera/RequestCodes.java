package com.mk.latte.ui.camera;

import com.yalantis.ucrop.UCrop;

/**
 * 请求码存储
 * @author lenovo
 * @data 2017/11/17
 */

public class RequestCodes {
    public static final int TAKE_PHOTO = 4;
    public static final int PICK_PHOTO = 5;
    public static final int CROP_PHOTO = UCrop.REQUEST_CROP;
    public static final int CROP_ERROR = UCrop.RESULT_ERROR;
    public static final int SCAN = 7;
}
