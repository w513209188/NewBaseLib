package com.wb.baselib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wb.baselib.R;

/**
 * Created by desin on 2017/1/16.
 */

public class TopBarView extends RelativeLayout{

    private LinearLayout left_back, right_more;
    private TextView title, backTxt, moreTxt;
    private ImageView backImg, moreImg;

    public TopBarView(Context context) {
        super(context);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.view_topview, this);
        title= (TextView) view.findViewById(R.id.title);
        left_back= (LinearLayout) view.findViewById(R.id.left_back);
        right_more = (LinearLayout) view.findViewById(R.id.right_more);
        backTxt = (TextView) view.findViewById(R.id.backTxt);
        moreTxt = (TextView) view.findViewById(R.id.moreTxt);
        backImg = (ImageView) view.findViewById(R.id.backImg);
        moreImg = (ImageView) view.findViewById(R.id.moreImg);
    }

    /**
     * 只设置标题  |     title       |
     * @param msg
     */
    public void showTitle(String msg){
        left_back.setVisibility(GONE);
        title.setText(msg);
    }

    /**
     * 设置左边返回按钮以及标题  |<     title       |
     * @param back
     * @param msg
     */
    public void showLBackATitle(OnClickListener back,String msg){
        title.setText(msg);
        left_back.setOnClickListener(back);
    }

    /**
     * 设置  |<txt      title        |
     * @param msg
     * @param txt1 左侧文本
     * @param back
     */
    public void showLBack(String msg, String txt1, OnClickListener back){
        showLBackATitle(back, msg);
        backTxt.setText(txt1);
    }

    /**
     * 设置  |<       title       ♏|
     * @param msg
     * @param img2 右侧图片资源
     * @param back
     * @param more
     */
    public void showRMore(String msg, int img2, OnClickListener back, OnClickListener more){
        showLBackATitle(back, msg);
        moreImg.setBackgroundResource(img2);
        right_more.setOnClickListener(more);
    }

    /**
     * 设置  |<       title       txt|
     * @param msg
     * @param txt2 右侧文本
     * @param back
     * @param more
     */
    public void showRMore(String msg, String txt2, OnClickListener back, OnClickListener more){
        showLBackATitle(back, msg);
        moreTxt.setText(txt2);
        right_more.setOnClickListener(more);
    }

    /**
     * 设置  |<       title       txt|
     * @param msg
     * @param more
     */
    public void showRNoBMore(String msg, String txt, OnClickListener more){
        left_back.setVisibility(GONE);
        title.setText(msg);
        moreTxt.setText(txt);
        right_more.setOnClickListener(more);
    }
    /**
     * 设置  |<       title       txt|
     * @param msg
     * @param more
     */
    public void showRNoTMore(String msg, int img2, OnClickListener more){
        left_back.setVisibility(GONE);
        title.setText(msg);
        moreTxt.setVisibility(GONE);
        moreImg.setBackgroundResource(img2);
        right_more.setOnClickListener(more);
    }

}
