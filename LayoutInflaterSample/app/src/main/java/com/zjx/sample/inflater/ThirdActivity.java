package com.zjx.sample.inflater;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;

public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 自定义Factory，必须在setContentView之前
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.setFactory2(new MyFactory(inflater.getFactory()));

        setContentView(R.layout.layout_third);
    }
}
