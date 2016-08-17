package ru.wearemad.mvptest.Core.mvp.model;

import android.graphics.Bitmap;

/**
 * Created by Artem on 09.07.2016.
 */
public interface DescriptionLoaderInteractor {

    interface onLoadFinishedListener {
        void onSuccess(Bitmap image);
        void onFailed();
    }
    void loadImage(onLoadFinishedListener listener, String url);
}
