import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import json.ComposerSearch;
import json.OpenOpusService;

public class ComposerSearchPresenter
{
    private ComposerSearchFrame view;
    private OpenOpusService model;

    public ComposerSearchPresenter(ComposerSearchFrame view, OpenOpusService model)
    {
        this.view = view;
        this.model = model;
    }

    public void loadSearchResults(String searchTerm)
    {
        Disposable disposable = model.keywordSearch(searchTerm)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribe(this::onNext, this::onError);
    }

    private void onNext(ComposerSearch composerSearch)
    {
        // todo: add
        /*double temp = currentWeather.getTemperature();
        view.setTemperature(temp);*/
    }

    private void onError(Throwable throwable)
    {
        // todo: add
        /*view.showError("Error: " + throwable.getMessage());
        throwable.printStackTrace();*/
    }
}
