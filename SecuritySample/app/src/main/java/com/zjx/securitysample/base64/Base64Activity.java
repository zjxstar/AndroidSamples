package com.zjx.securitysample.base64;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zjx.securitysample.R;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class Base64Activity  extends AppCompatActivity {

    private static final String TAG = Base64Activity.class.getSimpleName();
    private TextView mContentView;
    private StringBuilder mContentBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base64);

        mContentView = findViewById(R.id.content);
        mContentBuilder = new StringBuilder();
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void testByJava(View view) {
        try {
            // 使用基本编码
            String base64encodedString = java.util.Base64.getEncoder().encodeToString("Hello java8 Base64".getBytes("utf-8"));
            Log.d(TAG, "Base64 编码字符串 (基本) :" + base64encodedString);

            // 解码
            byte[] base64decodedBytes = java.util.Base64.getDecoder().decode(base64encodedString);

            Log.d(TAG, "原始字符串: " + new String(base64decodedBytes, "utf-8"));
            base64encodedString = java.util.Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
            Log.d(TAG, "Base64 编码字符串 (URL) :" + base64encodedString);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 5; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }

            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = java.util.Base64.getMimeEncoder().encodeToString(mimeBytes);
            Log.d(TAG, "Base64 编码字符串 (MIME) :" + mimeEncodedString);

            Log.d(TAG, "使用Android的Base64 NO_WRAP 不会换行: " + Base64.encodeToString(mimeBytes, Base64.NO_WRAP));
//            String result = java.util.Base64.getEncoder().encodeToString("Ma".getBytes("utf-8"));
//            byte[] resultb = java.util.Base64.getDecoder().decode(result);
//            Log.d(TAG, "testByJava: " + new String(resultb));
        }catch(UnsupportedEncodingException e){
            Log.e(TAG, "Error :" + e.getMessage());
        }
    }

    public void encodeTest(View view) {
        try {
            byte[] encodeStr = Base64.encode("Man".getBytes("utf-8"), Base64.DEFAULT);
            mContentBuilder.append("原文: Man").append("\n");
            mContentBuilder.append("使用Android的Base64 encode DEFAULT：").append(new String(encodeStr));
            mContentBuilder.append("\n");
            mContentBuilder.append("原文：Ma").append("\n");
            mContentBuilder.append("一个=号：").append(Base64.encodeToString("Ma".getBytes("utf-8"), Base64.DEFAULT));
            mContentBuilder.append("\n");
            mContentBuilder.append("原文：M").append("\n");
            mContentBuilder.append("两个=号：").append(Base64.encodeToString("M".getBytes("utf-8"), Base64.DEFAULT));
            mContentBuilder.append("\n");
            mContentBuilder.append("原文：M").append("\n");
            mContentBuilder.append("使用NO_PADDING，去除两个=号：").append(Base64.encodeToString("M".getBytes("utf-8"), Base64.NO_PADDING));
            mContentView.setText(mContentBuilder.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void encodeUrlSafe(View view) {
        try {
            mContentBuilder.append("\n");
            mContentBuilder.append("原文：TutorialsPoint?java8").append("\n");
            mContentBuilder.append("URL Safe encode：");
            mContentBuilder.append(Base64.encodeToString("TutorialsPoint?java8".getBytes("utf-8"), Base64.URL_SAFE));
            mContentView.setText(mContentBuilder.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void decodeTest(View view) {
        byte[] result = Base64.decode("SGVsbG8gamF2YTggQmFzZTY0", Base64.DEFAULT);
        mContentBuilder.append("\n");
        mContentBuilder.append("原文：SGVsbG8gamF2YTggQmFzZTY0").append("\n");
        mContentBuilder.append("Decode: ").append(new String(result));
        mContentView.setText(mContentBuilder.toString());
    }

}
