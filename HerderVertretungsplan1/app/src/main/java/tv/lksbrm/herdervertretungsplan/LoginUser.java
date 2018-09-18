package tv.lksbrm.herdervertretungsplan;

//Created by lksbrm

public class LoginUser
{
    String NAME;
    String HASH;

    public LoginUser(String name, String hash)
    {
        NAME = name;
        HASH = hash;
    }

    public String getName()
    {
        return NAME;
    }

    public String getHash()
    {
        return HASH;
    }
}
