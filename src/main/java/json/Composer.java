package json;

import com.google.gson.annotations.SerializedName;

public class Composer
{
    String id;
    String name;
    @SerializedName("complete_name")
    String completeName;
    String birth;
    String death;
    String epoch;
    String portrait;
}
