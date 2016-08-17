package ru.wearemad.mvptest.Core.ResponseDataModel;

/**
 * Created by Artem on 06.07.2016.
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WallItemsResponse {
    @SerializedName("response")
    @Expose
    private Response response;

    /**
     *
     * @return
     * The response
     */
    public Response getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    public void setResponse(Response response) {
        this.response = response;
    }

}
