package ru.wearemad.mvptest.Core;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.wearemad.mvptest.Core.ResponseDataModel.Photo;
import ru.wearemad.mvptest.Core.ResponseDataModel.WallItemsResponse;

/**
 * Created by Artem on 06.07.2016.
 */
public interface VkApiInterface {

    @GET("wall.get?owner_id=-114128553&v=5.45")
    Call<WallItemsResponse> getWallItems();
}
