package json;

import com.google.gson.annotations.SerializedName;

public class Status
{
    String success;
    String source;
    int rows;
    @SerializedName("processingtime")
    double processingTime;
    String api;
    String version;
}
