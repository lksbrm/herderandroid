package tv.lksbrm.herdervertretungsplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Lukas on 03.10.2017.
 */
public class FragmentToday extends Fragment
{

    //Instanz von VarManager
    public static VarManager varManager;

    //Instanz von XMLManager wird init. in onCreate
    XMLManager xml;

    //Recycler View contentView
    RecyclerView.Adapter rvadapter;
    RecyclerView.LayoutManager rvlayoutmanager;

    ArrayList<SubstElement> substElements;

    Toolbar t;

    Activity mainActivity;
    

    View contentView;

    static final String DATEC = "today";

    RecyclerView rv;

    private SwipeRefreshLayout swipeContainer;

    public void setContext(Activity ma)
    {
        this.mainActivity = ma;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        contentView = inflater.inflate(R.layout.fragment_today, null);

        rv = (RecyclerView) contentView.findViewById(R.id.recycler1);

        rvlayoutmanager = new LinearLayoutManager(getActivity());
        swipeContainer = (SwipeRefreshLayout) contentView.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                refreshTable();
                swipeContainer.setRefreshing(false);
            }
        });


        varManager = new VarManager(mainActivity);


        t = (Toolbar) contentView.findViewById(R.id.toolbar);

        xml = new XMLManager(mainActivity);

        rv.setLayoutManager(rvlayoutmanager);

        init();


        Toolbar t = (Toolbar) contentView.findViewById(R.id.toolbar);

        try {
            xml.setSubst(DATEC);
            substElements = xml.getSubst();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(Integer.valueOf(Build.VERSION.SDK) >= 21)
        {
            //ViewCompat.setElevation(t, 4);
        }


        MobileAds.initialize(getActivity(), "ca-app-pub-6959636925815241~9382737799");

        varManager = new VarManager(mainActivity);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(substElements.size() == 0) {
                    mainActivity.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            TextView tv = (TextView) contentView.findViewById(R.id.nothing);
                            tv.setText("Für morgen liegen noch keine Vertretungsplandaten vor.");
                        }
                    });
                }
            }
        }).start();

        Date dt = new Date();
        SimpleDateFormat df = new SimpleDateFormat("d.M.yyyy");

        refreshTable();


        return contentView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    private void init()
    {
        LoginManager lm = new LoginManager(mainActivity);
        //Checkt, ob login noch aktuell
        lm.loginUser();

    }

    public void refreshTable()
    {
        try {
            xml.downloadData();
            xml.setSubst(DATEC);
            substElements = xml.getSubst();
            rvadapter = new SubstAdapter(mainActivity, substElements);
            rv.setAdapter(rvadapter);
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(substElements.size() == 0) {
                        mainActivity.runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                TextView tv = (TextView) contentView.findViewById(R.id.nothing);
                                tv.setText("Für heute liegen noch keine Vertretungsplandaten vor.");
                            }
                        });
                    }else
                    {
                        mainActivity.runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                TextView tv = (TextView) contentView.findViewById(R.id.nothing);
                                tv.setText("");
                            }
                        });
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }

        rv.setAdapter(rvadapter);
    }

}
