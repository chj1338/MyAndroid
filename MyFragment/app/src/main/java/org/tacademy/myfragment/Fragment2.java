package org.tacademy.myfragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by T on 2015-07-16.
 */
public class Fragment2 extends Fragment {

    TextView textView2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment2, container, false);

        textView2 = (TextView)rootView.findViewById(R.id.textView2);

        Button button2 = (Button)rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onItemClick(1, "아이유");
            }
        });

        return rootView;
    }

    public void setNameText(String name) {
        textView2.setText(name);
    }
}
