package com.zjx.sample.proguard;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zjx.sample.proguard.model.Movie;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTV = findViewById(R.id.textView);
    }

    public void toastMsg(View view) {
        Toast.makeText(this, "我就是想Toast", Toast.LENGTH_SHORT).show();
    }

    public void testReflect(View view) {
        try {
            Class<?> clazz = Class.forName("com.zjx.sample.proguard.model.Dog");
            Field breedField = clazz.getDeclaredField("breed");
            breedField.setAccessible(true);
            String breed = (String) breedField.get(clazz.newInstance());
            Toast.makeText(this, "Dog breed: " + breed, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Dog breed: Exception", Toast.LENGTH_SHORT).show();
        }
    }

    public void testJson(View view) {
        Movie movie1 = new Movie("一出好戏", 120, 50, "http://test/movie1.png");
        Movie movie2 = new Movie("德鲁大叔", 140, 49, "http://test/movie2.png");
        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        final Gson gson = new Gson();
        // 不混淆的话属性名会变
        String moviesJson = gson.toJson(movies);
        mTV.setText(moviesJson);
        Type type = new TypeToken<List<Movie>>(){}.getType();
        List<Movie> fromMovies = gson.fromJson(moviesJson, type);
        Toast.makeText(this, "movie name : " + fromMovies.get(0).name, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String jstr = "{\"name\":\"复联\", \"time\":180, \"money\":38, \"poster\":\"http://test/movie3.png\"}";
                // 不混淆的话为null
                Movie movie3 = gson.fromJson(jstr, Movie.class);
                Toast.makeText(MainActivity.this, "movie3 name: " + movie3.name, Toast.LENGTH_SHORT).show();
            }
        }, 2000);

    }
}
