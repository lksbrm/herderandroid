package tv.lksbrm.herdervertretungsplan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.RunnableFuture;

//Created by lksbrm


public class LoginActivity extends AppCompatActivity
{

    public Toolbar toolbar;
    public Button skip;

    public MainActivity ma;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Anmelden");

        skip = (Button) findViewById(R.id.button);

        skip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                boolean succ = false;
                String sclass = "";

                String res;

                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            EditText namefield = (EditText) findViewById(R.id.editText);
                            EditText hashfield = (EditText) findViewById(R.id.editText2);
                            EditText classfield = (EditText) findViewById(R.id.editText3);

                            final String name = namefield.getText().toString();
                            final String hash = hashfield.getText().toString();
                            final String sclass = classfield.getText().toString();


                            URL url = new URL("http://testapp.herder-koeln.de/login.inc.php");
                            Map<String, Object> params = new LinkedHashMap<String, Object>();
                            params.put("username", name);
                            params.put("password", hash);

                            StringBuilder postData = new StringBuilder();
                            for(Map.Entry<String, Object> param : params.entrySet())
                            {
                                if (postData.length() != 0) postData.append('&');
                                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                                postData.append('=');
                                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                            }

                            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                            conn.setDoOutput(true);
                            conn.getOutputStream().write(postDataBytes);

                            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                            final String res = getFileContent(conn.getInputStream());

                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    if(res.contains("0"))
                                    {
                                        userWrong();
                                    }else
                                    {
                                        SharedPreferences sp = LoginActivity.this.getSharedPreferences("login", MODE_PRIVATE);
                                        SharedPreferences.Editor edit = sp.edit();

                                        edit.putBoolean("loggedin", true);
                                        edit.putString("username", name);
                                        edit.putString("password", hash);
                                        edit.putString("class", sclass);
                                        edit.commit();
                                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(i);
                                        finish();


                                    }
                                }
                            });





                        }catch(Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                }).start();





            }
        });


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

    public void userWrong()
    {
        TextView tv = (TextView) findViewById(R.id.textView4);
        tv.setText("Benutzer oder Passwort falsch!");
    }

}
