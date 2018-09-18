package tv.lksbrm.herdervertretungsplan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Lukas on 04.10.2017.
 */
public class FragmentFrame extends Fragment
{

    MainActivity mainActivity;

    View contentView;

    Toolbar t;

    ViewPager viewPager;
    TabLayout tabLayout;

    ViewPagerAdapter viewPagerAdapter;

    XMLManager xml;


    public void setContext(MainActivity ma)
    {
        mainActivity = ma;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        contentView = inflater.inflate(R.layout.fragment_frame, null);

        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), getActivity());

        tabLayout = (TabLayout) contentView.findViewById(R.id.tablayout);
        viewPager = (ViewPager) contentView.findViewById(R.id.viewpager);

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

        ViewCompat.setElevation(tabLayout, 15);

        xml = new XMLManager(mainActivity);
        xml.downloadData();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });

        return contentView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }


}
