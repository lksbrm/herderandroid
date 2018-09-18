package tv.lksbrm.herdervertretungsplan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Looper;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class InitActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_init);

        //Wenn Nutzer nicht angemeldet ist:
        if(!(getSharedPreferences("login", MODE_PRIVATE).getBoolean("loggedin", false)))
        {
            Intent i = new Intent(InitActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }else
        {

            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    Intent i = new Intent(InitActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }).start();
            finish();
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

}
