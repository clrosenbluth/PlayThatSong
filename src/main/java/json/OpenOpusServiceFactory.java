package json;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenOpusServiceFactory
{
    public OpenOpusService getInstance()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openopus.org")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(OpenOpusService.class);
    }
}
