package com.wb.baselib.base.mvp;

/**
 * Created by desin on 2017/1/12.
 */

public interface BaseView {
    //展示错误信息
    void showErrorMsg(String msg);
    //展示等待加载动画
    void showLoadV(String msg);
    //关闭等待动画
    void closeLoadV();
}
