package json;

public class ComposerSearch
{
    Status status;
    Composer[] composers;

    public String[] getComposerNames()
    {
        String[] names = new String[status.rows];
        for (int i = 0; i < status.rows; i++)
        {
            names[i] = composers[i].name;
        }
        return names;
    }

    public String[] getComposerFullNames()
    {
        String[] names = new String[status.rows];
        for (int i = 0; i < status.rows; i++)
        {
            names[i] = composers[i].completeName;
        }
        return names;
    }

    public int[] getComposerIds()
    {
        int[] ids = new int[status.rows];
        for (int i = 0; i < status.rows; i++)
        {
            ids[i] = Integer.parseInt(composers[i].id);
        }
        return ids;
    }

    public String[] getComposerImages()
    {
        String[] images = new String[status.rows];
        for (int i = 0; i < status.rows; i++)
        {
            images[i] = composers[i].portrait;
        }
        return images;
    }
}
