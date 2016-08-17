package ru.wearemad.mvptest.Core.mvp.model;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.wearemad.mvptest.Core.ResponseDataModel.WallItemsResponse;
import ru.wearemad.mvptest.Core.VkApiInterface;
import ru.wearemad.mvptest.myApplication;

/**
 * Created by Artem on 06.07.2016.
 */
public class MainLoadItemsInteractorImpl implements MainLoadItemsInteractor {

    @Inject
    Retrofit retrofit;
    @Inject
    VkApiInterface vkApiInterface;

    public MainLoadItemsInteractorImpl () {
        myApplication.getVkComponent().inject(this);
    }

    @Override
    public void loadItems(final onLoadFinishedListener listener) {

        Call<WallItemsResponse> call = vkApiInterface.getWallItems();
        call.enqueue(new Callback<WallItemsResponse>() {
            @Override
            public void onResponse(Call<WallItemsResponse> call, Response<WallItemsResponse> response) {
                if (response.isSuccessful()) {
                    listener.OnSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<WallItemsResponse> call, Throwable t) {
                listener.OnFailed("Ошибка, попробуйте ещё раз!");
            }
        });

    }
}
