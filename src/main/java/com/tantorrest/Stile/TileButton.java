package com.tantorrest.Stile;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by tony on 8/27/13.
 */
public class TileButton extends Button {
    private CenterColor centerColor;
    private BorderColor borderColor;
    private ArrayList<CenterColor> centerColors;
    private ArrayList<BorderColor> borderColors;
    private int rowId, colId;

    public TileButton(Context context) {
        super(context);
    }

    public CenterColor getCenterColor() {
        return centerColor;
    }

    public void setCenterColor(CenterColor centerColor) {
        this.centerColor = centerColor;
    }

    public BorderColor getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(BorderColor borderColor) {
        this.borderColor = borderColor;
    }

    public ArrayList<CenterColor> getCenterColors() {
        return centerColors;
    }

    public void setCenterColors(ArrayList<CenterColor> centerColors) {
        this.centerColors = centerColors;
    }

    public ArrayList<BorderColor> getBorderColors() {
        return borderColors;
    }

    public void setBorderColors(ArrayList<BorderColor> borderColors) {
        this.borderColors = borderColors;
    }


    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public int getColId() {
        return colId;
    }

    public void setColId(int colId) {
        this.colId = colId;
    }


    public void changeAppearance(BorderColor border, CenterColor center) {
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[] {Color.WHITE, center.getColor()});
        gd.setStroke(10, border.getColor());
        this.setBackground(gd);
    }

    public void setAppearance(BorderColor border, CenterColor center) {
        setBorderColor(border);
        setCenterColor(center);
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[] {Color.WHITE, center.getColor()});
        gd.setStroke(10, border.getColor());
        this.setBackground(gd);
    }

    public void setAppearance(){
        changeAppearance(getBorderColor(), getCenterColor());
    }



}
