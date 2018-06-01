package com.wb.baselib;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.hss01248.dialog.ActivityStackManager;
import com.hss01248.dialog.StyledDialog;
import com.wb.baselib.bean.AppUpdateBean;
import com.wb.baselib.interfaces.IApplicationDelegate;
import com.wb.baselib.classs.ClassUtils;
import com.wb.baselib.prase.GsonUtils;
import com.wb.baselib.app.AppUtils;

import org.lzh.framework.updatepluginlib.UpdateConfig;
import org.lzh.framework.updatepluginlib.model.CheckEntity;
import org.lzh.framework.updatepluginlib.model.HttpMethod;
import org.lzh.framework.updatepluginlib.model.Update;
import org.lzh.framework.updatepluginlib.model.UpdateParser;

import java.util.List;

public class BaseApplication extends Application {

    public static final String ROOT_PACKAGE = "ly.rrnjnx.com";

    private static BaseApplication sInstance;

    private List<IApplicationDelegate> mAppDelegateList;

    public static BaseApplication getIns() {
        return sInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        AppUtils.init(this);
        mAppDelegateList = ClassUtils.getObjectsWithInterface(this, IApplicationDelegate.class, ROOT_PACKAGE);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onCreate();
        }
        registCallback();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTerminate();
        }
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onLowMemory();
        }
    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTrimMemory(level);
        }
    }
    private void registCallback( ) {
        StyledDialog.init(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityStackManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityStackManager.getInstance().removeActivity(activity);
            }
        });
    }
    //版本更新
    public void updateApp(String updataApp) {
        CheckEntity ch=new CheckEntity();
        ch.setMethod(HttpMethod.GET);
        ch.setUrl(updataApp);
        UpdateConfig.getConfig()
                .checkEntity(ch)
                .jsonParser(new UpdateParser() {
                    @Override
                    public Update parse(String httpResponse) {
                        Log.e("获取到的版本更新",httpResponse);
                        try {
//                    JSONObject object = new JSONObject(httpResponse);
                            AppUpdateBean bean= GsonUtils.newInstance().getBean(httpResponse, AppUpdateBean.class);
                            Update update = new Update(httpResponse);
                            // 此apk包的更新时间
                            update.setUpdateTime(System.currentTimeMillis());
                            // 此apk包的下载地址
                            update.setUpdateUrl(bean.getData().getVersion_info().getApk_address());
                            // 此apk包的版本号
                            update.setVersionCode(Integer.parseInt(bean.getData().getVersion_info().getVersion_code()));
                            // 此apk包的版本名称
                            update.setVersionName(bean.getData().getVersion_info().getVersion_name());
                            // 此apk包的更新内容
                            update.setUpdateContent(bean.getData().getVersion_info().getVersion_detail());
                            // 此apk包是否为强制更新
                            if(bean.getData().getVersion_info().getIs_force_update()==null||bean.getData().getVersion_info().getIs_force_update().equals("")){
                                update.setForced(true);
                            }else if(bean.getData().getVersion_info().getIs_force_update().equals("0")){
                                update.setForced(false);
                            }else {
                                update.setForced(true);
                            }
                            update.setIgnore(false);
                            // 是否显示忽略此次版本更新按钮
//                            if(bean.getData().getIs_ignore_version()==null||bean.getData().getIs_ignore_version().equals("")){
//                                update.setIgnore(true);
//                            }else if(bean.getData().getIs_ignore_version().equals("0")){
//                                update.setIgnore(false);
//                            }else {
//                                update.setIgnore(true);
//                            }

                            return update;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                });
    }
}
