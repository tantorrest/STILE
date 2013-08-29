package com.tantorrest.Stile;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tony on 8/23/13.
 */
public class SetLineFragment extends Fragment {
    private ArrayList<ColorChoice> colors;
    private int lines;
    private TableLayout grid;
    private List<String> samples;
    private ArrayList<Button> rows, cols, borderColors, centerColors;
    private static Context ctx;
    private View.OnClickListener borderClick, centerClick, rowClick, colClick;
    private Button currentColorButton, nextButton;
    private int currentColor;
    private ArrayList<BorderColor> borders;
    private ArrayList<CenterColor> centers;
    private BorderColor currentBorder;
    private CenterColor currentCenter;
    private ChoiceCallbacks mCallbacks;

    public SetLineFragment() {
    }

    public SetLineFragment(ArrayList<ColorChoice> colors, int lines) {
        this.colors = colors;
        this.lines = lines;
        int numberOfTiles = colors.size()*lines*colors.size()*lines;
        samples = new ArrayList<String>(numberOfTiles);
        for (int i = 0; i<numberOfTiles; i++){
            String string = Integer.toString(i);
            samples.add(string);
        }
        rows = new ArrayList<Button>(colors.size()*lines);
        cols = new ArrayList<Button>(colors.size()*lines);
        borderColors = new ArrayList<Button>(colors.size());
        centerColors = new ArrayList<Button>(colors.size());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int numberOfTiles = colors.size()*lines*colors.size()*lines;
        samples = new ArrayList<String>(numberOfTiles);
        for (int i = 0; i<numberOfTiles; i++){
            String string = Integer.toString(i);
            samples.add(string);
        }
        rows = new ArrayList<Button>(colors.size()*lines);
        cols = new ArrayList<Button>(colors.size()*lines);
        borderColors = new ArrayList<Button>(colors.size());
        centerColors = new ArrayList<Button>(colors.size());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (ChoiceCallbacks) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grid_set, container, false);
    }

    public ArrayList<ColorChoice> getColors() {
        return colors;
    }

    public void setColors(ArrayList<ColorChoice> colors) {
        this.colors = colors;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ctx = getActivity();

        nextButton = (Button) view.findViewById(R.id.button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<borderColors.size(); i++) {
                    Log.d("BORDERCOLORFUL", "Color ROW IDS:" + borders.get(i).getRows());
                }
                for (int i=0; i<borderColors.size(); i++) {
                    Log.d("BORDERCOLORFUL", "Color COL IDS:" + centers.get(i).getCols());
                }
                mCallbacks.generateBoard(borders, centers);
            }
        });
        borderClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                Log.d("BORDER", "Border: " + id);
                v.setActivated(true);
                currentColorButton = (Button) v;
                currentBorder = borders.get(id-1);
                currentColor = colors.get(id-1).getColor();
                disableAllHorizontal();
                disableVertical();

            }
        };
        centerClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                Log.d("CENTER", "Center: " + id);
                v.setActivated(true);
                currentColorButton = (Button) v;
                currentCenter = centers.get(id-1);
                currentColor = colors.get(id-1).getColor();
                disableHorizontal();
                disableAllVertical();

            }
        };
        colClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                v.setBackgroundColor(colors.get(currentColorButton.getId()-1).getColor());
                v.setId(colors.get(currentColorButton.getId()-1).getColor());
                currentCenter.addLine(id);
                disableHorizontal();
                button();
            }
        };
        rowClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                v.setBackgroundColor(colors.get(currentColorButton.getId()-1).getColor());
                v.setId(colors.get(currentColorButton.getId()-1).getColor());
                currentBorder.addLine(id);
                disableVertical();
                button();
            }
        };
        LinearLayout borders = (LinearLayout) view.findViewById(R.id.border_colors);
        RelativeLayout gridframe = (RelativeLayout) view.findViewById(R.id.gridframe);
        borders.setWeightSum(colors.size());
        for (int j = 1; j < colors.size() + 1; j++) {
            Button btnTag = new Button(getActivity());
            btnTag.setId(j);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 1.0));
//            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            btnTag.setText(colors.get(j-1).toString());
            btnTag.setBackgroundColor(colors.get(j-1).getColor());
            btnTag.setOnClickListener(borderClick);
            borders.addView(btnTag);
            borderColors.add(btnTag);
        }
        LinearLayout centers = (LinearLayout) view.findViewById(R.id.center_colors);
        centers.setWeightSum(colors.size());
        for (int j = 1; j < colors.size() + 1; j++) {
            Button btnTag = new Button(getActivity());
            btnTag.setText(colors.get(j-1).toString());
            btnTag.setId(j);
            btnTag.setBackgroundColor(colors.get(j-1).getColor());
            btnTag.setOnClickListener(centerClick);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 1.0));
            centers.addView(btnTag);
            centerColors.add(btnTag);
        }
        TableLayout grid = (TableLayout) view.findViewById(R.id.grid);

        int rlHeight = getRLHeight();
        int rlWidth = getRLWidth();
        double gridHeight = rlHeight*9.0/10.5;
        double btnHeight = rlHeight*0.75/10.5;
        Log.d("DIMEN", "Double Grid PX Height: " + gridHeight);
        double gridWidth = rlWidth*9.0/10.5;
        double btnWidth = rlWidth*0.75/10.5;
        Log.d("DIMEN", "Double Grid PX Width: " + gridWidth);
        int gridSize = 0;
        if (gridHeight>gridWidth) {
            gridSize = (int) gridWidth;
        }
        else {
            gridSize = (int) gridHeight;
        }

        Log.d("DIMEN", "Real Grid Size: " + gridSize);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(gridSize, gridSize);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        grid.setLayoutParams(lp);
