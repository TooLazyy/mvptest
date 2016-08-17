package ru.wearemad.mvptest.Core.mvp.presenter;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Artem on 09.07.2016.
 */
public interface DescriptionPresenter {

    void onResume();
    void onItemClick(String link, Context context);
    void onDestroy();
    void onImageClick(Context c, ArrayList<String> urls);
}
