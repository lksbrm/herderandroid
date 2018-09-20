package tv.lksbrm.herdervertretungsplan;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Created by lksbrm

public class MainActivity extends AppCompatActivity {

    //Instanz von VarManager
    public static VarManager varManager;

    //Instanz von XMLManager wird init. in onCreate
    XMLManager xml;

    //Recycler View MainActivity
    public RecyclerView rv;
    RecyclerView.Adapter rvadapter;
    RecyclerView.LayoutManager rvlayoutmanager;

    //DrawerLayout
    DrawerLayout dl;
    ActionBarDrawerToggle abdt;

    Toolbar t;

    //Werbung f = keine t = werbung
    public static boolean advertisement = false;


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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        t = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(t);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        xml = new XMLManager(this);
        varManager = new VarManager(this);


        init();


        Toolbar t = (Toolbar) findViewById(R.id.toolbar);

        t.setTitle("Herder Vertretungsplan");

        dl = (DrawerLayout) findViewById(R.id.drawer);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.filler1, R.string.filler2);
        dl.setDrawerListener(abdt);

        MobileAds.initialize(this, "ca-app-pub-6959636925815241~9382737799");

        varManager = new VarManager(this);

        rv = (RecyclerView) findViewById(R.id.recycler);
        rvlayoutmanager = new LinearLayoutManager(this);
        rv.setLayoutManager(rvlayoutmanager);

        rvadapter = new SubstAdapter(this);
        rv.setAdapter(rvadapter);

        abdt.syncState();


        //Wenn Nutzer nicht angemeldet ist:
        if(!(varManager.getPreferences("login").getBoolean("loggedin", false)))
        {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }


        if(varManager.getPreferences("login").getString("username", "???") != null)
        {
            TextView tv = (TextView) findViewById(R.id.textView3);
            tv.setText("Benutzerkonto: " + varManager.getPreferences("login").getString("username", "???"));
        }



    }

    private void init()
    {
        LoginManager lm = new LoginManager(this);
        //Checkt, ob login noch aktuell
        lm.loginUser();

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        if(abdt.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState)
    {
        super.onPostCreate(savedInstanceState, persistentState);
        abdt.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {

        super.onConfigurationChanged(newConfig);
        abdt.onConfigurationChanged(new Configuration());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Per Notebook Geaddet



}