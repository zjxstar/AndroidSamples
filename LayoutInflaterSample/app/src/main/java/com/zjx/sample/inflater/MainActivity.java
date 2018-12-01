package com.zjx.sample.inflater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 如果继承自AppCompatActivity，必须要放在super之前，否则AppCompatActivity会设置自己的Factory，会导致重复赋值异常
        LayoutInflater.from(this).setFactory2(new LayoutInflater.Factory2() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                Log.e(TAG, "Factory2 onCreateView parent:" + parent + ",name = " + name);
                if (name.equals("TextView")) { // 替换成自己的逻辑
                    return new Button(context, attrs);
                }

                // 委托给AppCompatViewInflater
                AppCompatDelegate appCompatDelegate = getDelegate();
                return appCompatDelegate.createView(parent, name, context, attrs);
            }

            @Override
            public View onCreateView(String name, Context context, AttributeSet attrs) {
                Log.e(TAG, "Factory name = " + name);
                for (int i = 0; i < attrs.getAttributeCount(); i++) {
                    Log.e(TAG, attrs.getAttributeName(i) + " , " + attrs.getAttributeValue(i));
                }
                return null;
            }
        });
        super.onCreate(savedInstanceState);

        // 如果继承自Activity，必须放在setContentView之前
//        LayoutInflater.from(this).setFactory(new LayoutInflater.Factory() {
//            @Override
//            public View onCreateView(String name, Context context, AttributeSet attrs) {
//                Log.e(TAG, "Factory name = " + name);
//                for (int i = 0; i < attrs.getAttributeCount(); i++) {
//                    Log.e(TAG, attrs.getAttributeName(i) + " , " + attrs.getAttributeValue(i));
//                }
//                return null;
//            }
//        });
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 试一下tag标签
                Toast.makeText(MainActivity.this, (String) v.getTag(R.id.tag), Toast.LENGTH_SHORT).show();
            }
        });

        // include里设置的id优先级高
        View includeView = findViewById(R.id.main_include);
        includeView.findViewById(R.id.textView2);
        // layout里面设置的id已经无效
//        View includeLayoutView = findViewById(R.id.main_include_layout);
//        includeLayoutView.findViewById(R.id.textView);
    }


    public void toSecond(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void toThird(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

}