//        int gridWidth = (int) convertDpToPixel(400, getActivity());
//        int gridHeight = (int) convertDpToPixel(400, getActivity());
        Log.d("DIMEN", "GRIDWIDTH: " + gridWidth + " GRIDHEIGHT: " + gridHeight );
        grid.setWeightSum(colors.size()*lines);
        Drawable tile = getResources().getDrawable(R.drawable.tile);

        for (int row = 1; row < colors.size()*lines + 1; row++)
        {
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setLayoutParams(new TableRow.LayoutParams(gridSize, gridSize/(colors.size()*lines)));
            tableRow.setWeightSum(colors.size()*lines);

            for (int column = 1; column < colors.size()*lines + 1; column++)
            {
                TileButton btn = new TileButton(getActivity());
                btn.setEnabled(false);
                btn.setRowId(row);
                btn.setColId(column);
//                btn.setWidth(750/(colors.size()*lines)-4);
//                btn.setHeight(750/(colors.size()*lines)-4);
                btn.setText("R"+row+"C"+column);
//                btn.setVisibility(View.VISIBLE);
                btn.setLayoutParams(new TableRow.LayoutParams(gridSize/(colors.size()*lines), gridSize/(colors.size()*lines)));
//                btn.setPadding(0,0,0,0);
//                btn.setPadding(2, 2, 2, 2);
                tableRow.addView(btn);
            }
            grid.addView(tableRow, new TableLayout.LayoutParams(gridSize, gridSize/(colors.size()*lines)));
        }
        LinearLayout vertical = (LinearLayout) view.findViewById(R.id.row_buttons);
        vertical.setWeightSum(colors.size()*lines);
        for (int j = 1; j < colors.size()*lines + 1; j++) {
            Button btnTag = new Button(getActivity());
            btnTag.setLayoutParams(new LinearLayout.LayoutParams((int) btnWidth, gridSize/(colors.size()*lines)));
            btnTag.setGravity(Button.TEXT_ALIGNMENT_CENTER);
            btnTag.setText("Row " + j);
            btnTag.setOnClickListener(rowClick);
            btnTag.setId(j);
            vertical.addView(btnTag);
            rows.add(btnTag);
        }
        LinearLayout horizontal = (LinearLayout) view.findViewById(R.id.col_buttons);
        horizontal.setWeightSum(colors.size()*lines);
        for (int j = 1; j < colors.size()*lines + 1; j++) {
            Button btnTag = new Button(getActivity());


            btnTag.setLayoutParams(new LinearLayout.LayoutParams(gridSize/(colors.size()*lines), (int) btnHeight));
            btnTag.setGravity(Button.TEXT_ALIGNMENT_CENTER);
            btnTag.setText("Col " + j);
            btnTag.setId(j);
            btnTag.setOnClickListener(colClick);
            horizontal.addView(btnTag);
            cols.add(btnTag);
        }
        Log.d("DIMEN", rlWidth + ", " + rlHeight);


    }

    private void button() {
        boolean allSet = true;
        for (int i = 0; i < cols.size(); i++) {
            if (cols.get(i).isEnabled() || rows.get(i).isEnabled()){
                allSet = false;
            }
        }
        if (allSet){
            nextButton.setVisibility(View.VISIBLE);
            nextButton.setEnabled(true);
        }
        else {
            nextButton.setVisibility(View.INVISIBLE);
            nextButton.setEnabled(false);
        }
    }

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public static int getRLHeight(){
        DisplayMetrics display = ctx.getResources().getDisplayMetrics();
        double height = display.heightPixels;
        Log.d("DIMEN", "Real display PX Height: " + height);
        int px = (int) (height*0.7);
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

    public void disableCenters() {
        for (int i = 0; i<centerColors.size(); i++) {
            Button btn = centerColors.get(i);
            btn.setEnabled(false);
        }
    }

    public void disableCenters(int id) {
        for (int i = 0; i<centerColors.size(); i++) {
            Button btn = centerColors.get(i);
            btn.setEnabled(false);
            if (btn.getId() == id) {
                btn.setEnabled(true);
            }
        }
    }

    public void disableBorders() {
        for (int i = 0; i<borderColors.size(); i++) {
            Button btn = borderColors.get(i);
            btn.setEnabled(false);
        }
    }

    public void disableBorders(int id) {
        for (int i = 0; i<borderColors.size(); i++) {
            Button btn = borderColors.get(i);
            btn.setEnabled(false);
            if (btn.getId() == id) {
                btn.setEnabled(true);
            }
        }
    }
    public void disableHorizontal() {
        int count = 0;
        for (int i = 0; i<cols.size(); i++) {
            Button btn = cols.get(i);
            btn.setEnabled(true);
            if (btn.getId() == currentColor){
                count++;
            }
        }
        if (count == lines) {
            disableAllHorizontal();
            currentColorButton.setEnabled(false);
        }
    }

    public void disableAllHorizontal() {
        for (int i = 0; i<cols.size(); i++) {
            Button btn = cols.get(i);
            btn.setEnabled(false);

        }
    }
    public void disableVertical() {
        int count = 0;
        for (int i = 0; i<rows.size(); i++) {
            Button btn = rows.get(i);
            btn.setEnabled(true);
            if (btn.getId() == currentColor){
                count++;
            }
        }
        if (count == lines) {
            disableAllVertical();
            currentColorButton.setEnabled(false);
        }
    }
    public void disableAllVertical() {
        int count = 0;
        for (int i = 0; i<rows.size(); i++) {
            Button btn = rows.get(i);
            btn.setEnabled(false);
        }
    }


    public void setBorders(ArrayList<BorderColor> borders) {
        this.borders = borders;
    }

    public void setCenters(ArrayList<CenterColor> centers) {
        this.centers = centers;
    }
}
