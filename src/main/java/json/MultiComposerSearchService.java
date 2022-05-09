package json;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MultiComposerSearchService
{
    @GET("/composer/list/search/{comp}.json")
    Observable<ComposerSearch> getComposerList(@Path("comp")String composerName);
}
