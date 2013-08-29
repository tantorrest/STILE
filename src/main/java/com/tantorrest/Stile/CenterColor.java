package com.tantorrest.Stile;

import java.util.ArrayList;

/**
 * Created by tony on 8/27/13.
 */
public class CenterColor extends ColorChoice {
    int lines;
    ArrayList<Integer> cols;
    public CenterColor(int color){
        super(color);
        cols = new ArrayList<Integer>();
    }

    public void addLine(int id) {
        cols.add(id);
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public ArrayList<Integer> getCols() {
        return cols;
    }

    public void setCols(ArrayList<Integer> cols) {
        this.cols = cols;
    }
}
