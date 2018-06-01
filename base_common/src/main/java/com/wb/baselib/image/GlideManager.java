package com.wb.baselib.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 这个是glide处理工具类
 */

public class GlideManager {
    private static GlideManager glideUtils;
    public static GlideManager getInstance(){
        if(glideUtils==null){
            synchronized (GlideManager.class){
                glideUtils=new GlideManager();
            }
        }
        return glideUtils;
    }

    /**
     * 设置普通的图片
     * @param imageViewm
     * @param res  占位图
     * @param mContext
     * @param path 图片地址
     * @param isC 是否切元显示
     */
    public void setCommonPhoto(ImageView imageViewm, int res, Context mContext,String path,boolean isC){
        if(isC){
            Glide.with(mContext).load(path).centerCrop().placeholder(res).error(res).into(imageViewm);
        }else {
            Glide.with(mContext).load(path).placeholder(res).error(res).into(imageViewm);
        }

    }

    /**
     * 设置原形图片
     * @param imageViewm
     * @param res 占位图
     * @param mContext
     * @param path 图片地址
     */
    public void setRoundPhoto(ImageView imageViewm, int res, Context mContext, String path){
        Glide.with(mContext).load(path).error(res).placeholder(res).transform(new GlideCircleTransform(mContext)).into(imageViewm);
    }
    public void getGlideCacherSize(){
    }
}
