package ru.wearemad.mvptest.Core.dependencyinjection.components;

import dagger.Component;
import ru.wearemad.mvptest.Core.dependencyinjection.modules.VkApiModule;
import ru.wearemad.mvptest.Core.dependencyinjection.scopes.UserScope;
import ru.wearemad.mvptest.Core.mvp.model.MainLoadItemsInteractorImpl;
import ru.wearemad.mvptest.MainActivity;

/**
 * Created by Artem on 06.07.2016.
 */
@UserScope
@Component(dependencies = ApiComponent.class, modules = VkApiModule.class)
public interface VkComponent {
    void inject(MainActivity activity);
    void inject(MainLoadItemsInteractorImpl loader);
}
