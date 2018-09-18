package tv.lksbrm.herdervertretungsplan;

/**
 * Created by lksbrm
 */
public class User
{

    private String NAME;
    private String HASH;
    private String ID;
    private String DISPLAYNAME;
    private String REGISTEREDSINCE;


    public User(String name, String hash, String id, String displayname, String registeredsince)
    {
        NAME = name;
        HASH = hash;
        ID = id;
        DISPLAYNAME = displayname;
        REGISTEREDSINCE = registeredsince;
    }

    public User()
    {

    }

    public String getName()
    {
        return NAME;
    }

    public String getID()
    {
        return ID;
    }

    public String getHash()
    {
        return HASH;
    }

    public String getDisplayName()
    {
        return DISPLAYNAME;
    }

    public String getDate()
    {
        return REGISTEREDSINCE;
    }

    public void setName(String name)
    {
        NAME = name;
    }

    public void setHash(String hash)
    {
        HASH = hash;
    }

    public void setID(String id)
    {
        ID = id;
    }

    public void setDisplayName(String displayname)
    {
        DISPLAYNAME = displayname;
    }

    public void setDate(String registeredSince)
    {
        REGISTEREDSINCE = registeredSince;
    }

}
