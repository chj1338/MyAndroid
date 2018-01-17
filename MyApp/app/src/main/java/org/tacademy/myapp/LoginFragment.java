package org.tacademy.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {

    public static interface OnFragmentListener {
        public void onFragmentChanged(int index, String name);
    }

    OnFragmentListener listener;

    public LoginFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        listener = (OnFragmentListener)activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_login, container, false);

        Button button = (Button)rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                listener.onFragmentChanged(1, "메뉴화면");
            }
        });

        Button button2 = (Button)rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                Animation flow = AnimationUtils.loadAnimation(getActivity(), R.anim.flow);
                //v.startAnimation(flow);

                View v2 = rootView.findViewById(R.id.rootLayout);
                v2.startAnimation(flow);
            }
        });

        return rootView;
    }
}
