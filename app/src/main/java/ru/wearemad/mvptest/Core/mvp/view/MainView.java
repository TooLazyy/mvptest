package ru.wearemad.mvptest.Core.mvp.view;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Artem on 06.07.2016.
 */
public interface MainView {

    void showLoading();
    void hideLoading();
    void showError(String message);
    void setupAdapterData(ArrayList<HashMap<String, Object>> data);
    void openDescriptionActivity(Intent intent);
    void stopRefresh();
}
