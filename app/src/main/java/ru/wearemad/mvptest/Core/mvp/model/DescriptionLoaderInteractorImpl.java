package ru.wearemad.mvptest.Core.mvp.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import ru.wearemad.mvptest.R;
import ru.wearemad.mvptest.myApplication;

/**
 * Created by Artem on 09.07.2016.
 */
public class DescriptionLoaderInteractorImpl implements DescriptionLoaderInteractor{

    @Inject
    public Picasso picasso;

    public DescriptionLoaderInteractorImpl () {
        myApplication.getPicassoComponent().inject(this);
    }

    @Override
    public void loadImage(final onLoadFinishedListener listener, String url) {
        picasso.load(url)
                .error(R.drawable.holder)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        listener.onSuccess(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        listener.onFailed();
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }
}
