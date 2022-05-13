package json;

public class WorkSearch
{
    Status status;
    Work[] works;

    public String[] getWorkNames()
    {
        String[] names = new String[status.rows];
        for (int i = 0; i < status.rows; i++)
        {
            names[i] = works[i].title;
        }
        return names;
    }
}
