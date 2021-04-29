package com.example.myapplication;



import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;


public class TouchListener implements View.OnTouchListener {
    PicActivity picActivity;
    GestureDetectorCompat gestureDetectorCompat;

    int pointerCount;
    int[] ids;
    int x;
    int y;
    public TouchListener(PicActivity ma) {
        this.picActivity = ma;
        gestureDetectorCompat = new GestureDetectorCompat(this.picActivity, new MyGestureListener());
        pointerCount = 0;
        x = 0;
        y = 0;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureDetectorCompat.onTouchEvent(motionEvent);
        int maskedAction = motionEvent.getActionMasked();
        switch(maskedAction){
            case MotionEvent.ACTION_BUTTON_PRESS:

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    int id = motionEvent.getPointerId(i);
                    picActivity.addPath(id, motionEvent.getX(i), motionEvent.getY(i));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    int id = motionEvent.getPointerId(i);
                    picActivity.updatePath(id, motionEvent.getX(i), motionEvent.getY(i));
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                pointerCount = motionEvent.getPointerCount();
                ids = new int[pointerCount];

                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    int id = motionEvent.getPointerId(i);
                    ids[i] = id;

                    picActivity.removePath(id);
                }

                break;
        }
        return true;
    }

    public int getCounter()
    {
        return pointerCount;
    }

    public int[] getArray()
    {
        return ids;
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            //Log.v("Checker", String.valueOf(e.getX()));
            float x = e.getX();
            float y = e.getY();
            picActivity.onDoubleTap(x, y);
            return super.onDoubleTap(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            float x = e.getX();
            float y = e.getY();
            picActivity.onLongPress(x, y);
            super.onLongPress(e);
        }

    }
}
