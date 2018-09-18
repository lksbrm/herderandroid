package tv.lksbrm.herdervertretungsplan;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by lksbrm
 */
public class VarManager
{


    public static Activity ma;

    public static ArrayList<SubstElement> usersl = new ArrayList<SubstElement>();

    public static String DATE;

    public static boolean LOADED = false;

    public static int C_TIME = 1000;

    public static String DATE_TOMORROW = "";

    public static String display_name = "";
    public static String stufe = "";
    public static String token = "";
    public static String date_today = "";
    public static String date_tomorrow = "";


    public VarManager(Activity m)
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
