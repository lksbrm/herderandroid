package tv.lksbrm.herdervertretungsplan;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lksbrm
 */
public class VarManager
{


    public static MainActivity ma;


    public VarManager(MainActivity m)
    {
        ma = m;
    }


    public SharedPreferences.Editor getPreferencesEditor(String filename)
    {
        SharedPreferences preferences = ma.getSharedPreferences(filename, Context.MODE_PRIVATE);

        return preferences.edit();
    }

    public SharedPreferences getPreferences(String filename)
    {
        SharedPreferences preferences = ma.getSharedPreferences(filename, Context.MODE_PRIVATE);

        return preferences;
    }


}
