package com.wangbo.www.baselibtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wb.baselib.base.activity.MvpActivity;
import com.wb.baselib.base.mvp.BasePreaenter;


public class MainActivity extends MvpActivity {
    private Button rebt;

    @Override
    protected BasePreaenter onCreatePresenter() {
        return null;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        rebt=this.findViewById(R.id.rebt);
////        RxBus.getIntanceBus().registerRxBus(String.class, new Consumer<String>() {
////            @Override
////            public void accept(String s) throws Exception {
////                Log.e("------",s);
////            }
////        });
//        rebt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                ToActivityUtil.newInsance().toNextActivity(MainActivity.this,TestActivity.class);
////                HttpConfig httpConfig=new HttpConfig.HttpConfigBuilder().setmBaseUrl("http://test.syhtclass.com/")
////                        .setUseCustGson(false)
////                        .build();
////                HttpManager.newInstance().commonRequest(HttpManager.newInstance().getService(BasicsApiService.class).userLogin("dsfdsfdsfds"), new BaseObserver<Result<String>>(AppUtils.getContext()) {
////                    @Override
////                    public void onSubscribe(Disposable d) {
////
////                    }
////
////                    @Override
////                    public void onComplete() {
////
////                    }
////
////                    @Override
////                    public void onSuccess(Result<String> stringResult) {
////
////                    }
////
////                    @Override
////                    public void onFail(ApiException e) {
////                        Log.e("失败了",e.getMessage());
////                    }
////                });
////                HttpManager.newInstance().commonRequest(HttpManager.newInstance().getService(BasicsApiService.class).userLogin("dsfdsfdsfds"), new Observer() {
////
////                    @Override
////                    public void onError(Throwable e) {
////                        Log.e("失败","-----"+e.getMessage());
////                    }
////
////                    @Override
////                    public void onComplete() {
////
////                    }
////
////                    @Override
////                    public void onSubscribe(Disposable d) {
////
////                    }
////
////                    @Override
////                    public void onNext(Object o) {
////                        Log.e("请求到了","-----");
////                    }
////                });
//            }
//        });
//    }


    private void requestPermissions() {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }
}
