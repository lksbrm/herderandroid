package tv.lksbrm.herdervertretungsplan;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

//Created by lksbrm

public class MainActivity extends AppCompatActivity {


    //Instanz von VarManager
    public static VarManager varManager;

    //Werbung f = keine t = werbung
    public static boolean advertisement;

    RecyclerView rv1;
    RecyclerView rv2;

    NavigationView navigationView;

    //DrawerLayout
    DrawerLayout dl;
    ActionBarDrawerToggle abdt;

    XMLManager xml;

    Toolbar t;

    InitActivity ia;

    FragmentFrame fragmentFrame;
    FragmentSettings fragmentSettings;

    int i = 87;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        t = (Toolbar) findViewById(R.id.toolbar);
        t.setTitle("Herder Vertretungsplan");

        setSupportActionBar(t);

        dl = (DrawerLayout) findViewById(R.id.drawer);
        dl.setDrawerElevation(20);
        abdt = new DrawerToggle(MainActivity.this, dl, R.string.filler1, R.string.filler2);
        dl.setDrawerListener(abdt);


        new BackgroundJob().execute();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        abdt.syncState();

        TextView tv = (TextView) findViewById(R.id.textView3);
        tv.setText("Benutzerkonto: " + getSharedPreferences("login", MODE_PRIVATE).getString("username", "???"));

        dl.setBackgroundResource(R.drawable.lechteck5);

        tv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i--;
                if(i == 0)
                {
                    Intent in = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(in);
                }

            }
        });


    }

    public void init()
    {

        rv1 = (RecyclerView) findViewById(R.id.recycler1);
        rv2 = (RecyclerView) findViewById(R.id.recycler2);


        varManager = new VarManager(this);

    }

    public class BackgroundJob extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {

            fragmentFrame = new FragmentFrame();
            fragmentSettings = new FragmentSettings();
            init();
            fragmentFrame.setContext(MainActivity.this);
            final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, fragmentFrame);
            fragmentTransaction.commit();

            advertisement = varManager.getPreferences("login").getBoolean("ads", false);


            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {

                    navigationView = (NavigationView) findViewById(R.id.navview);
                    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
                    {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem)
                        {

                            switch(menuItem.getItemId())
                            {
                                case R.id.item2:
                                    FragmentTransaction trans2 = getSupportFragmentManager().beginTransaction();
                                    trans2.add(R.id.container, fragmentSettings);
                                    trans2.commit();
                                    break;

                                case R.id.item1:
                                    FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                                    trans.remove(fragmentSettings);
                                    trans.commit();
                                    break;


                            }
                            dl.closeDrawers();
                            menuItem.setChecked(true);


                            return false;
                        }
                    });
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            //progressDialog.cancel();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        if(abdt.onOptionsItemSelected(item))
        {
            return true;
        }else
        {
            return false;
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        abdt.syncState();
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        abdt.onConfigurationChanged(new Configuration());
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }
}