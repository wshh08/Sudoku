package com.wshh08.sudoku;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by wshh08 on 15-10-18.
 */
public class KeyDialog extends Dialog{
    private int[] used;
    private View[] keys = new View[9];
    private MyView myView = (MyView)findViewById(R.id.my_view);
    public KeyDialog(Context context, int[] used, MyView myView)
    {
        super(context);
        this.used = used;
        this.myView = myView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("KeyDialog");
        setContentView(R.layout.layout_keypad);
        findAllViews();
        for(int i=0; i<this.used.length; i++)
        {
            if(used[i] != 0)
            {
                keys[used[i]-1].setVisibility(View.INVISIBLE);
            }
        }
        setListener();
    }

//    初始化９个按钮
    public void findAllViews()
    {
        keys[0] = findViewById(R.id.keypad_1);
        keys[1] = findViewById(R.id.keypad_2);
        keys[2] = findViewById(R.id.keypad_3);
        keys[3] = findViewById(R.id.keypad_4);
        keys[4] = findViewById(R.id.keypad_5);
        keys[5] = findViewById(R.id.keypad_6);
        keys[6] = findViewById(R.id.keypad_7);
        keys[7] = findViewById(R.id.keypad_8);
        keys[8] = findViewById(R.id.keypad_9);
    }

//    为按钮设置监听器
    public void setListener()
    {
        for(int i=0; i<keys.length; i++)
        {
            final int t = i + 1;
            keys[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returnResult(t);
                }
            });
        }
    }

    //对话框将选择的数据传递给View对象，让其处理业务逻辑
    public void returnResult(int tile)
    {
        myView.setSelectTile(tile);
        dismiss(); //取消对话框
    }
}













