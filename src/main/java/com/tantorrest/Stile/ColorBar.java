package com.tantorrest.Stile;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by tony on 8/31/13.
 */
public class ColorBar extends LinearLayout {
    private Paint mPaint;
    private float mCurrentHue = 0;
    private int mCurrentX = 0, mCurrentY = 0;
    private int mCurrentColor, mDefaultColor;
    private final int[] mHueBarColors = new int[258];
    private int[] mMainColors = new int[65536];
    private OnColorChangedListener mListener;
    private ColorCallbacks colorCallback;
    private int height, width, margin, barHeight, barWidth, color;
    private Context c;
    // Initialize the colors of the hue slider bar
    ColorBar(Context c, ColorCallbacks colorCallback) {
        super(c);
        this.c = c;
        this.colorCallback = colorCallback;
        LayoutInflater mInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.color_bar, this, true);

//        DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();
//        height = displayMetrics.heightPixels;
//        width = displayMetrics.widthPixels;
//        margin = (int) (width*10.0/276);
//        barHeight = (int) (height*40/366.0);
//        barWidth = width - (2*margin);

        // Get the current hue from the current color and update the main
        // color field


        // Initialize the colors of the hue slider bar
        int index = 0;
        for (float i = 0; i < 256; i += 256 / 42) // Red (#f00) to pink
        // (#f0f)
        {
            mHueBarColors[index] = Color.rgb(255, 0, (int) i);
            index++;
        }
        for (float i = 0; i < 256; i += 256 / 42) // Pink (#f0f) to blue
        // (#00f)
        {
            mHueBarColors[index] = Color.rgb(255 - (int) i, 0, 255);
            index++;
        }
        for (float i = 0; i < 256; i += 256 / 42) // Blue (#00f) to light
        // blue (#0ff)
        {
            mHueBarColors[index] = Color.rgb(0, (int) i, 255);
            index++;
        }
        for (float i = 0; i < 256; i += 256 / 42) // Light blue (#0ff) to
        // green (#0f0)
        {
            mHueBarColors[index] = Color.rgb(0, 255, 255 - (int) i);
            index++;
        }
        for (float i = 0; i < 256; i += 256 / 42) // Green (#0f0) to yellow
        // (#ff0)
        {
            mHueBarColors[index] = Color.rgb((int) i, 255, 0);
            index++;
        }
        for (float i = 0; i < 256; i += 256 / 42) // Yellow (#ff0) to red
        // (#f00)
        {
            mHueBarColors[index] = Color.rgb(255, 255 - (int) i, 0);
            index++;
        }

        // Initializes the Paint that will draw the View
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextAlign(Paint.Align.CENTER);
}
    public interface OnColorChangedListener {
        void colorChanged(String key, int color);
    }

    public void addBars(int barWidth, int barHeight){

        LinearLayout.LayoutParams lp = new LayoutParams((int)(barWidth/256), barHeight, 1);
        for (int x = 0; x < 256; x++) {
            RelativeLayout bar = new RelativeLayout(c);
            bar.setBackgroundColor(mHueBarColors[x]);
            bar.setId(mHueBarColors[x]);
            bar.setLayoutParams(lp);
            bar.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN){
                        color = v.getId();
                        colorCallback.onColorTouch(color);
                    }
                    return false;
                }
            });
            this.addView(bar);
    }

}

    public int getColor(){
        return color;
    }
}
