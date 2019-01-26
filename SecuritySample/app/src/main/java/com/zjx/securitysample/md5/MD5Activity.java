package com.zjx.securitysample.md5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zjx.securitysample.R;
import com.zjx.securitysample.md5.MD5;
import com.zjx.securitysample.md5.MD5K;
import com.zjx.securitysample.md5.MD5Utils;

import java.io.IOException;

public class MD5Activity extends AppCompatActivity {

    private static final String ORIGINAL_CONTENT = "Hello MD5 in Java";

    private TextView mMD5BeforeView;
    private TextView mMD5AfterView;

    private StringBuilder mContentBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md5);
        mMD5BeforeView = findViewById(R.id.tv_md5_before);
        mMD5BeforeView.setText(ORIGINAL_CONTENT);
        mMD5AfterView = findViewById(R.id.tv_md5_after);
        mContentBuilder = new StringBuilder();
    }

    public void doMD5Original(View view) {
        String md5 = MD5.getInstance().getMD5(ORIGINAL_CONTENT);
        mContentBuilder.append("Result md5 by original: ").append("\n")
                .append(md5).append("\n");
        mMD5AfterView.setText(mContentBuilder.toString());
    }

    public void doMD5Str(View view) {
        String md5 = MD5Utils.getMD5ForStr(ORIGINAL_CONTENT);
        mContentBuilder.append("Result md5 for string: ").append("\n")
                .append(md5).append("\n");
        mMD5AfterView.setText(mContentBuilder.toString());
    }

    public void doMD5File(View view) {
        try {
            String md5 = MD5Utils.getMD5ForFile(getAssets().open("README.txt"));
            mContentBuilder.append("Result md5 for file: ").append("\n")
                    .append(md5).append("\n");
            mMD5AfterView.setText(mContentBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doMD5KStr(View view) {
        MD5K md5K = new MD5K();
        String md5 = md5K.getMD5ForStr(ORIGINAL_CONTENT);
        mContentBuilder.append("Result md5 by Kotlin for string: ").append("\n")
                .append(md5).append("\n");
        mMD5AfterView.setText(mContentBuilder.toString());
    }

    public void doMD5KFile(View view) {
        MD5K md5K = new MD5K();
        try {
            String md5 = md5K.getMD5ForFile(getAssets().open("README.txt"));
            mContentBuilder.append("Result md5 by Kotlin for file: ").append("\n")
                    .append(md5).append("\n");
            mMD5AfterView.setText(mContentBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
