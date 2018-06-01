package com.wb.baselib.permissions;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wb.baselib.permissions.interfaces.PerMissionCall;


public class PerMissionsManager {
    private static PerMissionsManager perMissionsManager;
    public static PerMissionsManager newInstance(){
        if(perMissionsManager==null){
            synchronized (PerMissionsManager.class){
                perMissionsManager=new PerMissionsManager();
            }
        }
        return perMissionsManager;
    }

    /**
     * 获取相应权限
     * @param mActivity
     * @param missionCall  响应回调
     * @param permissions  权限
     */
    public void getUserPerMissions(Activity mActivity,final PerMissionCall missionCall, final String... permissions){
        RxPermissions rxPermissions = new RxPermissions(mActivity);
        rxPermissions.requestEach(permissions)
               .subscribe(new io.reactivex.functions.Consumer<Permission>() {
                   @Override
                   public void accept(Permission permission) throws Exception {
                       if (permission.granted) {
                           // 用户已经同意该权限
                           missionCall.userPerMissionStatus(true);
                       } else if (permission.shouldShowRequestPermissionRationale) {
                           // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                           missionCall.userPerMissionStatus(false);
                       } else {
                           // 用户拒绝了该权限，并且选中『不再询问』
                           missionCall.userPerMissionStatus(false);
                       }
                   }
               });


    }
}
