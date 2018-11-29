package com.zjx.sample.inflater;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SecondActivity extends Activity {

    private static final String TAG = SecondActivity.class.getSimpleName();

    private LinearLayout mContainer;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_second);
        mContainer = findViewById(R.id.container);
        mInflater = LayoutInflater.from(this);

        test1();

        test2();

        test3();

        test4();
    }

    private void test1() {
        // 此处的View是mContainer
        View view = mInflater.inflate(R.layout.layout_second_item, mContainer, true);
        if (view instanceof LinearLayout) {
            Log.d(TAG, "test1: is LinearLayout");
        }
        // match_parent match_parent
        Log.d(TAG, "test1 width: " + view.getLayoutParams().width + " height: " + view.getLayoutParams().height);
    }

    private void test2() {
        // 此处的view是item2，view会有LayoutParam，宽高是layout里设置的，但不会自动添加到mContainer中
        View view = mInflater.inflate(R.layout.layout_second_item, mContainer, false);
        if (view instanceof RelativeLayout) {
            Log.d(TAG, "test2: is RelativeLayout");
        }
        // match_parent wrap_content
        Log.d(TAG, "test2 width: " + view.getLayoutParams().width + " height: " + view.getLayoutParams().height);
    }

    private void test3() {
        // 此处的view是item2，但LayoutParam为null，attacToRoot参数没有用到
        View view = mInflater.inflate(R.layout.layout_second_item2, null, true);
        Log.d(TAG, "test3 : " + view.getLayoutParams());
        // 主动添加到container时，用的是container的宽高
        mContainer.addView(view);
    }

    /**
     * 和test3执行的逻辑一样
     */
    private void test4() {
        View view = mInflater.inflate(R.layout.layout_second_item2, null, false);
        Log.d(TAG, "test4 : " + view.getLayoutParams());
    }
}
