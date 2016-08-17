package ru.wearemad.mvptest.Core.mvp.model;

import ru.wearemad.mvptest.Core.ResponseDataModel.WallItemsResponse;

/**
 * Created by Artem on 06.07.2016.
 */
public interface MainLoadItemsInteractor {

    interface onLoadFinishedListener {
        void OnSuccess(WallItemsResponse response);
        void OnFailed(String message);
    }
    void loadItems(onLoadFinishedListener listener);
}
