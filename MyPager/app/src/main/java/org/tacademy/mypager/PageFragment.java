package org.tacademy.mypager;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by T on 2015-07-16.
 */
public class PageFragment extends Fragment {

    int index;
    String name;
    int resId;

    public static PageFragment newInstance(int index, String name, int resId) {
        PageFragment frag = new PageFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        args.putString("name", name);
        args.putInt("resId", resId);

        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        index = args.getInt("index");
        name  = args.getString("name");
        resId = args.getInt("resId");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_page, container, false);

        TextView textView2 = (TextView)rootView.findViewById(R.id.textView2);
        ImageView imageView = (ImageView)rootView.findViewById(R.id.imageView);

        textView2.setText(name);
        imageView.setImageResource(resId);

        return rootView;
    }
}
