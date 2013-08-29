package com.tantorrest.Stile;

import java.util.ArrayList;

/**
 * Created by tony on 8/27/13.
 */
public class BorderColor extends ColorChoice {
    int lines;
    ArrayList<Integer> rows;
    public BorderColor(int color){
        super(color);
        rows = new ArrayList<Integer>();
    }

    public void addLine(int id) {
        rows.add(id);
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public ArrayList<Integer> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Integer> rows) {
        this.rows = rows;
    }
}