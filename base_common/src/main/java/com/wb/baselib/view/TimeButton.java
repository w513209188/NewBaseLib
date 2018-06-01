package com.wb.baselib.view;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.wb.baselib.appconfig.BaseAppConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 *这个是一个短信倒计时自定义view
 * 			@author CJ
 *         2016年9月1日 10:59:18 [佛祖保佑 永无BUG]
 *         PS: 由于发现timer每次cancle()之后不能重新schedule方法,所以计时完毕只恐timer.
 *         每次开始计时的时候重新设置timer, 没想到好办法初次下策
 *         注意把该类的onCreate()onDestroy()和activity的onCreate()onDestroy()同步处理
 *
 */
@SuppressLint("AppCompatCustomView")
public class TimeButton extends TextView implements OnClickListener {
    private long lenght = 60 * 1000;// 倒计时长度,这里给了默认60秒
    private String textafter = "秒后重新获取";
    private String textbefore = "获取验证码";
    private final String TIME = "time";
    private final String CTIME = "ctime";
    private OnClickListener mOnclickListener;
    private Timer t;
    private TimerTask tt;
    private long time;
    Map<String, Long> map = new HashMap<String, Long>();

    public TimeButton(Context context) {
        super(context);
        setOnClickListener(this);

    }

    public TimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @SuppressLint("HandlerLeak")
    Handler han = new Handler() {
        public void handleMessage(android.os.Message msg) {
            TimeButton.this.setText(time / 1000 + textafter);
            time -= 1000;
            if (time < 0) {
//				TimeButton.this.setBackgroundResource(getResources().getColor(R.color.dumiaonormal2));
                TimeButton.this.setEnabled(true);
                TimeButton.this.setText(textbefore);
                clearTimer();
            }
        };
    };

    private void initTimer() {
        time = lenght;
        t = new Timer();
        tt = new TimerTask() {

            @Override
            public void run() {
                han.sendEmptyMessage(0x01);
            }
        };
    }

    public void clearTimer() {
        if (tt != null) {
            tt.cancel();
            tt = null;
        }
        if (t != null)
            t.cancel();
        t = null;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        if (l instanceof TimeButton) {
            super.setOnClickListener(l);
        } else
            this.mOnclickListener = l;
    }

    @Override
    public void onClick(View v) {
        if (mOnclickListener != null)
            mOnclickListener.onClick(v);
        //if(is){
//			initTimer();
//			this.setText(time / 1000 + textafter);
//			this.setEnabled(false);
//			t.schedule(tt, 0, 1000);
        //}

        // t.scheduleAtFixedRate(task, delay, period);
    }

    /**
     * 和activity的onDestroy()方法同步
     */
    public void onDestroy() {
        if (BaseAppConfig.map == null)
            BaseAppConfig.map = new HashMap<String, Long>();
        BaseAppConfig.map.put(TIME, time);
        BaseAppConfig.map.put(CTIME, System.currentTimeMillis());
        clearTimer();
    }

    /**
     * 和activity的onCreate()方法同步
     */
    public void onCreate(Bundle bundle) {
        if (BaseAppConfig.map == null)
            return;
        if (BaseAppConfig.map.size() <= 0)// 这里表示没有上次未完成的计时
            return;
        long time = System.currentTimeMillis() - BaseAppConfig.map.get(CTIME)
                - BaseAppConfig.map.get(TIME);
        BaseAppConfig.map.clear();
        if (time > 0)
            return;
        else {
            initTimer();
            this.time = Math.abs(time);
            t.schedule(tt, 0, 1000);
            this.setText(time + textafter);
            this.setEnabled(false);
        }
    }

    /** * 设置计时时候显示的文本 */
    public TimeButton setTextAfter(String text1) {
        this.textafter = text1;
        return this;
    }

    /** * 设置点击之前的文本 */
    public TimeButton setTextBefore(String text0) {
        this.textbefore = text0;
        this.setText(textbefore);
        return this;
    }

    /**
     * 设置到计时长度
     *
     * @param lenght
     *            时间 默认毫秒
     * @return
     */
    public TimeButton setLenght(long lenght) {
        this.lenght = lenght;
        return this;
    }
    /*

*
*/
    public void setIsStarTime(){
        initTimer();
        this.setText(time / 1000 + textafter);
        this.setEnabled(false);
        t.schedule(tt, 0, 1000);
    }
}