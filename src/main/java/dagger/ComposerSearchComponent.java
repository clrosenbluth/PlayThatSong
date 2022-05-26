package dagger;

import javax.inject.Singleton;

@Singleton
@Component(modules = { ComposerSearchModule.class })
public interface ComposerSearchComponent
{
    ComposerSearchFrame getComposerSearchFrame();
}