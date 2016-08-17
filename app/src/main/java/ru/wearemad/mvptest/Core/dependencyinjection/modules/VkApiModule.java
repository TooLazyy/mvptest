package ru.wearemad.mvptest.Core.dependencyinjection.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.wearemad.mvptest.Core.VkApiInterface;
import ru.wearemad.mvptest.Core.dependencyinjection.scopes.UserScope;

/**
 * Created by Artem on 06.07.2016.
 */
@Module
public class VkApiModule {

    @Provides
    @UserScope
    public VkApiInterface provideVkInterface (Retrofit retrofit) {
        return retrofit.create(VkApiInterface.class);
    }

}
