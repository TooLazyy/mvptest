package ru.wearemad.mvptest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

import ru.wearemad.mvptest.Core.mvp.presenter.FullScreenPresenterImpl;
import ru.wearemad.mvptest.Core.mvp.view.FullScreenImageAdapter;
import ru.wearemad.mvptest.Core.mvp.view.FullScreenImageView;

/**
 * Created by Artem on 15.02.2016.
 */
public class FullScreenViewActivity extends AppCompatActivity implements FullScreenImageView {


    private ViewPager viewPager;
    private FullScreenImageAdapter adapter;
    private FullScreenPresenterImpl presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        presenter = new FullScreenPresenterImpl();
        adapter = new FullScreenImageAdapter(this, (ArrayList<String>)getIntent()
                .getStringArrayListExtra("data"));
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
