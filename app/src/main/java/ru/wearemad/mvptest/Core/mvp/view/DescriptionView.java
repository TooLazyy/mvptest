package ru.wearemad.mvptest.Core.mvp.view;

import android.graphics.Bitmap;

/**
 * Created by Artem on 09.07.2016.
 */
public interface DescriptionView {

    void showLoading ();
    void hideLoading ();
    void showError(String message);
    void showFirstImage(Bitmap image);
    void showPlaceholderImage();
}
