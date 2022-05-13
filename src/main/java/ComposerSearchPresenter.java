import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import json.ComposerSearch;
import json.OpenOpusService;
import json.WorkSearch;

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
                .subscribe(this::onKeywordSearchNext, this::onKeywordSearchError);
    }

    private void onKeywordSearchNext(ComposerSearch composerSearch)
    {
        view.addSearchResults(
                composerSearch.getComposerFullNames(),
                composerSearch.getComposerIds());
        view.setInfo("Returning " + composerSearch.getComposerFullNames().length + " results");
    }

    private void onKeywordSearchError(Throwable throwable)
    {
        // todo: fix
        view.setInfo("Error: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    public void loadComposerResult(int composerId)
    {
        Disposable disposable = model.idSearch(composerId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribe(this::onIdSearchNext, this::onIdSearchError);
    }

    private void onIdSearchNext(WorkSearch workSearch)
    {
        // todo: add
    }

    private void onIdSearchError(Throwable throwable)
    {
        // todo: add
    }
}
