package com.wb.baselib.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.wb.baselib.R;

import java.util.List;

/**
 * Created by desin on 2017/5/18.
 */

public class CourseListTabAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private Context mContext;
    private List<String> titles;
    private CourselistCall mCall;

    public void setmCall(CourselistCall mCall) {
        this.mCall = mCall;
    }

    public CourseListTabAdapter(FragmentManager fragmentManager, Context context,List<Fragment> fragments,List<String> strs) {
        super(fragmentManager);
        this.fragmentList = fragments;
        this.mContext = context;
        this.titles = strs;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    @Override
    public View getViewForTab(final int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.course_list_tab_item, container, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.test_tv);
        View fgx = convertView.findViewById(R.id.right_v);
        textView.setText(titles.get(position));
        int witdh = getTextWidth(textView);
        int padding = 10;
        textView.setWidth((int) (witdh * 1.3f) + padding);
        if (position==0) {
            fgx.setVisibility(View.GONE);
        } else {
            fgx.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    private int getTextWidth(TextView textView) {
        if (textView == null) {
            return 0;
        }
        Rect bounds = new Rect();
        String text = textView.getText().toString();
        Paint paint = textView.getPaint();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.left + bounds.width();
        return width;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        return fragmentList.get(position);
    }

    public interface CourselistCall {
        void getPageNum(int num);
    }
}
