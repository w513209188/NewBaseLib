package com.wangbo.www.baselibtest;

import android.os.Bundle;

import com.wb.baselib.base.fragment.LazyFragment;

public class TestFragment extends LazyFragment {
    @Override
    public boolean isLazyFragment() {
        return true;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.public_grideview);
    }
}
