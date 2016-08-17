package ru.wearemad.mvptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.widget.TextView;

/**
 * Created by Artem on 09.02.2016.
 */
public class AboutActivity extends AppCompatActivity {

    private TextView about;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        toolbar = (Toolbar) findViewById(R.id.tbToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        about = (TextView)findViewById(R.id.tvDisplay);
        String data = "" +
                "web: https://wearemad.ru \n" +
                "Email: yes@wearemad.ru \n" +
                "Телефон: 8-961-680-42-51 \n";

        about.setText(data);
        Linkify.addLinks(about, Linkify.ALL);
    }
}
