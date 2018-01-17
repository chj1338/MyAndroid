package org.tacademy.myfragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by T on 2015-07-16.
 */
public class Fragment1 extends Fragment {

    public static interface OnItemClickListener {
        public void onItemClick(int index, String data);
    }

    OnItemClickListener listener;
    Context context;

    EditText editText;
    TextView textView2;
    ImageView imageView;

    int index;
    String name;
    int resId;


    /*
      * 굳이 instance를 생성해서 만드는 이유는 Framgment 관련 코드를
      * MainActivity에 두지 안고 Fragment 내에 독립시키기 위해서이다.
      * 그래야 다른 소스쪽에 영향을 최소화 할 수 있다.
     */
    public static Fragment1 newInstance(int index, String name, int resId){
        Fragment1 frag =new Fragment1();

        Bundle args = new Bundle();
        args.putInt("index", index);
        args.putString("name", name);
        args.putInt("resId", resId);

        frag.setArguments(args);

        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(context, "onCreateView 호출됨", Toast.LENGTH_LONG).show();

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.sub_layout, container, false);

        editText = (EditText)rootView.findViewById(R.id.editText);
        textView2 = (TextView)rootView.findViewById(R.id.textView2);
        imageView = (ImageView)rootView.findViewById(R.id.imageView);

        editText.setText(name);
        imageView.setImageResource(resId);

        Button button2 = (Button)rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //MainActivity activity = (MainActivity) getActivity();
               //activity.onItemClick(0, "브라운아이드걸스");

               listener.onItemClick(0, "브라운아이드걸스");
           }
        });

        return rootView;
    }

    public void setNameText(String name) {
        textView2.setText(name);
    }


    /*
     * Fragment 함수 실행순서는
     * OnAttach - OnCreate - OnCreateView
     * instance 초기화는 OnAttach에 진행하고
     * 변수 정의 및 초기화는 OnCreate 에서 진행한다.
     * 화면에 값을 바인딩 하는 과정은 OnCreateView에서 진행한다.
     *
     */



    @Override
    public void onAttach(Activity activity) {
        listener = (OnItemClickListener) activity;
        context = (Context) activity;

        // Toast.makeText(getActivity(), "onAttach 호출됨", Toast.LENGTH_LONG).show();
        // 그나마 onAttach에는 activity가 인자로 넘어오나 아래쪽 다른 함수에는 아얘 없어서 getActivity()를 써야한다.
        // 그래서 context를 전역변수로 선언해놓고 사용한다.
        Toast.makeText(context, "onAttach 호출됨", Toast.LENGTH_LONG).show();

        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Toast.makeText(context, "onCreate 호출됨", Toast.LENGTH_LONG).show();

        Bundle bundle = getArguments();
        index = bundle.getInt("index");
        name = bundle.getString("name");
        resId = bundle.getInt("resId");

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(context, "onDestroy 호출됨", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Toast.makeText(context, "onDestroyView 호출됨", Toast.LENGTH_LONG).show();
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Toast.makeText(context, "onDetach 호출됨", Toast.LENGTH_LONG).show();
        super.onDetach();
    }

    @Override
    public void onPause() {
        Toast.makeText(context, "onPause 호출됨", Toast.LENGTH_LONG).show();
        super.onPause();
    }

    @Override
    public void onResume() {
        Toast.makeText(context, "onResume 호출됨", Toast.LENGTH_LONG).show();
        super.onResume();
    }

    @Override
    public void onStart() {
        Toast.makeText(getActivity(), "onStart 호출됨", Toast.LENGTH_LONG).show();
        super.onStart();
    }

    @Override
    public void onStop() {
        Toast.makeText(context, "onStop 호출됨", Toast.LENGTH_LONG).show();
        super.onStop();
    }

}
