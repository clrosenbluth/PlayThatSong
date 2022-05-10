import json.ComposerSearch;
import json.WorkSearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetOpenOpusTest
{
    GetOpenOpus getOpenOpus = new GetOpenOpus();

    @Test
    void keywordSearch()
    {
        // given
        String composerName = "bach";

        // when
        ComposerSearch composerSearch = getOpenOpus.keywordSearch(composerName).blockingFirst();

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
        WorkSearch workSearch = getOpenOpus.idSearch(composerId).blockingFirst();

        // then
        assertTrue(workSearch.getWorkNames().length > 0);
    }
}