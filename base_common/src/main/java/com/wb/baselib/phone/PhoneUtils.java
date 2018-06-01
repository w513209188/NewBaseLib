package com.wb.baselib.phone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.WindowManager;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/15.
 */

public class PhoneUtils {
    public static PhoneUtils phoneUtils;
    public static PhoneUtils newInstance(){
        if(phoneUtils==null){
            phoneUtils=new PhoneUtils();
        }
        return phoneUtils;
    }
    /**
     * 获取到手机信息屏幕方向
     * @param mActivity
     * @return
     */
    public  int getDisplayRotation(Activity mActivity) {
        int rotation = mActivity.getWindowManager().getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
        }
        return 0;
    }

    /**
     * 获取设备号
     * @param mContext
     * @return
     */
    public  String getIMEI(Context mContext) {
        try {
            @SuppressLint("WrongConstant")
            TelephonyManager tm = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
            @SuppressLint("MissingPermission") String deviceId = tm.getDeviceId();
            return deviceId;
        }catch (Exception e){
            return "00000000";
        }
    }

    /**
     * 获取手机屏幕得宽
     * @param context
     * @return
     */
    public  int getPhoneWidth(Context context) {
        Activity activity = (Activity) context;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 获取手机屏幕得高
     * @param context
     * @return
     */
    public  int getPhoneHeight(Context context) {
        Activity activity = (Activity) context;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 获取手机得设备号
     * @param context
     */
    public  String getImieStatus(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission")
        String deviceId = tm.getDeviceId();
        return deviceId;
    }
    /**
     * 验证网址Url
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public  boolean IsUrl(String str) {
        try {
            String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
            return match(regex, str);
        }catch (Exception e){
            return false;
        }

    }
    /**
     * @param regex
     * 正则表达式字符串
     * @param str
     * 要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private  boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    /**
     * 验证手机格式
     */
    public  boolean isMobileNO(String mobiles) {
        String telRegex = "[1][34758]\\d{9}";
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }


//	验证邮箱格式

    public  boolean isEmail(String email) {

        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

        Pattern p = Pattern.compile(str);

        Matcher m = p.matcher(email);

        return m.matches();

    }

    /**
     * dp转换为px
     */
    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转换为dp
     */
    public  int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     *            （DisplayMetrics类中属�?scaledDensity�?
     * @return
     */
    public  int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public  int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取渠道商id
     * @param context
     * @return
     */
    public  String getChannelId(Context context) {
        ApplicationInfo appInfo;
        try {
            appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            return appInfo.metaData.getInt("UMENG_CHANNEL") + "";

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            return "0";
        }
    }

    /**
     * 获取app版本号码
     * @param context
     * @return
     */
    public  String getVersion(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return 0 + "";
        }
    }
    /**
     * 获取appcode
     * @param context
     * @return
     */
    public  String getVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode+"";
        } catch (PackageManager.NameNotFoundException e) {
            return 0 + "";
        }
    }
    /**
     * 获取状态栏的高度
     * @return
     */
    public  int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public  int getFontSize(Context context, int textSize) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        int rate = (int) (textSize * (float) screenHeight / 1280);
        return rate;
    }

    /**
     *
     * @param context
     * @return
     */
    private  boolean isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true; // ~ 400-7000 kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true; // ~ 10+ Mbps
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }

    /**
     * 获取屏幕宽度和高度，单位为px
     *
     * @param context
     * @return
     */
    public  Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }

    /**
     * 获取屏幕长宽比
     * @param context
     * @return
     */
    public  float getScreenRate(Context context) {
        Point P = getScreenMetrics(context);
        float c = dip2px(context, 50) * 7 / 9;
        float H = P.y - c;
        float W = P.x;
        return (H / W);
    }
    /**
     * 获取当前应用程序的包名
     * @param context 上下文对象
     * @return 返回包名
     */
    public  String getAppProcessName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }
}
