package ru.wearemad.mvptest.Core.mvp.presenter;

import android.content.Context;
import android.view.MenuItem;

import java.util.HashMap;
/**
 * Created by Artem on 06.07.2016.
 */
public interface MainPresenter {

    void onResume();
    void onItemClick(HashMap item, Context c);
    void onDestroy();
    void onRefresh();
    void menuItemSelected(MenuItem item, Context context);
}
