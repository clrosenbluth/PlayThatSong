import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import json.ComposerSearch;
import json.OpenOpusService;
import json.WorkSearch;

import java.awt.*;
import java.io.IOException;
import java.net.*;

public class ComposerSearchPresenter
{
    private ComposerSearchFrame view;
    private OpenOpusService model;
    private int[] composerIds;

    public ComposerSearchPresenter(ComposerSearchFrame view, OpenOpusService model)
    {
        this.view = view;
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
        view.addComposerSearchResults(composerSearch.getComposerFullNames());
        view.setInfo("Returning " + composerSearch.getComposerFullNames().length + " results");
    }

    private void onKeywordSearchError(Throwable throwable)
    {
        view.setInfo("Error: " + throwable.getMessage());
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
        view.setComposerWorks(workSearch.getWorkNames());
        view.setInfo("Showing works");
    }

    private void onIdSearchError(Throwable throwable)
    {
        view.setInfo("Error: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    public void openWorkInBrowser(String workName)
    {
        try
        {
            String urlString = "https://www.youtube.com/results?search_query="
                    + URLEncoder.encode(workName, "UTF-8");
            Desktop.getDesktop().browse(new URI(urlString));
            view.setInfo(urlString);
        } catch (URISyntaxException | IOException e)
        {
            view.setInfo(e.getMessage());
            e.printStackTrace();
        }
    }

    public void setComposerIds(int[] ids)
    {
        this.composerIds = ids;
    }
}
