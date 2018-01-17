package org.tacademy.myapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment2 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);

		Button button1 = (Button) rootView.findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NavigationDrawerFragment.NavigationDrawerCallbacks callback = (NavigationDrawerFragment.NavigationDrawerCallbacks) getActivity();
				callback.onNavigationDrawerItemSelected(2);
			}
			
		});
		
		
		return rootView;
	}

	
	
}
