package json;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SingleComposerSearchService
{
    @GET("/work/list/composer/{id}/genre/all.json")
    Observable<WorkSearch> getWorkList(@Query("id")int composerId);
}
