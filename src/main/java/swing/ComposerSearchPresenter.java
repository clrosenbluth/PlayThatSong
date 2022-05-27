package swing;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import json.ComposerSearch;
import json.OpenOpusService;
import json.WorkSearch;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.awt.*;
import java.io.IOException;
import java.net.*;

@Singleton
public class ComposerSearchPresenter
{
    private final Provider<ComposerSearchFrame> viewProvider;
    private final OpenOpusService model;
    private int[] composerIds;

    @Inject
    public ComposerSearchPresenter(
            Provider<ComposerSearchFrame> viewProvider,
            OpenOpusService model)
    {
        this.viewProvider = viewProvider;
        this.model = model;
    }

    public void loadSearchResults(String searchTerm)
    {
        Disposable disposable = model.keywordSearch(searchTerm)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(this::onKeywordSearchNext, this::onKeywordSearchError);
    }

    private void onKeywordSearchNext(ComposerSearch composerSearch)
    {
        composerIds = composerSearch.getComposerIds();
        viewProvider.get().addComposerSearchResults(composerSearch.getComposerFullNames());
        viewProvider.get().setInfo("Returning "
                + composerSearch.getComposerFullNames().length
                + " results");
    }

    private void onKeywordSearchError(Throwable throwable)
    {
        viewProvider.get().setInfo("Error: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    public void loadComposerResult(int index)
    {
        Disposable disposable = model.idSearch(composerIds[index])
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(this::onIdSearchNext, this::onIdSearchError);
    }

    private void onIdSearchNext(WorkSearch workSearch)
    {
        viewProvider.get().setComposerWorks(workSearch.getWorkNames());
        viewProvider.get().setInfo("Showing works");
    }

    private void onIdSearchError(Throwable throwable)
    {
        viewProvider.get().setInfo("Error: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    public void openWorkInBrowser(String workName)
    {
        try
        {
            String urlString = "https://www.youtube.com/results?search_query="
                    + URLEncoder.encode(workName, "UTF-8");
            Desktop.getDesktop().browse(new URI(urlString));
            viewProvider.get().setInfo(urlString);
        } catch (URISyntaxException | IOException e)
        {
            viewProvider.get().setInfo(e.getMessage());
            e.printStackTrace();
        }
    }

    public void setComposerIds(int[] ids)
    {
        this.composerIds = ids;
    }
}
