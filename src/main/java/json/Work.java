package json;

import com.google.gson.annotations.SerializedName;

public class Work
{
    String title;
    String subtitle;
    @SerializedName("searchterms")
    String searchTerms;
    String popular;
    String recommended;
    String id;
    String genre;
}
