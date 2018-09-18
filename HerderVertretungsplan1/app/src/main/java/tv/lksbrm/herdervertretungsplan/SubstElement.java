package tv.lksbrm.herdervertretungsplan;

import java.util.Date;

/**
 * Created by lksbrm
 */
public class SubstElement
{
    private int ID;
    private String CLASS;
    private String LESSON;
    private String TEACHER;
    private String TEACHER_SUBST;
    private String ROOM;
    private String SUBJECT;
    private String TEXT;
    private String TYPE;
    private String DATE;

    public SubstElement(int id, String sclass, String lesson, String teacher, String teacher_subst, String room, String subject, String text, String type, String date)
    {
        ID = id;
        CLASS = sclass;
        LESSON = lesson;
        TEACHER = teacher;
        TEACHER_SUBST = teacher_subst;
        ROOM = room;
        SUBJECT = subject;
        TEXT = text;
        TYPE = type;
        DATE = date;
    }

    public SubstElement()
    {

    }



    public int getID()
    {
        return ID;
    }

    public String getAffectedClass()
    {
        return CLASS;
    }

    public String getLesson()
    {
        return LESSON;
    }

    public String getTeacher()
    {
        return TEACHER;
    }

    public String getTeacherSubst()
    {
        return TEACHER_SUBST;
    }

    public String getRoom()
    {
        return ROOM;
    }

    public String getSubject()
    {
        return SUBJECT;
    }

    public String getText()
    {
        return TEXT;
    }

    public String getType()
    {
        return TYPE;
    }

    public String getDate()
    {
        return DATE;
    }

    public void setID(int id) { ID = id; }

    public void setLesson(String text)
    {
        LESSON = text;
    }

    public void setTeacher(String text)
    {
        TEACHER = text;
    }

    public void setTeacherSubst(String text)
    {
        TEACHER_SUBST = text;
    }

    public void setRoom(String text)
    {
        ROOM = text;
    }

    public void setSubject(String text)
    {
        SUBJECT = text;
    }

    public void setText(String text)
    {
        TEXT = text;
    }

    public void setType(String text)
    {
        TYPE = text;
    }

    public void setClass(String text)
    {
        CLASS = text;
    }

    public void setDate(String text)
    {
        DATE = text;
    }

}