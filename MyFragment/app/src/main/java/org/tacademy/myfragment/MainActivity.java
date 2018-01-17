package org.tacademy.myfragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Fragment1.OnItemClickListener {

    LinearLayout container;
    LinearLayout container2;

    Fragment1 fragment1;
    Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout)findViewById(R.id.container);
        container2 = (LinearLayout)findViewById(R.id.container2);

        //fragment1 = new Fragment1();
        fragment1 = Fragment1.newInstance(0, "백합", R.drawable.lily_flower_plant);
        fragment2 = new Fragment2();
    }

    public void onButton1Clicked(View v) {
        //SubLayout view = new SubLayout(this);
        //container.addView(view);

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container2, fragment2).commit();
    }

    @Override
    public void onItemClick(int index, String data) {
        if(index == 0) {
            Toast.makeText(this, "첫번째 프래그먼트로 부터 전달받은 data : " + index + ", " + data, Toast.LENGTH_LONG).show();
            fragment1.setNameText("");
            fragment2.setNameText(data);
        } else if(index == 1) {
            Toast.makeText(this, "두번째 프래그먼트로 부터 전달받은 data : " + index + ", " + data, Toast.LENGTH_LONG).show();
            fragment1.setNameText(data);
            fragment2.setNameText("");
        }



    }
}
