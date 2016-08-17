package ru.wearemad.mvptest.Core.ResponseDataModel;

/**
 * Created by Artem on 06.07.2016.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Reposts {

    @SerializedName("count")
    @Expose
    private int count;

    /**
     *
     * @return
     * The count
     */
    public int getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(int count) {
        this.count = count;
    }

}
