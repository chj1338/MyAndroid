package org.tacademy.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class MenuFragment extends Fragment {

    LoginFragment.OnFragmentListener listener;

    public MenuFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        listener = (LoginFragment.OnFragmentListener)activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_manu, container, false);

        Button button = (Button)rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                listener.onFragmentChanged(0, "로그인화면");
            }
        });

        return rootView;
    }
}
