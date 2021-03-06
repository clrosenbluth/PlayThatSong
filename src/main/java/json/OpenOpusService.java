package json;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OpenOpusService
{
    @GET("/composer/list/search/{composer}.json")
    Single<ComposerSearch> keywordSearch(@Path("composer")String composerName);

    @GET("/work/list/composer/{id}/genre/all.json")
    Single<WorkSearch> idSearch(@Path("id")int composerId);
}
