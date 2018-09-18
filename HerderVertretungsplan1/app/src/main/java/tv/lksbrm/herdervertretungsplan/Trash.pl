 public ArrayList<User> getUsersFromFile()
            throws XmlPullParserException, IOException
    {

        ArrayList<User> users = new ArrayList<User>();


        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();


        xpp.setInput(ma.getAssets().open("users.xml"), "UTF-8");

        int eventType = xpp.getEventType();

        String name = "";
        int id = 0;
        String hash = "";
        String regsince = "";
        String displayname = "++++++++++++++++++++++";

        int tagtype = -1;



        while(eventType != XmlPullParser.END_DOCUMENT)
        {

            String tagname = xpp.getName();
            switch(eventType)
            {
                case XmlPullParser.START_TAG:

                    tagname = xpp.getName();


                    if(tagname.equalsIgnoreCase("table"))
                    {

                        u = new User();

                    }

                    if(tagname.equalsIgnoreCase("column"))
                    {
                        //tagtype = -1;

                        if(xpp.getAttributeValue(null, "name").equalsIgnoreCase("ID"))
                        {
                            tagtype = 0;
                        }
                        if(xpp.getAttributeValue(null, "name").equalsIgnoreCase("user_login"))
                        {
                            tagtype = 1;
                        }
                        if(xpp.getAttributeValue(null, "name").equalsIgnoreCase("user_pass"))
                        {
                            tagtype = 2;
                        }
                        if(xpp.getAttributeValue(null, "name").equalsIgnoreCase("user_registered"))
                        {
                            tagtype = 3;
                        }
                        if(xpp.getAttributeValue(null, "name").equalsIgnoreCase("display_name"))
                        {
                            tagtype = 4;
                        }
                    }

                    break;

                case XmlPullParser.TEXT:

                    text = xpp.getText();

                    break;

                case XmlPullParser.END_TAG:


                    if(tagname.equalsIgnoreCase("table"))
                    {

                        users.add(u);

                    }else if(tagname.equalsIgnoreCase("column"))
                    {
                        //text = xpp.getText();
                        switch(tagtype)
                        {
                            case 0:
                                u.setID(text);
                                break;

                            case 1:
                                u.setName(text);
                                break;

                            case 2:
                                u.setHash(text);
                                break;

                            case 3:
                                u.setDate(text);
                                break;

                            case 4:
                                u.setDisplayName(text);
                                break;
                        }
                    }

                    break;
            }
            eventType = xpp.next();
        }

        return users;
    }
--------------------------------------------------------------------------------------

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

--------------------------------------------------------------------

public void setSubstTable(final InputStream is, final String datec)
    {

                try {
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    XmlPullParser xpp = factory.newPullParser();

                    substs.clear();

                    xpp.setInput(new InputStreamReader(is));

                    int eventType = xpp.getEventType();

                    String sclass = "";
                    String lesson = "";
                    String teacher = "";
                    String room = "";
                    String subject = "";
                    String textsubst = "";
                    String typesubst = "";
                    String sdate = "";
                    String teacherorig = "";
                    String info = "";


                    int tagtype = 0;

                    boolean shouldparse = false;

                    boolean th = false;

                    boolean date = false;


                    //XMLPullParser parst die Vertr. Plan Datei und Gibt alle Reihen als Objekt in einer Liste zurück


                    while (eventType != XmlPullParser.END_DOCUMENT) {

                        String tagname = xpp.getName();
                        switch (eventType) {
                            case XmlPullParser.START_TAG:


                                tagname = xpp.getName();

                                if (tagname.equalsIgnoreCase("th")) {

                                }


                                if (shouldparse) {

                                    if (tagname.equalsIgnoreCase("tr")) {

                                        s = new SubstElement();
                                    }

                                    if (tagname.equalsIgnoreCase("td")) {
                                        tagtype++;
                                        if (tagtype == 10) {
                                            tagtype = 1;
                                        }
                                    }


                                } else if (xpp.getAttributeValue(null, "class") != null && xpp.getAttributeValue(null, "class").equalsIgnoreCase("mon_list")) {
                                    shouldparse = true;
                                }

                                if (tagname.equalsIgnoreCase("div")) {
                                    date = true;
                                }


                                break;

                            case XmlPullParser.TEXT:

                                text = xpp.getText();

                                break;

                            case XmlPullParser.END_TAG:


                                if (tagname.equalsIgnoreCase("table")) {
                                    shouldparse = false;
                                }


                                if (shouldparse) {


                                    if (tagname.equalsIgnoreCase("tr")) {
                                        s = new SubstElement();
                                        s.setClass(sclass);
                                        s.setLesson(lesson);
                                        if (teacher.equalsIgnoreCase("+")) {
                                            s.setTeacher(teacherorig);
                                        } else {
                                            s.setTeacher(teacher);
                                        }
                                        s.setRoom(room);
                                        if (subject.equalsIgnoreCase("-")) {
                                            s.setSubject(info);
                                        } else {
                                            s.setSubject(subject);
                                        }
                                        s.setText(textsubst);
                                        s.setType(typesubst);

                                        VarManager.DATE = sdate;
                                        //if(!lesson.equalsIgnoreCase(""))
                                        //{
                                        Date dt = new Date();
                                        SimpleDateFormat df = new SimpleDateFormat("d.M.yyyy");
                                        if (datec.equalsIgnoreCase("today")) {
                                            if (sdate.contains(df.format(dt))) {
                                                s.setDate("today");
                                                substs.add(s);
                                            }
                                        } else {
                                            if (!sdate.contains(df.format(dt))) {
                                                SimpleDateFormat df1 = new SimpleDateFormat("Md");
                                                String[] datea = sdate.replace(".", ":").split(":");
                                                String rebuild = datea[1] + datea[0];
                                                if (Integer.parseInt(rebuild) > Integer.parseInt(df1.format(dt))) {
                                                    s.setDate("tomorrow");
                                                    substs.add(s);
                                                }
                                            }
                                        }


                                        //}

                                    }


                                    if (tagname.equalsIgnoreCase("td")) {

                                        switch (tagtype) {
                                            case 1:
                                                sclass = text;
                                                break;

                                            case 2:
                                                typesubst = text;
                                                break;

                                            case 3:
                                                lesson = text;
                                                break;

                                            case 4:
                                                subject = text;
                                                break;

                                            case 5:
                                                teacherorig = text;
                                                break;

                                            case 6:
                                                teacher = text;
                                                break;

                                            case 7:
                                                room = text;
                                                break;

                                            case 8:
                                                break;

                                            case 9:
                                                info = text;
                                                break;
                                        }


                                    }


                                }
                                if (tagname.equalsIgnoreCase("div") && date) {
                                    sdate = text;
                                    date = false;
                                }

                                break;
                        }
                        eventType = xpp.next();
                    }
                    VarManager.LOADED = true;
                }catch(Exception e)
                {
                    System.out.println("___________________-------------------_______________");
                }



    }