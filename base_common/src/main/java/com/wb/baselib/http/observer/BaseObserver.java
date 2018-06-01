package com.wb.baselib.http.observer;

import android.content.Context;

import com.wb.baselib.appconfig.BaseAppConfig;
import com.wb.baselib.event.RxBus;
import com.wb.baselib.http.exception.ApiErrorHelper;
import com.wb.baselib.http.exception.ApiException;
import com.wb.rxbus.taskBean.RxLoginBean;

import io.reactivex.Observer;


public abstract class BaseObserver<T> implements Observer<T> {
    private Context mContext;

    private BaseObserver() {
    }

    protected BaseObserver(Context context) {
        mContext = context;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Override
    public void onError(Throwable e) {
        ApiException apiException = ApiErrorHelper.handleCommonError(mContext, e);
        if(apiException.getErrorCode()== BaseAppConfig.EXITAPPCODE){
            RxBus.getIntanceBus().post(new RxLoginBean(1));
        }
        onFail(apiException);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
       
    }
    public abstract void onSuccess(T t);
    public abstract void onFail(ApiException e);
}
