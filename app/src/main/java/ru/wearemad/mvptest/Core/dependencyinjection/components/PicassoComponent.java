package ru.wearemad.mvptest.Core.dependencyinjection.components;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import ru.wearemad.mvptest.Core.dependencyinjection.modules.AppModule;
import ru.wearemad.mvptest.Core.dependencyinjection.modules.PicassoModule;
import ru.wearemad.mvptest.Core.mvp.model.DescriptionLoaderInteractorImpl;
import ru.wearemad.mvptest.Core.mvp.view.MainListViewAdapter;

/**
 * Created by Artem on 09.07.2016.
 */
@Singleton
@Component(modules = {AppModule.class, PicassoModule.class})
public interface PicassoComponent {
    void inject(DescriptionLoaderInteractorImpl loader);
    void inject(MainListViewAdapter adapter);
}
