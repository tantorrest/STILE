package com.tantorrest.Stile;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

/**
 * Created by tony on 8/19/13.
 */
public class BoardFragment extends Fragment {

    private ChoiceCallbacks mCallbacks;
    private static Context ctx;
    private ArrayList<ColorChoice> colors;
    private int lines;
    private TableLayout grid;

    private ArrayList<BorderColor> borders;
    private ArrayList<CenterColor> centers;

    public BoardFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ctx = getActivity();
        grid = (TableLayout) view.findViewById(R.id.grid);
        int rlHeight = getRLHeight();
        int rlWidth = getRLWidth();
       
        int gridSize = 0;
        if (rlHeight>rlWidth) {
            gridSize = (int) rlWidth;
        }
        else {
            gridSize = (int) rlHeight;
        }

        Log.d("DIMEN", "Real Grid Size: " + gridSize);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(gridSize, gridSize);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        grid.setLayoutParams(lp);
        grid.setWeightSum(colors.size()*lines);
        for (int row = 1; row < colors.size()*lines + 1; row++)
        {
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setLayoutParams(new TableRow.LayoutParams(gridSize, gridSize/(colors.size()*lines)));
            tableRow.setWeightSum(colors.size()*lines);

            for (int column = 1; column < colors.size()*lines + 1; column++)
            {
                TileButton btn = getRandomTile();
                btn.setEnabled(true);
                btn.setRowId(row);
                btn.setColId(column);
//                btn.setText("R"+row+"C"+column);
//                btn.setVisibility(View.VISIBLE);
                btn.setLayoutParams(new TableRow.LayoutParams(gridSize/(colors.size()*lines), gridSize/(colors.size()*lines), 1));
//                setRandomAppearance(btn);
                tableRow.addView(btn);
            }
            grid.addView(tableRow, new TableLayout.LayoutParams(gridSize, gridSize/(colors.size()*lines)));
        }
        
    }

    public BorderColor randomBorder() {
        int i = (int) (borders.size()*Math.random());
        return borders.get(i);
    }
    public CenterColor randomCenter() {
        int i = (int) (centers.size()*Math.random());
        return centers.get(i);
    }

    private void setRandomAppearance(TileButton btn) {
        BorderColor border = randomBorder();
        CenterColor center = randomCenter();
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[] {center.getColor(), center.getColor()});
        gd.setStroke(5, border.getColor());
        btn.setBackground(gd);
    }

    private TileButton getRandomTile() {
        TileButton btn = new TileButton(getActivity());
        BorderColor border = randomBorder();
        CenterColor center = randomCenter();
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[] {Color.WHITE, center.getColor(), Color.WHITE,});
//        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
//        gd.setGradientRadius((float)1.2);
        gd.setStroke(15, border.getColor());
        btn.setBackground(gd);
        return btn;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("SWEET", "Activity: " + activity.getClass());

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof ChoiceCallbacks)) {
            throw new IllegalStateException(
                    "Activity must implement fragment's callbacks.");
        }

        mCallbacks = (ChoiceCallbacks) activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    public static int getRLHeight(){
        DisplayMetrics display = ctx.getResources().getDisplayMetrics();
        double height = display.heightPixels;
        Log.d("DIMEN", "Real display PX Height: " + height);
        int px = (int) (height);
        Log.d("DIMEN", "Int display PX Height: " + px);
        return px;
    }

    public static int getRLWidth() {
        DisplayMetrics display = ctx.getResources().getDisplayMetrics();
        double width = display.widthPixels;
        Log.d("DIMEN", "Real display PX Width: " + width);
        int px = (int) (width);
        Log.d("DIMEN", "Int display PX Width: " + px);
        return px;
    }
    public ArrayList<BorderColor> getBorders() {
        return borders;
    }

    public void setBorders(ArrayList<BorderColor> borders) {
        this.borders = borders;
    }

    public ArrayList<CenterColor> getCenters() {
        return centers;
    }

    public void setCenters(ArrayList<CenterColor> centers) {
        this.centers = centers;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public void setColors(ArrayList<ColorChoice> colors) {
        this.colors = colors;
    }
}
