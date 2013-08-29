package com.tantorrest.Stile;

import java.util.ArrayList;

/**
 * Created by tony on 8/19/13.
 */
public interface ChoiceCallbacks {
    public void onChoiceSelected(ColorChoice color);
    public void setLines(int lines);


    public void create();

    void generateBoard(ArrayList<BorderColor> borders, ArrayList<CenterColor> centers);
}
