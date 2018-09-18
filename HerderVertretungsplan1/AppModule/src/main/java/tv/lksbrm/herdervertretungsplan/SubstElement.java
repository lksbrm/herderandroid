package tv.lksbrm.herdervertretungsplan;

/**
 * Created by lksbrm
 */
public class SubstElement
{
    private String CLASS;
    private String LESSON;
    private String TEACHER;
    private String ROOM;
    private String SUBJECT;
    private String TEXT;
    private String TYPE;

    public SubstElement(String sclass, String lesson, String teacher, String room, String subject, String text, String type)
    {
        CLASS = sclass;
        LESSON = lesson;
        TEACHER = teacher;
        ROOM = room;
        SUBJECT = subject;
        TEXT = text;
        TYPE = type;
    }

    public SubstElement()
    {

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

    public void setLesson(String text)
    {
        LESSON = text;
    }

    public void setTeacher(String text)
    {
        TEACHER = text;
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

}