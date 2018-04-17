package com.mk.latte.ui.camera;

import android.net.Uri;

import com.mk.latte.delegates.PermissionCheckerDelegate;
import com.mk.latte.util.file.FileUtil;

/**
 * 相机的调用类
 *
 * @author lenovo
 * @data 2017/11/17
 */

public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        CameraHandler.create(delegate).beginCameraDialog();
    }

}
