package tv.lksbrm.herdervertretungsplan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by Lukas on 04.10.2017.
 */
public class FragmentSettings extends Fragment
{
    View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        contentView = inflater.inflate(R.layout.fragment_settings, null);

        ToggleButton s = (ToggleButton) contentView.findViewById(R.id.switch1);

        s.setTextOn("Aktiviert");

        SharedPreferences sh = getActivity().getSharedPreferences("login", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        EditText et = (EditText) contentView.findViewById(R.id.editText3);
        et.setText(getActivity().getSharedPreferences("login", Context.MODE_PRIVATE).getString("class", ""));

        if(sh.getBoolean("ads", false))
        {
            s.setChecked(true);
            TextView tv = (TextView) contentView.findViewById(R.id.textView8);
            tv.setText("Danke, dass du uns unterstützt, indem du unsere dezente Werbung aktivierst!");
        }else
        {
            s.setChecked(false);
        }


        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                SharedPreferences sp = getActivity().getSharedPreferences("login", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();

                edit.putBoolean("ads", isChecked);
                MainActivity.advertisement = isChecked;
                edit.commit();

                if(isChecked)
                {
                    TextView tv = (TextView) contentView.findViewById(R.id.textView8);
                    tv.setText("Danke, dass du uns unterstützt, indem du unsere dezente Werbung aktivierst!");
                }else
                {
                    TextView tv = (TextView) contentView.findViewById(R.id.textView8);
                    tv.setText("Indem du unsere nicht-nervige Werbung aktivierst, unterstützt du das Herder Admin-Team ohne etwas dafür zu tun!");
                }


            }
        });

        Button b = (Button) contentView.findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences sp = getActivity().getSharedPreferences("login", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();

                edit.putBoolean("loggedin", false);
                edit.putString("username", "");
                edit.putString("password", "");
                edit.putString("class", "");
                edit.commit();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();

            }
        });

        Button b2 = (Button) contentView.findViewById(R.id.button3);
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText et1 = (EditText) contentView.findViewById(R.id.editText3);

                SharedPreferences sp = getActivity().getSharedPreferences("login", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();

                ed.putString("class", et1.getText().toString());
                ed.commit();
                Toast.makeText(getActivity().getApplicationContext(), "Erfolgreich gespeichert!", Toast.LENGTH_SHORT).show();
            }
        });

        return contentView;
    }
}
