package dagger;

import json.OpenOpusService;
import json.OpenOpusServiceFactory;

import javax.inject.Singleton;

@Module
public class ComposerSearchModule
{
    @Singleton
    @Provides
    public OpenOpusService providesOpenOpusService(
            OpenOpusServiceFactory factory
    )
    {
        return factory.getInstance();
    }
}
