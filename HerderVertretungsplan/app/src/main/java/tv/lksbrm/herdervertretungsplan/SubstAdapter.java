package tv.lksbrm.herdervertretungsplan;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.NativeExpressAdView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lksbrm
 */
public class SubstAdapter extends RecyclerView.Adapter<SubstAdapter.ViewHolderClass>
{



    Activity ma;
    VarManager vm;
    String day;
    ArrayList<SubstElement> userl = new ArrayList<SubstElement>();
    public SubstAdapter(Activity xml, ArrayList<SubstElement> se)
    {
        ma = xml;
        userl = se;


    }
    private static final int MENU_ITEM_VIEW_TYPE = 0;
    private static final int AD_VIEW_TYPE = 1;
    private static final int DATE_TYPE = 2;




    class ViewHolderClass extends RecyclerView.ViewHolder
    {

        TextView lesson;
        TextView type;
        TextView teacher;
        TextView room;
        TextView subject;
        TextView sclass;

        public ViewHolderClass(View itemView)
        {

            super(itemView);

            lesson = (TextView) itemView.findViewById(R.id.lesson);
            type = (TextView) itemView.findViewById(R.id.type);
            teacher = (TextView) itemView.findViewById(R.id.teacher);
            room = (TextView) itemView.findViewById(R.id.room);
            subject = (TextView) itemView.findViewById(R.id.subject);
            sclass = (TextView) itemView.findViewById(R.id.textView6);

        }
    }

    class AdViewHolderClass extends ViewHolderClass
    {
        AdViewHolderClass(View view)
        {
            super(view);
        }

    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType)
    {
        switch(viewType)
        {
            case MENU_ITEM_VIEW_TYPE:
                    View itemView1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

                    return new ViewHolderClass(itemView1);
            case AD_VIEW_TYPE:


                    View adView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_item_layout, parent, false);

                    return new ViewHolderClass(adView);

            case DATE_TYPE:

                    View dateView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

                    return new ViewHolderClass(dateView);





        }
        return null;



    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position)
    {

        int viewType = getItemViewType(position);
        ViewHolderClass holder1 = (ViewHolderClass) holder;
        switch(viewType)
        {
            case MENU_ITEM_VIEW_TYPE:

                try
                    {
                        if(!userl.get(position).getAffectedClass().equalsIgnoreCase(""))
                        {
                            try
                            {


                                holder1.sclass.setText(userl.get(position).getLesson());
                                if (userl.get(position).getType().contains("eigenverant")) {
                                    holder1.type.setText("Entfall");
                                } else {
                                    holder1.type.setText(userl.get(position).getType());
                                }

                                if (userl.get(position).getSubject().contains("NULL")) {
                                    holder1.subject.setText(userl.get(position).getText());
                                    holder1.subject.setTextSize(17);
                                } else {
                                    holder1.subject.setText(userl.get(position).getSubject());
                                }

                                holder1.room.setText(userl.get(position).getRoom());
                                if (userl.get(position).getTeacher().contains("+")) {
                                    holder1.teacher.setText("-");
                                    holder1.teacher.setTextColor(Color.DKGRAY);
                                } else {
                                    holder1.teacher.setText(userl.get(position).getTeacher());
                                }

                                holder1.lesson.setText(userl.get(position).getAffectedClass());
                                if(userl.get(position).getAffectedClass().equalsIgnoreCase(MainActivity.varManager.getPreferences("login").getString("class", "")))
                                {
                                    RelativeLayout rl = (RelativeLayout) holder1.itemView;
                                    rl.setBackgroundResource(R.drawable.gruppe3);
                                }else
                                {
                                    RelativeLayout rl = (RelativeLayout) holder1.itemView;
                                    rl.setBackgroundResource(R.drawable.grup);
                                }






                            }catch(Exception e)
                            {

                            }
                        }else
                        {

                        }
                    }catch(Exception e)
                    {

                    }

                break;

            case AD_VIEW_TYPE:

                    //AdViewHolderClass ad = (AdViewHolderClass) holder;
                    NativeExpressAdView adView = new NativeExpressAdView(ma.getApplicationContext(), null);

                    RelativeLayout rl = (RelativeLayout) holder1.itemView;
                    //rl.setBackgroundColor(Color.rgb(221, 221, 221));

                    adView.setAdSize(new AdSize(350, 80));

                    adView.setAdUnitId("ca-app-pub-6959636925815241/6017497413");
                    adView.loadAd(new AdRequest.Builder().build());


                    rl.removeAllViews();

                    rl.addView(adView);
                break;

            case DATE_TYPE:

                    break;



        }

    }

    @Override
    public int getItemCount()
    {
        try {
            return userl.size();
        }catch(Exception e)
        {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        if(MainActivity.advertisement)
        {
            return (position % 8 == 0) ? AD_VIEW_TYPE: MENU_ITEM_VIEW_TYPE;
        }else
        {
            return MENU_ITEM_VIEW_TYPE;
        }

    }




}
