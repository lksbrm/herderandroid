package tv.lksbrm.herdervertretungsplan;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by lksbrm
 */
public class XMLManager
{


    MainActivity ma;
    public XMLManager(MainActivity ma)
    {
        this.ma = ma;
    }

    private String text;
    private String text2;
    private User u;
    private SubstElement s;


    public ArrayList<SubstElement> getSubstFromFile()
            throws XmlPullParserException, IOException
    {

        ArrayList<SubstElement> substs = new ArrayList<SubstElement>();


        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();


        xpp.setInput(ma.getAssets().open("subst.xml"), "UTF-8");

        int eventType = xpp.getEventType();

        String sclass = "";
        String lesson = "";
        String teacher = "";
        String room = "";
        String subject = "";
        String textsubst = "";
        String typesubst = "";

        int tagtype = -1;



        //XMLPullParser parst die Vertr. Plan Datei und Gibt alle Reihen als Objekt in einer Liste zurück



        while(eventType != XmlPullParser.END_DOCUMENT)
        {

            String tagname = xpp.getName();
            switch(eventType)
            {
                case XmlPullParser.START_TAG:

                    tagname = xpp.getName();


                    if(tagname.equalsIgnoreCase("table"))
                    {

                        s = new SubstElement();

                    }

                    if(tagname.equalsIgnoreCase("column"))
                    {
                        //tagtype = -1;

                        if(xpp.getAttributeValue(null, "name").equalsIgnoreCase("klassen"))
                        {
                            tagtype = 0;
                        }
                        if(xpp.getAttributeValue(null, "name").equalsIgnoreCase("stunde"))
                        {
                            tagtype = 1;
                        }
                        if(xpp.getAttributeValue(null, "name").equalsIgnoreCase("vertreter"))
                        {
                            tagtype = 2;
                        }
                        if(xpp.getAttributeValue(null, "name").equalsIgnoreCase("raum"))
                        {
                            tagtype = 3;
                        }
                        if(xpp.getAttributeValue(null, "name").equalsIgnoreCase("kurs"))
                        {
                            tagtype = 4;
                        }
                        if(xpp.getAttributeValue(null, "name").equalsIgnoreCase("text"))
                        {
                            tagtype = 5;
                        }
                        if(xpp.getAttributeValue(null, "name").equalsIgnoreCase("art"))
                        {
                            tagtype = 6;
                        }
                    }

                    break;

                case XmlPullParser.TEXT:

                    text2 = xpp.getText();

                    break;

                case XmlPullParser.END_TAG:


                    if(tagname.equalsIgnoreCase("table"))
                    {

                        substs.add(s);

                    }else if(tagname.equalsIgnoreCase("column"))
                    {
                        //text = xpp.getText();
                        switch(tagtype)
                        {
                            case 0:
                                s.setClass(text2);
                                tagtype = -1;
                                break;

                            case 1:
                                s.setLesson(text2);
                                tagtype = -1;
                                break;

                            case 2:
                                s.setTeacher(text2);
                                tagtype = -1;
                                break;

                            case 3:
                                s.setRoom(text2);
                                tagtype = -1;
                                break;

                            case 4:
                                s.setSubject(text2);
                                tagtype = -1;
                                break;

                            case 5:
                                s.setText(text2);
                                tagtype = -1;
                                break;

                            case 6:
                                s.setType(text2);
                                tagtype = -1;
                                break;
                        }
                    }

                    break;
            }
            eventType = xpp.next();
        }

        return substs;
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

}
