package ru.wearemad.mvptest.Core.mvp.presenter;

import ru.wearemad.mvptest.Core.mvp.view.FullScreenImageView;

/**
 * Created by Artem on 17.07.2016.
 */
public class FullScreenPresenterImpl implements FullScreenPresenter {

    private FullScreenImageView view;

    public FullScreenPresenterImpl() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
