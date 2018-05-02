package com.mk.latte.delegates;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.mk.latte.ui.camera.CameraImageBean;
import com.mk.latte.ui.camera.LatteCamera;
import com.mk.latte.ui.camera.RequestCodes;
import com.mk.latte.ui.scanner.ScannerDelegate;
import com.mk.latte.util.callback.CallBackManager;
import com.mk.latte.util.callback.CallBackType;
import com.mk.latte.util.callback.IGlobalCallBack;
import com.yalantis.ucrop.UCrop;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 权限验证
 * Created by lenovo on 2017/10/16.
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {

    //不是直接调用的方法 不能是 private，static 否则注解不能读取
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera() {
        LatteCamera.start(this);
    }

    //调用方法的入口

    /**
     * 照相机 调用的方法
     */
    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithCheck(this);
    }

    //扫描二维码
    @NeedsPermission(Manifest.permission.CAMERA)
    void startScan(BaseDelegate delegate) {
        delegate.getSupportDelegate().startForResult(new ScannerDelegate(),RequestCodes.SCAN);
    }

    public void startScanWithCheck(BaseDelegate delegate)
    {
        PermissionCheckerDelegatePermissionsDispatcher.startScanWithCheck(this,delegate);
    }


    /**
     * 获取权限失败
     */
    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        Toast.makeText(getContext(), "不允许拍照", Toast.LENGTH_SHORT).show();
    }

    /**
     * 拒绝权限
     */
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever() {
        Toast.makeText(getContext(), "永久拒绝权限", Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }

    private void showRationaleDialog(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("权限管理")
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult
                (this, requestCode, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.checkWriteWithCheck(this);
        PermissionCheckerDelegatePermissionsDispatcher.checkRedWithCheck(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.TAKE_PHOTO:
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                    UCrop.of(resultUri, resultUri)
                            .withMaxResultSize(400, 400)
                            .start(getContext(), this);
                    break;
                case RequestCodes.PICK_PHOTO:
                    if (data != null) {
                        final Uri pickPath = data.getData();
                        //从相册选择后需要有个路径存放剪裁过的图片
                        final String pickCropPath = LatteCamera.createCropFile().getPath();
                        UCrop.of(pickPath, Uri.parse(pickCropPath))
                                .withMaxResultSize(400, 400)
                                .start(getContext(), this);
                    }
                    break;
                case RequestCodes.CROP_PHOTO:
                    final Uri cropUri = UCrop.getOutput(data);
                    //拿到剪裁后的数据进行处理
                    @SuppressWarnings("unchecked")
                    final IGlobalCallBack<Uri> callback = CallBackManager
                            .getInstance()
                            .getCallBack(CallBackType.ON_CROP);
                    if (callback != null) {
                        callback.executeCallBack(cropUri);
                    }
                    break;
                case RequestCodes.CROP_ERROR:
                    Toast.makeText(getContext(), "剪裁出错", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }


    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void checkWrite() {

    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void checkRed() {

    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onWriteaDenied() {
        Toast.makeText(getContext(), "不允许写文件", Toast.LENGTH_LONG).show();
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onWriteNever() {
        Toast.makeText(getContext(), "永久拒绝写文件", Toast.LENGTH_LONG).show();
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onWriteRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }

    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
    void onRedDenied() {
        Toast.makeText(getContext(), "不允读文件", Toast.LENGTH_LONG).show();
    }

    @OnNeverAskAgain(Manifest.permission.READ_EXTERNAL_STORAGE)
    void onRedNever() {
        Toast.makeText(getContext(), "永久拒绝读文件", Toast.LENGTH_LONG).show();
    }

    @OnShowRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
    void onRedRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }

}

