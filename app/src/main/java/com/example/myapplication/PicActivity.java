package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class PicActivity extends AppCompatActivity {
    MyCanvas myCanvas;
    ImageView img;
    TouchListener touchListener;
    Random rd = new Random();
    Button red, green, blue, undo, clear, done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        img = findViewById(R.id.imageView);

        red = findViewById(R.id.red);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        undo = findViewById(R.id.undo);
        clear = findViewById(R.id.clear);
        done = findViewById(R.id.done);

        byte[] array = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(array, 0, array.length);
        myCanvas = findViewById(R.id.myCanvas);
        myCanvas.setBackground(new BitmapDrawable(getResources(), bmp));
        touchListener = new TouchListener(this);
        myCanvas.setOnTouchListener(touchListener);


        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.setColorRed();
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.setColorGreen();
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.setColorBlue();
            }
        });

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myCanvas.undo(0);
                for (int i = 0; i < touchListener.getCounter(); i++)
                {
                    //myCanvas.removePath(touchListener.getArray()[i]);

                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myCanvas.removePic();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addPath(int id, float x, float y) {
        myCanvas.addPath(id, x, y);
    }

    public void updatePath(int id, float x, float y) {
        myCanvas.updatePath(id, x, y);
    }

    public void removePath(int id) {
        myCanvas.removePath(id);
    }

    public void onDoubleTap(float x, float y) {
        myCanvas.addImage( x, y);

    }

    public void onLongPress(float x, float y) {
        myCanvas.addLongImage(x, y);
    }

    public void addMap()
    {

        myCanvas.addMap();
    }

}