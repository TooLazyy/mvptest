package ru.wearemad.mvptest.Core.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Retrofit;
import ru.wearemad.mvptest.AboutActivity;
import ru.wearemad.mvptest.Constants;
import ru.wearemad.mvptest.Core.ResponseDataModel.WallItemsResponse;
import ru.wearemad.mvptest.Core.VkApiInterface;
import ru.wearemad.mvptest.Core.mvp.model.MainLoadItemsInteractor;
import ru.wearemad.mvptest.Core.mvp.model.MainLoadItemsInteractorImpl;
import ru.wearemad.mvptest.Core.mvp.view.MainView;
import ru.wearemad.mvptest.DescriptionActivity;
import ru.wearemad.mvptest.MainActivity;
import ru.wearemad.mvptest.R;
import ru.wearemad.mvptest.myApplication;

/**
 * Created by Artem on 06.07.2016.
 */
public class MainPresenterImpl implements MainPresenter, MainLoadItemsInteractor.onLoadFinishedListener {

    private MainView mainView;
    private MainLoadItemsInteractorImpl mainLoadItemsInteractor;


    public MainPresenterImpl (MainView view) {
        mainView = view;
        mainLoadItemsInteractor = new MainLoadItemsInteractorImpl();
    }

    @Override
    public void onResume() {
        if(mainView!=null) {
            mainView.showLoading();
        }
        mainLoadItemsInteractor.loadItems(this);
    }

    @Override
    public void onItemClick(HashMap item, Context c) {
        if(mainView!=null) {
            openActivity(item, c);
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void OnSuccess(WallItemsResponse response) {
        if (mainView != null) {
            mainView.setupAdapterData(convertResponse(response));
            mainView.hideLoading();
        }
    }

    @Override
    public void OnFailed(String message) {
        if (mainView != null) {
            mainView.showError(message);
            mainView.hideLoading();
        }
    }

    private void openActivity (HashMap item, Context c) {
        Intent intent = new Intent(c, DescriptionActivity.class);
        intent.putExtra("data", item);
        mainView.openDescriptionActivity(intent);
    }

    @Override
    public void onRefresh() {
        mainView.showLoading();
        mainLoadItemsInteractor.loadItems(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainView.stopRefresh();
            }
        },1500);
    }

    @Override
    public void menuItemSelected(MenuItem item, Context context) {
        switch (item.getItemId()) {
            case R.id.act_group:
                openLink(context);
                break;
            case R.id.act_exit:
                exit(context);
                break;
            case R.id.act_about:
                startAboutActivity(context);
                break;
            default:
                break;
        }
    }

    private void startAboutActivity(Context c) {
        c.startActivity(new Intent(c, AboutActivity.class));
    }

    private void openLink(Context c) {
        Uri href = Uri.parse("http://vk.com/stuff_group");
        Intent browser = new Intent(Intent.ACTION_VIEW, href);
        c.startActivity(browser);
    }

    private void exit (Context c) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(intent);
    }

    private ArrayList<HashMap<String, Object>> convertResponse(WallItemsResponse response) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();
        if (response.getResponse().getCount()>0) {
            for (int i=0; i < response.getResponse().getItems().size(); i++) {
                HashMap m = new HashMap<>();
                m.put(Constants.ATTR_TITLE, response.getResponse()
                        .getItems().get(i)
                        .getText().split("\n")[0]);
                m.put(Constants.ATTR_TEXT, response.getResponse().getItems().get(i).getText());

                List<String> photos = new ArrayList<>();
                if(!response.getResponse().getItems().get(i).getAttachments().isEmpty()) {
                    String image;
                    for (int j=0; j<response.getResponse().getItems().get(i).getAttachments().size(); j++) {
                       if (response.getResponse().getItems().get(i).getAttachments().get(j).getPhoto().getPhoto1280()!=null) {
                           image = response.getResponse().getItems().get(i).getAttachments().get(j).getPhoto().getPhoto1280();
                       } else if(response.getResponse().getItems().get(i).getAttachments().get(j).getPhoto().getPhoto807()!=null) {
                           image = response.getResponse().getItems().get(i).getAttachments().get(j).getPhoto().getPhoto807();
                       } else if (response.getResponse().getItems().get(i).getAttachments().get(j).getPhoto().getPhoto604()!=null) {
                           image = response.getResponse().getItems().get(i).getAttachments().get(j).getPhoto().getPhoto604();
                       } else if (response.getResponse().getItems().get(i).getAttachments().get(j).getPhoto().getPhoto130()!=null) {
                           image = response.getResponse().getItems().get(i).getAttachments().get(j).getPhoto().getPhoto130();
                       } else if (response.getResponse().getItems().get(i).getAttachments().get(j).getPhoto().getPhoto75()!=null) {
                           image = response.getResponse().getItems().get(i).getAttachments().get(j).getPhoto().getPhoto75();
                       } else {
                           image = "-1";
                       }
                       if (!image.equals("-1")) {
                           photos.add(image);
                       }
                    }
                }

                m.put(Constants.ATTR_IMAGE, photos);
                data.add(m);
            }
        }
        return data;
    }
}
