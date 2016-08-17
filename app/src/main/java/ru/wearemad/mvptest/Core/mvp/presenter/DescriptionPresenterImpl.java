package ru.wearemad.mvptest.Core.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import ru.wearemad.mvptest.Core.mvp.model.DescriptionLoaderInteractor;
import ru.wearemad.mvptest.Core.mvp.model.DescriptionLoaderInteractorImpl;
import ru.wearemad.mvptest.Core.mvp.view.DescriptionView;
import ru.wearemad.mvptest.FullScreenViewActivity;

/**
 * Created by Artem on 09.07.2016.
 */
public class DescriptionPresenterImpl implements DescriptionPresenter, DescriptionLoaderInteractor.onLoadFinishedListener {

    private DescriptionView view;
    private DescriptionLoaderInteractorImpl loader;
    private String url;

    public DescriptionPresenterImpl (DescriptionView view, String url) {
        this.view = view;
        loader = new DescriptionLoaderInteractorImpl();
        this.url = url;
    }

    @Override
    public void onResume() {
        if(view!=null) {
            view.showLoading();
        }
        loader.loadImage(this, url);
    }

    @Override
    public void onItemClick(String link, Context c) {
        if (link.length()>0) {
            Uri href = Uri.parse(link);
            Intent browser = new Intent(Intent.ACTION_VIEW, href);
            c.startActivity(browser);
        } else {
            if(view!=null) {
                view.showError("Ссылки нет(");
            }
        }
    }

    @Override
    public void onSuccess(Bitmap image) {
        if (view != null) {
            view.showFirstImage(image);
            view.hideLoading();
        }
    }

    @Override
    public void onFailed() {
        if (view != null) {
            view.showPlaceholderImage();
            view.hideLoading();
            view.showError("Ошибка загрузки, попробуйте ещё раз.");
        }
    }

    @Override
    public void onImageClick(Context c, ArrayList<String> urls) {
        Intent intent = new Intent(c, FullScreenViewActivity.class);
        intent.putStringArrayListExtra("data", urls);
        c.startActivity(intent);
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
