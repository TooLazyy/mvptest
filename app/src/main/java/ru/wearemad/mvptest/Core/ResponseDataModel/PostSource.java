package ru.wearemad.mvptest.Core.ResponseDataModel;

/**
 * Created by Artem on 06.07.2016.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class PostSource {

    @SerializedName("type")
    @Expose
    private String type;

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

}