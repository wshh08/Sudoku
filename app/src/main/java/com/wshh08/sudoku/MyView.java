package com.wshh08.sudoku;

import android.content.Context;
//import android.graphics.BitmapFactory;
import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Color;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
//import android.view.LayoutInflater;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wshh08 on 15-10-18.
 */
public class MyView extends View {
    public int step = 0;
    public Game game = new Game();
    public float width;
    public float height;
    public int selectX;
    public int selectY;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(R.layout.layout_myview, this);
    }

//    重写onMeasure方法以便绘制**正方形View**。
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, widthMode);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(widthMeasureSpec>=heightMeasureSpec){
            super.onMeasure(heightMeasureSpec-100, heightMeasureSpec-100);
        }
        else super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

//    重写View初始化时调用的onDraw方法
//    getHeight()&&getWidth()方法需在super.onDraw(canvas)后调用，否则返回值为０
//    private float height = (float)getHeight()/9f;
//    private float width = getWidth()/9;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        this.width = w/9f;
        this.height = h/9f;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint darkPaint = new Paint();
        darkPaint.setColor(getResources().getColor(R.color.sudoku_dark));

        Paint hilitePaint = new Paint();
        hilitePaint.setColor(getResources().getColor(R.color.sudoku_hilite));

        Paint lightPaint = new Paint();
        lightPaint.setColor(getResources().getColor(R.color.sudoku_light));
//        lightPaint.setColor(Color.RED);

//        float height = getHeight()/9;
//        float width = getWidth()/9;
//        绘制九宫格
        for(int i=0; i<=9; i++)
        {
//            绘制横线、竖线
//            canvas.drawLine(0, i*120, getWidth(), i*120, lightPaint);
            canvas.drawLine(0, i*height, getWidth(), i*height, lightPaint);
            canvas.drawLine(i*width, 0, i*width, getHeight(), lightPaint);
            Log.e("Myview.width", ((Float)(float)(getWidth()/9)).toString());
            Log.e("Myview.height",((Float)height).toString());
        }
//        绘制四根粗线
        for(int i=0; i<=9; i++)
        {
            if(i % 3 !=0)
            {
                continue;
            }

            canvas.drawLine(0, i*height, getWidth(), i*height, darkPaint);
            canvas.drawLine(0, i*height+1, getWidth(), i*height+1, hilitePaint);

            canvas.drawLine(i*width, 0, i*width, getHeight(), darkPaint);
            canvas.drawLine(i*width+1, 0, i*width+1, getHeight(), hilitePaint);
        }

//        在格子中绘制数字
        Paint numberPaint = new Paint();
        numberPaint.setColor(Color.BLACK);
        numberPaint.setStyle(Paint.Style.STROKE);
        numberPaint.setTextSize(height * 0.75f);
        numberPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = numberPaint.getFontMetrics();
        float x = width/2;
        float y = height/2 - (fontMetrics.ascent+fontMetrics.descent)/2;
//        canvas.drawText("9", 4*width+x, y, numberPainter);
        for(int i=0; i<9; i++)
            for(int j=0; j<9; j++)
                canvas.drawText(game.getTileString(i,j), i*width+x, j*height+y, numberPaint);

//        Paint paint = new Paint();
//        paint.setColor(getResources().getColor(R.color.colorAccent));
//        paint.setARGB(155, 100, 200, 0);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setStrokeWidth(5);
//        paint.setTextSize(100);
//        canvas.drawText("Apple", 50, 100, paint);
//        canvas.drawLine(50, 100, 300, 100, paint);
//        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 100, 200, paint);
//        canvas.drawRect(100, 300, 200, 500, paint);
//        canvas.drawCircle(300, 300, 100, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() != MotionEvent.ACTION_DOWN)
        {
            return super.onTouchEvent(event);
        }
        selectX = (int)(event.getX()/width);//获取触摸点在格子中的x坐标
        selectY = (int)(event.getY()/height);

        int[] used = game.getUsedTile(selectX, selectY); //获得已经使用了的数据，这样就可以知道哪些数字不能用了
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<used.length; i++ )
            sb.append(used[i]);
        Log.e("TAG", sb.toString()); //调试输出不可用数据。
        KeyDialog keyDialog = new KeyDialog(this.getContext(), used, this);
        keyDialog.show();
        return true;
    }

//    View类接受KeyDialog传递过来的数据，调用业务逻辑Game类进行处理
    public void setSelectTile(int tile)
    {
        step += 1;
//        String str = "Steps:" + String.valueOf(step);
//        stepText.setText(str);
        Log.e("TAG", game.setTileIfValid(selectX, selectY, tile) + "" + tile);
        if(game.setTileIfValid(selectX, selectY, tile))
        {
            invalidate(); //刷新view
        }
    }
}

















