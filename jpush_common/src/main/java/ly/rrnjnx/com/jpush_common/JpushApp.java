package ly.rrnjnx.com.jpush_common;

import android.content.Context;

import cn.jpush.android.api.JPushInterface;
import ly.rrnjnx.com.jshape_common.JshapeApp;

public class JpushApp  {
    private static JpushApp mJpushApp;
    public static JpushApp getInstance(){
        if(mJpushApp==null){
            mJpushApp=new JpushApp();
        }
        return mJpushApp;
    }

    /**
     * 初始化 极光推送
     * @param mContext
     */
    public void initJpush(Context mContext,boolean isDebug){
        JPushInterface.setDebugMode(isDebug);
        JPushInterface.init(mContext);
    }
}
