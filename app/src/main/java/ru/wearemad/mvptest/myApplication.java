package ru.wearemad.mvptest;

import android.app.Application;


import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import ru.wearemad.mvptest.Core.dependencyinjection.components.ApiComponent;
import ru.wearemad.mvptest.Core.dependencyinjection.components.DaggerApiComponent;
import ru.wearemad.mvptest.Core.dependencyinjection.components.DaggerPicassoComponent;
import ru.wearemad.mvptest.Core.dependencyinjection.components.DaggerVkComponent;
import ru.wearemad.mvptest.Core.dependencyinjection.components.PicassoComponent;
import ru.wearemad.mvptest.Core.dependencyinjection.components.VkComponent;
import ru.wearemad.mvptest.Core.dependencyinjection.modules.ApiModule;
import ru.wearemad.mvptest.Core.dependencyinjection.modules.AppModule;
import ru.wearemad.mvptest.Core.dependencyinjection.modules.PicassoModule;
import ru.wearemad.mvptest.Core.dependencyinjection.modules.VkApiModule;
import ru.wearemad.mvptest.Core.mvp.model.MainLoadItemsInteractorImpl;

/**
 * Created by Artem on 06.07.2016.
 */
public class myApplication extends Application {

    private ApiComponent apiComponent;
    private static VkComponent vkComponent;
    private static PicassoComponent picassoComponent;
    @Inject
    MainLoadItemsInteractorImpl loader;

    @Override
    public void onCreate() {
        super.onCreate();

        apiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(Constants.BASE_URL))
                .build();

        vkComponent = DaggerVkComponent.builder()
                .apiComponent(apiComponent)
                .vkApiModule(new VkApiModule())
                .build();

        picassoComponent = DaggerPicassoComponent.builder()
                .appModule(new AppModule(this))
                .picassoModule(new PicassoModule())
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }

    public static VkComponent getVkComponent () {
        return vkComponent;
    }

    public static PicassoComponent getPicassoComponent() {
        return picassoComponent;
    }

}
