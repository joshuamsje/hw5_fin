package com.example.myapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MyCanvas extends View {
    HashMap <Integer, Path> activePaths;

    ArrayList<Path> paths;
    ArrayList<Float> xHold;
    ArrayList<Float> yHold;

    ArrayList<Float> xLong;
    ArrayList<Float> yLong;
    List<HashMap<Integer, Path>> list;
    Paint pathPaint;
    int index;

    int picCheck;

    boolean check;

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activePaths = new HashMap<>();
        paths = new ArrayList<>();
        xHold = new ArrayList<Float>();
        yHold = new ArrayList<Float>();
        xLong = new ArrayList<Float>();
        yLong = new ArrayList<Float>();

        check = false;
        index = 0;
        picCheck = -1;

        list = new ArrayList<>();

        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setColor(Color.BLACK);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(70);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < xHold.size(); i++)
        {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star2);
            canvas.drawBitmap(bitmap, xHold.get(i), yHold.get(i), null);

        }

        for (int i = 0; i < xLong.size(); i++)
        {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hokie_pic);
            canvas.drawBitmap(bitmap, xLong.get(i), yLong.get(i), null);

        }

        for(Path path: activePaths.values()){
            canvas.drawPath(path, pathPaint);
        }

        for (int i = 0; i < paths.size(); i++)
        {
            canvas.drawPath(paths.get(i), pathPaint);
        }


        super.onDraw(canvas);
    }


    public void addImage(float x, float y)
    {
        xHold.add(x);
        yHold.add(y);
        invalidate();
    }

    public void addLongImage(float x, float y)
    {
        xLong.add(x);
        yLong.add(y);
        invalidate();
    }

    public void addPath(int id, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        activePaths.put(id, path);

        invalidate();
    }

    public void updatePath(int id, float x, float y) {
        Path path = activePaths.get(id);
        if(path != null){
            path.lineTo(x, y);
        }
        invalidate();
    }

    public void addMap()
    {

        //list.add(activePaths);
        list.add(index, activePaths);
        index++;
        for (int i = 0;  i < list.size(); i++)
        {
            for (Integer k : list.get(i).keySet())
            {
                System.out.println(k + ": " + list.get(i).get(k));
            }
        }
        invalidate();
    }

    public void removePath(int id) {
        if(activePaths.containsKey(id)){
            Path temp = activePaths.get(id);
            paths.add(temp);
            activePaths.remove(id);

        }
        invalidate();
    }

    public void undo(int i)
    {
        paths.remove(0);
        invalidate();
    }

    public void removePic() {

        paths.clear();
        xHold.clear();
        yHold.clear();
        xLong.clear();
        yLong.clear();

        invalidate();
    }

    public void setColorRed()
    {

        pathPaint.setColor(Color.RED);
    }

    public void setColorBlue()
    {

        pathPaint.setColor(Color.BLUE);
    }
    public void setColorGreen()
    {

        pathPaint.setColor(Color.GREEN);
    }

}
