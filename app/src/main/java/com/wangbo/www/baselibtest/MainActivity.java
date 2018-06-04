package com.wangbo.www.baselibtest;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wb.baselib.base.activity.BaseActivity;
import com.wb.baselib.base.activity.MvpActivity;
import com.wb.baselib.base.fragment.BaseFragment;
import com.wb.baselib.base.mvp.BasePreaenter;
import com.wb.baselib.view.BottomBarView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
    private Button rebt;
    private BottomBarView test_bt;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        test_bt=getViewById(R.id.test_bt);
//        List<BaseFragment> fragments=new ArrayList<>();
//        fragments.add(new TestFragment());
//        fragments.add(new TestFragment());
//        fragments.add(new TestFragment());
//        test_bt.setBottomNoIcon(R.mipmap.tab_course_no,R.mipmap.tab_index_no,R.mipmap.tab_myserf_no)
//                .setBottomSelectIcon(R.mipmap.tab_course_yes,R.mipmap.tab_index_yes,R.mipmap.tab_myserf_yes)
//                .setBottomTextSelectColor(R.color.main_bg)
//                .setBottomTextNoColor(R.color.black)
//                .setBottomTitles("首页","课程","个人")
//                .setFragments(fragments)
//                .bindFrament(getSupportFragmentManager());
//        test_bt.setBadge(2,"草");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }
}
