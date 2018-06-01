package com.wb.baselib.base.mvp;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
/**
 * Created by desin on 2017/1/12.
 */

public class BasePreaenter<V extends BaseView,M extends BaseModel> {
    protected M mModel;
    protected V mView;
    private CompositeDisposable mCompositeSubscription;
    protected void addSubscribe(Disposable subscription){
        if(mCompositeSubscription==null){
            mCompositeSubscription=new CompositeDisposable();
        }
        mCompositeSubscription.add(subscription);
    }
    public void unSubscribe(){
        if(mView!=null){
            mView=null;
        }
        if(mCompositeSubscription!=null){
            mCompositeSubscription.clear();
        }
    }
}
