package com.wshh08.sudoku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
//import android.widget.TextView;

/**
 * Created by wshh08 on 15-10-18.
 */
public class MainActivity extends AppCompatActivity{
    TextView text_step;
    MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        text_step = (TextView)findViewById(R.id.text_step);
        myView = (MyView)findViewById(R.id.my_view);
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.e("MyView.step", String.valueOf(myView.step));
        text_step.setText(String.valueOf(myView.step));
    }
}
