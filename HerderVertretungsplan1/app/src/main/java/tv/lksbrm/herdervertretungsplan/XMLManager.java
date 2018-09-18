package tv.lksbrm.herdervertretungsplan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Looper;
import android.os.StrictMode;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lksbrm
 */
public class XMLManager
{


    Activity ma;
    public XMLManager(Activity ma)
    {
        this.ma = ma;
    }

    private String text;
    private String text2;
    private User u;
    private SubstElement s = new SubstElement();

    ArrayList<SubstElement> substs = new ArrayList<>();
    ArrayList<SubstElement> finalsubst = new ArrayList<>();

    String input = "-------------";


    public void downloadData()
    {

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Looper.prepare();
                    URL url = new URL("https://herder-koeln.de/wp-content/plugins/hg-vertr/android/db_to_xml.php?auth_code=420881337166686B");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    String subst = getFileContent(conn.getInputStream()).replace("</xml>", "");

                    while(!subst.contains("</element>"))
                    {

                    }

                    saveInFile(subst);
                    System.out.println("\n Plan heruntergeladen! \n");

                    ma.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            new ProgressDialog(ma.getApplicationContext()).dismiss();
                        }
                    });
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setSubst(final String DATEC)
            throws Exception
    {

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {

                try
                {
                    substs.clear();

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    XmlPullParser xpp = factory.newPullParser();

                    xpp.setInput(loadFromFile(), "UTF-8");

                    int eventType = xpp.getEventType();

                    int tagtype = -1;
                    String date = "";
                    String date_tomorrow = "";
                    String date_subst = "";


                    //XMLPullParser parst die Vertr. Plan Datei und Gibt alle Reihen als Objekt in einer Liste zurück



                    while(eventType != XmlPullParser.END_DOCUMENT)
                    {

                        String tagname = xpp.getName();
                        switch(eventType)
                        {
                            case XmlPullParser.START_TAG:

                                tagname = xpp.getName();

                                if(tagname.equalsIgnoreCase("meta_heute"))
                                {
                                    tagtype = 0;
                                }

                                if(tagname.equalsIgnoreCase("meta_morgen"))
                                {
                                    tagtype = 1;
                                }


                                if(tagname.equalsIgnoreCase("element"))
                                {

                                    s = new SubstElement();
                                }

                                break;

                            case XmlPullParser.TEXT:

                                text = xpp.getText();

                                break;

                            case XmlPullParser.END_TAG:

                                if(tagtype == 0)
                                {
                                    if(tagname.equalsIgnoreCase("datum"))
                                    {
                                        date = text;
                                        tagtype = -1;
                                    }
                                }

                                if(tagtype == 1)
                                {
                                    if(tagname.equalsIgnoreCase("datum"))
                                    {
                                        date_tomorrow = text;
                                        tagtype = -1;
                                        VarManager.DATE_TOMORROW = text;
                                    }
                                }

                                if(tagtype == -1)
                                {
                                   /**   if (tagname.equalsIgnoreCase("id")) {
                                        s.setID(Integer.parseInt(text));
                                    }*/

                                    if (tagname.equalsIgnoreCase("klasse")) {
                                        s.setClass(text);
                                    }

                                    if (tagname.equalsIgnoreCase("stunde")) {
                                        s.setLesson(text);
                                    }

                                    if (tagname.equalsIgnoreCase("lehrer")) {
                                        s.setTeacher(text);
                                    }

                                    if (tagname.equalsIgnoreCase("vertreter")) {
                                        s.setTeacherSubst(text);
                                    }

                                    if (tagname.equalsIgnoreCase("raum")) {
                                        s.setRoom(text);
                                    }

                                    if (tagname.equalsIgnoreCase("fach")) {
                                        s.setSubject(text);
                                    }

                                    if (tagname.equalsIgnoreCase("information")) {
                                        s.setText(text);
                                    }

                                    if (tagname.equalsIgnoreCase("art")) {
                                        s.setType(text);
                                    }

                                    if (tagname.equalsIgnoreCase("datum")) {
                                        s.setDate(text);
                                        date_subst = text;
                                    }

                                    if (tagname.equalsIgnoreCase("element")) {

                                        if(DATEC == "today")
                                        {
                                            if(date.equalsIgnoreCase(date_subst))
                                            {
                                                substs.add(s);
                                            }
                                        }

                                        if(DATEC == "tomorrow")
                                        {
                                            if(date_tomorrow.equalsIgnoreCase(date_subst))
                                            {
                                                substs.add(s);
                                            }
                                        }
                                    }
                                }

                                break;
                        }
                        eventType = xpp.next();
                    }

                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public ArrayList<LoginUser> getLoginUsers(ArrayList<User> u)
    {
        ArrayList<LoginUser> users = new ArrayList<LoginUser>();

        for(User a : u)
        {
            LoginUser lu = new LoginUser(a.getName(), a.getHash());
            users.add(lu);
        }

        return users;
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

    public ArrayList<SubstElement> getSubst()
    {
        return substs;
    }

    public void saveInFile(String s)
    {
        String fname = "Plan.txt";
        FileOutputStream outputStream;
        try
        {
            outputStream = ma.openFileOutput(fname, Context.MODE_PRIVATE);
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public InputStream loadFromFile()
    {
        File ordner;

        ordner = new File(ma.getCacheDir(), "Herder Vertretungsplan");
        System.out.println("---------" + ma.getApplicationContext().getFilesDir() + "-----------------");

        FileInputStream f = null;
        try
        {
            try
            {
                f = ma.openFileInput("Plan.txt");
            }catch(FileNotFoundException e)
            {
                downloadData();
                try {
                    Thread.sleep(VarManager.C_TIME);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                f = ma.openFileInput("Plan.txt");
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        try
        {
            return f;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
