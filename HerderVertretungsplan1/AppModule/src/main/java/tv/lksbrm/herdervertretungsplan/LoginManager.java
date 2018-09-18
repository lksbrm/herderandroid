package tv.lksbrm.herdervertretungsplan;

import android.content.Intent;

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

/**
 * Created by lksbrm
 */
public class LoginManager
{
    MainActivity ma;
    public LoginManager(MainActivity ma)
    {
        this.ma = ma;
    }
    public void loginUser()
    {
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    final String name = ma.getSharedPreferences("login", ma.MODE_PRIVATE).getString("username", "null");
                    final String hash = ma.getSharedPreferences("login", ma.MODE_PRIVATE).getString("password", "null");


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

                    String res = getFileContent(conn.getInputStream());

                    if(res.contains("0"))
                    {
                        ma.getSharedPreferences("login", ma.MODE_PRIVATE).edit().putBoolean("loggedin", false);
                        ma.getSharedPreferences("login", ma.MODE_PRIVATE).edit().commit();
                        Intent i = new Intent(ma, LoginActivity.class);
                        ma.startActivity(i);
                        ma.finish();
                    }


                }catch(Exception e)
                {

                }

            }
        });
        t.start();
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
