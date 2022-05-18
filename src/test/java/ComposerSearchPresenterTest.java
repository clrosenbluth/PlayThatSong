import io.reactivex.Single;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import json.ComposerSearch;
import json.OpenOpusService;
import json.WorkSearch;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComposerSearchPresenterTest
{
    private final ComposerSearchFrame view = mock(ComposerSearchFrame.class);
    private final OpenOpusService model = mock(OpenOpusService.class);
    private final ComposerSearchPresenter presenter = new ComposerSearchPresenter(view, model);

    @BeforeAll
    public static void beforeAllTests()
    {
        RxJavaPlugins.setIoSchedulerHandler((scheduler) -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler((scheduler) -> Schedulers.trampoline());
    }

    @Test
    public void loadSearchResults()
    {
        // given
        String[] composerList = new String[] {"JS Bach"};
        int[] idList = new int[] {1};

        ComposerSearch composerSearch = mock(ComposerSearch.class);
        doReturn(composerList)
                .when(composerSearch).getComposerFullNames();
        doReturn(idList)
                .when(composerSearch).getComposerIds();
        doReturn(Single.just(composerSearch))
                .when(model).keywordSearch("Bach");

        // when
        presenter.loadSearchResults("Bach");

        // then
        verify(view).addComposerSearchResults(composerList);
    }

    @Test
    public void loadComposerResults()
    {
        // given
        String[] workList = new String[] {"Concerto"};

        WorkSearch workSearch = mock(WorkSearch.class);
        doReturn(workList)
                .when(workSearch).getWorkNames();
        doReturn(Single.just(workSearch))
                .when(model).idSearch(5);

        // when
        presenter.loadComposerResult(5);

        // then
        verify(view).setComposerWorks(workList);
    }

    /*
    @Test
    public void openWorkInBrowser()
    {
        // given
        String workName = "concerto";

        // when
        presenter.openWorkInBrowser(workName);

        // then
        verify(view, Mockito.times(1)).setInfo(Mockito.anyString());
    }
     */

}