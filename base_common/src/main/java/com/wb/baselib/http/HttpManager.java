package com.wb.baselib.http;
import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wb.baselib.http.exception.MyGsonConverterFactory;
import com.wb.baselib.app.AppUtils;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static HttpManager httpManager;
    Retrofit mRetrofit;
    OkHttpClient okHttpClient;
    public  HttpConfig mHttpConfig;
    public HttpManager() {
        mHttpConfig=HttpConfig.newInstance();
        getRetrofit(getClient(AppUtils.getContext()));
    }
    public static HttpManager newInstance(){
                if(httpManager==null){

                    httpManager=new HttpManager();
                }
                return httpManager;
        }

    /**
     * 获取到 Retrofit
     * @param mclient
     * @return
     */
    public  Retrofit getRetrofit(OkHttpClient mclient){
        if(mRetrofit==null){
            synchronized (HttpManager.class){
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(mHttpConfig.getmBaseUrl())
                        .client(mclient)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                if(mHttpConfig.isUseCustGson()){
                    builder.addConverterFactory(MyGsonConverterFactory.create());
                }else {
                    builder.addConverterFactory(GsonConverterFactory.create());
                }
                mRetrofit=builder.build();
            }
        }
        return mRetrofit;
    }

    /**
     * 获取到Client
     * @param context
     * @return
     */
    public  OkHttpClient getClient(final Context context){
        if(okHttpClient==null){
            synchronized (HttpManager.class){
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //缓存
                int size = mHttpConfig.getmCacheSize()==0?20*1024*1024:mHttpConfig.getmCacheSize();
                File cacheFile=null;
                if(mHttpConfig.getmCacheFolder()==null){
                    cacheFile = new File(context.getCacheDir(), "OkHttpCache");
                }else {
                    cacheFile=mHttpConfig.getmCacheFolder();
                }
                Cache cache = new Cache(cacheFile, size);
                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .connectTimeout(mHttpConfig.getmConnectTimeout()==0?60:mHttpConfig.getmConnectTimeout(), TimeUnit.SECONDS)//连接超时时间
                        .readTimeout(mHttpConfig.getmConnectTimeout()==0?60:mHttpConfig.getmConnectTimeout(), TimeUnit.SECONDS)//读取超时时间
                        .writeTimeout(mHttpConfig.getmConnectTimeout()==0?60:mHttpConfig.getmConnectTimeout(), TimeUnit.SECONDS)//写入超时时间
                        .cache(cache);//缓存
                if(mHttpConfig.ismIsUseLog()){
                    builder.addInterceptor(loggingInterceptor);
                }
                if(mHttpConfig.getmMapHeader()==null||mHttpConfig.getmMapHeader().size()==0){
                }else {
                    builder.addInterceptor(new RequestInterceptor());
                }
                okHttpClient=builder.build();
            }

        }
        return okHttpClient;
    }
    /**
     * 获取指定的网络请求Api接口
     * @param serviceClass ApiService的类型
     * @return 相应的ApiService
     */
    public <T> T getService(Class<T> serviceClass) {
        T service =null;
        if (service == null) {
            service = mRetrofit.create(serviceClass);
        }
        return service;
    }
    /**
     * 普通的网络api请求，会根据全局配置判断是否使用失败重试机制
     * @param observable 请求
     * @param observer 请求回调
     */
    public void commonRequest(Observable observable, Observer observer) {
        handleThread(observable).subscribe(observer);
    }

    /**
     * 线程切换
     * @param observable
     * @return
     */
    private Observable handleThread(Observable observable) {
        return observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 设置头部拦截器
     */
    private  class RequestInterceptor implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request()
                    .newBuilder();
            for (Map.Entry<String, String> entry : mHttpConfig.getmMapHeader().entrySet()) {
                builder .addHeader(entry.getKey(), entry.getValue());
            }
            Request request=builder.build();
            return chain.proceed(request);
        }
    }

}
