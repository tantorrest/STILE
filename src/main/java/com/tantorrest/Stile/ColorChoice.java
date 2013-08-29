package com.tantorrest.Stile;

import android.graphics.Color;

/**
 * Created by tony on 8/19/13.
 */
public class ColorChoice {
    protected int color;


    public ColorChoice(int color){
        this.color=color;
    }

    @Override
    public String toString() {
        String name = "ERROR";
        switch (color){
            case Color.RED : name = "RED";
                break;
            case Color.GREEN : name = "GREEN";
                break;
            case Color.BLUE : name = "BLUE";
                break;
            case Color.CYAN : name = "CYAN";
                break;
            case Color.YELLOW : name = "YELLOW";
                break;
        }
        return name;
    }

    public int getColor() {
        return color;
    }

}
