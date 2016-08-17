package ru.wearemad.mvptest.Core.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.wearemad.mvptest.R;

/**
 * Created by Artem on 15.02.2016.
 */
public class FullScreenImageAdapter extends PagerAdapter {

    private Activity activity;
    private ArrayList<String> images;
    private LayoutInflater inflater;

    public FullScreenImageAdapter(Activity activity,
                                  ArrayList<String> arrayList) {
        this.activity = activity;
        this.images = arrayList;
        inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgDisplay;
        Button btnClose;

        View viewLayout = inflater.inflate(R.layout.item_full_screen_image, container,
                false);

        imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);
        btnClose = (Button) viewLayout.findViewById(R.id.btnClose);

        Picasso.with(activity.getApplicationContext())
                .load(images.get(position))
                .error(R.drawable.holder)
                .into(imgDisplay);

        // close button click event
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        (container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView((RelativeLayout) object);

    }
}
