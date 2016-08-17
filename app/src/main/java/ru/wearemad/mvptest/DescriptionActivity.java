package ru.wearemad.mvptest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ru.wearemad.mvptest.Core.mvp.presenter.DescriptionPresenter;
import ru.wearemad.mvptest.Core.mvp.presenter.DescriptionPresenterImpl;
import ru.wearemad.mvptest.Core.mvp.view.DescriptionView;

/**
 * Created by Artem on 09.07.2016.
 */
public class DescriptionActivity extends AppCompatActivity implements DescriptionView, Button.OnClickListener {

    private DescriptionPresenter presenter;
    private ImageView image;
    private Button button;
    private TextView tvTitle;
    private TextView tvText;
    private RelativeLayout loading;
    private ScrollView svContent;
    private Toolbar toolbar;
    private String link;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        setupView();
    }

    private void setupView () {
        HashMap data = (HashMap) getIntent().getSerializableExtra("data");
        List<String> photosArray = (List<String>)data.get(Constants.ATTR_IMAGE);
        final ArrayList<String> array = new ArrayList<>(photosArray);
        presenter = new DescriptionPresenterImpl(this,photosArray.get(0));
        image = (ImageView)findViewById(R.id.ivImage);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!array.isEmpty()) {
                    presenter.onImageClick(DescriptionActivity.this, array);
                }
            }
        });
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText("Фотография 1 из "+photosArray.size());
        tvText = (TextView)findViewById(R.id.tvText1);
        tvText.setText(data.get(Constants.ATTR_TEXT).toString().split("\n")[0]);
        button = (Button)findViewById(R.id.bn);
        button.setOnClickListener(this);
        loading = (RelativeLayout) findViewById(R.id.rlLoadingPanel);
        svContent = (ScrollView) findViewById(R.id.svContent);
        link = data.get(Constants.ATTR_TEXT).toString().split("\n")[1];
        toolbar = (Toolbar) findViewById(R.id.tbToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public void onClick(View view) {
        presenter.onItemClick(link, DescriptionActivity.this);
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
        svContent.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
        svContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void showFirstImage(Bitmap image) {
        this.image.setImageBitmap(image);
    }

    @Override
    public void showPlaceholderImage() {
        image.setImageResource(R.drawable.holder);
    }
}
