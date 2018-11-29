package com.zjx.sample.inflater;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * 这里仅模拟处理TextView和Button
 */
public class MyFactory implements LayoutInflater.Factory2 {

    public LayoutInflater.Factory mOriginalFactory;
    // 这个模拟新资源
    private Map<String, String> mColorMap;

    public MyFactory(LayoutInflater.Factory factory) {
        this.mOriginalFactory = factory;

        mColorMap = new HashMap<>();
        mColorMap.put("third_tv_text_color", "#0000ff");
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return onCreateView(name, context, attrs);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (mOriginalFactory != null) {
            view = mOriginalFactory.onCreateView(name, context, attrs);
        }

        if ("TextView".equals(name)) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
            // 注意这里的属性名：android:textColor，不用自定义命名空间
            int resourceId = ta.getResourceId(R.styleable.MyTextView_android_textColor, -1);
            String resourceName = context.getResources().getResourceName(resourceId);
            resourceName = resourceName.substring(resourceName.lastIndexOf('/') + 1);
            view = new TextView(context, attrs); // 可以直接修改原TextView的属性
            ta.recycle();
            if (view == null) {
                // 也可以直接替换成自定义TextView
                view = new MyTextView(context, attrs);
            } else {
                // 这里模拟替换原TextView的textColor属性值
                String color = mColorMap.get(resourceName);
                ((TextView) view).setTextColor(Color.parseColor(color));
            }
        }

        if ("Button".equals(name)) {
            view = new Button(context, attrs);
            // 读取自定义的属性值
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundButton);
            float radius = ta.getDimension(R.styleable.RoundButton_cornerRadius, 0);
            float strokeWidth = ta.getDimension(R.styleable.RoundButton_strokeWidth, 0);
            int strokeColor = ta.getColor(R.styleable.RoundButton_strokeColor, -1);
            // 构造圆角按钮
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(DensityUtil.dip2px(context, radius));
            drawable.setStroke(DensityUtil.dip2px(context, strokeWidth), strokeColor);
            view.setBackground(drawable);
            ta.recycle();
        }

        return view;
    }

    class MyTextView extends TextView {

        public MyTextView(Context context) {
            super(context);
        }

        public MyTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
            setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }

    }

}
