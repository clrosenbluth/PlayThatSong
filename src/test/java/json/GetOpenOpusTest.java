package json;

import json.ComposerSearch;
import json.OpenOpusService;
import json.OpenOpusServiceFactory;
import json.WorkSearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GetOpenOpusTest
{
    OpenOpusServiceFactory factory = new OpenOpusServiceFactory();
    OpenOpusService service = factory.getInstance();

    @Test
    void keywordSearch()
    {
        // given
        String composerName = "bach";

        // when
        ComposerSearch composerSearch = service.keywordSearch(composerName).blockingGet();

        // then
        assertTrue(composerSearch.getComposerNames().length > 0);
        assertTrue(composerSearch.getComposerFullNames().length > 0);
        assertTrue(composerSearch.getComposerIds().length > 0);
        assertTrue(composerSearch.getComposerImages().length > 0);
    }

    @Test
    void idSearch()
    {
        // given
        int composerId = 100;

        // when
        WorkSearch workSearch = service.idSearch(composerId).blockingGet();

        // then
        assertTrue(workSearch.getWorkNames().length > 0);
    }
}