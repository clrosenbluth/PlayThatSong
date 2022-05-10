import io.reactivex.Observable;
import json.ComposerSearch;
import json.OpenOpusService;
import json.WorkSearch;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetOpenOpus
{
    private final OpenOpusService service;

    public GetOpenOpus()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openopus.org")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(OpenOpusService.class);
    }

    public Observable<ComposerSearch> keywordSearch(String composerName)
    {
        return service.keywordSearch(composerName);
    }

    public Observable<WorkSearch> idSearch(int composerId)
    {
        return service.idSearch(composerId);
    }
}
