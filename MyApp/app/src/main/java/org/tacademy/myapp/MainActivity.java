package org.tacademy.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentListener {

    private static final String TAG = "MainActivity";

    LoginFragment frag1;
    MenuFragment frag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag1 = new LoginFragment();
        frag2 = new MenuFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container, frag1).commit();
    }


    @Override
    public void onFragmentChanged(int index, String name) {
        Log.d(TAG, "onFragementChanged() 호출됨" + index + ", " + name);

        if(index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, frag1).commit();
        } else if(index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, frag2).commit();
        }
    }
}
