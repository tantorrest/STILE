package com.tantorrest.Stile;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;

import java.util.ArrayList;

public class CreateActivity extends Activity implements ChoiceCallbacks {

    private ArrayList<ColorChoice> colors;
    private ArrayList<BorderColor> borderColors;
    private ArrayList<CenterColor> centerColors;
    private int lines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        colors = new ArrayList<ColorChoice>();
        borderColors = new ArrayList<BorderColor>();
        centerColors = new ArrayList<CenterColor>();

        ColorChoiceFragment colorChoiceFragment = new ColorChoiceFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.choice_containter, colorChoiceFragment).addToBackStack(null).commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create, menu);
        return true;
    }

    @Override
    public void onChoiceSelected(ColorChoice color) {
        colors.add(color);
        BorderColor bc = new BorderColor(color.getColor());
        CenterColor cc = new CenterColor(color.getColor());
        borderColors.add(bc);
        centerColors.add(cc);

    }

    @Override
    public void setLines(int lines) {
        this.lines = lines;
    }

    @Override
    public void create() {
        SetLineFragment setLineFragment = new SetLineFragment();
        setLineFragment.setColors(colors);
        setLineFragment.setBorders(borderColors);
        setLineFragment.setCenters(centerColors);
        setLineFragment.setLines(lines);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.choice_containter, setLineFragment).addToBackStack(null).commit();


    }

    @Override
    public void generateBoard(ArrayList<BorderColor> borders, ArrayList<CenterColor> centers) {
        setBorderColors(borders);
        setCenterColors(centers);
        BoardFragment frag = new BoardFragment();
        frag.setCenters(centers);
        frag.setBorders(borders);
        frag.setColors(colors);
        frag.setLines(lines);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.choice_containter, frag).addToBackStack(null).commit();

    }

    public void setBorderColors(ArrayList<BorderColor> borderColors) {
        this.borderColors = borderColors;
    }

    public void setCenterColors(ArrayList<CenterColor> centerColors) {
        this.centerColors = centerColors;
    }
}
