package tv.lksbrm.herdervertretungsplan;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lukas on 03.10.2017.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter
{

    Activity ma;

    public ViewPagerAdapter(FragmentManager fm, Activity mainActivity)
    {
        super(fm);
        ma = mainActivity;
    }

    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("d.M.yyyy");

    String[] titles = {format.format(date), "Nächster Tag"};


    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                FragmentToday fragmentToday = new FragmentToday();
                fragmentToday.setContext(ma);
                return fragmentToday;

            case 1:
                FragmentTomorrow fragmentTomorrow = new FragmentTomorrow();
                fragmentTomorrow.setContext(ma);
                return fragmentTomorrow;
        }

        return null;
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titles[position];
    }


    public String getFileContent(
            InputStream fis) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));


            String line;
            while(( line = br.readLine()) != null ) {
                sb.append( line );
                sb.append( '\n' );
            }
            return sb.toString();
        }catch(Exception e)
        {

        }
        return sb.toString();
    }
}
