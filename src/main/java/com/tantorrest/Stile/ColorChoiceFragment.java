package com.tantorrest.Stile;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by tony on 8/19/13.
 */
public class ColorChoiceFragment extends Fragment implements ColorCallbacks{

    private ChoiceCallbacks mCallbacks;
    private Button red, green, blue, cyan, yellow, next, addcolor;
    private ColorBar colorBar;
    public LinearLayout buttons;
    public RelativeLayout bar;
    private int width, height;
    private LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
    private View.OnLongClickListener removeClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            LinearLayout parent = (LinearLayout) v.getParent();
            parent.removeView(v);
            mCallbacks.removeColor(v.getId());
            return true;
        }
    };

    public ColorChoiceFragment() {

    }

    @Override
    public void onColorTouch(int color) {
        addcolor.setBackgroundColor(color);
        addcolor.setId(color);
        if (color< 384){
            addcolor.setTextColor(Color.BLACK);
        } else {
            addcolor.setTextColor(Color.BLACK);
        }
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        btnParams.leftMargin=width/5;
        btnParams.rightMargin=width/5;
        btnParams.topMargin=10;
        btnParams.bottomMargin=10;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bar = (RelativeLayout) view.findViewById(R.id.colorbar);
        buttons = (LinearLayout) view.findViewById(R.id.buttons);
        addcolor = (Button) view.findViewById(R.id.add_color);
        addcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onChoiceSelected(new ColorChoice(v.getId()));
                Button btn = new Button(getActivity());
                btn.setBackgroundColor(v.getId());
                btn.setId(v.getId());
                btn.setLayoutParams(btnParams);
                btn.setOnLongClickListener(removeClick);
                buttons.addView(btn);
            }
        });

        red = (Button) getView().findViewById(R.id.red);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorChoice red = new ColorChoice(Color.RED);
                mCallbacks.onChoiceSelected(red);
                Button btn = new Button(getActivity());
                btn.setBackgroundColor(Color.RED);
                btn.setLayoutParams(btnParams);
                btn.setOnLongClickListener(removeClick);
                buttons.addView(btn);
                disable(Color.RED);
            }
        });
        green = (Button) getView().findViewById(R.id.green);
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorChoice green = new ColorChoice(Color.GREEN);
                mCallbacks.onChoiceSelected(green);
                Button btn = new Button(getActivity());
                btn.setBackgroundColor(Color.GREEN);
                btn.setLayoutParams(btnParams);
                btn.setOnLongClickListener(removeClick);
                buttons.addView(btn);
                disable(Color.GREEN);
            }
        });
        blue = (Button) getView().findViewById(R.id.blue);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorChoice blue = new ColorChoice(Color.BLUE);
                mCallbacks.onChoiceSelected(blue);
                Button btn = new Button(getActivity());
                btn.setBackgroundColor(Color.BLUE);
                btn.setLayoutParams(btnParams);
                btn.setOnLongClickListener(removeClick);
                buttons.addView(btn);
                disable(Color.BLUE);
            }
        });
        cyan = (Button) getView().findViewById(R.id.cyan);
        cyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorChoice cyan = new ColorChoice(Color.CYAN);
                mCallbacks.onChoiceSelected(cyan);
                Button btn = new Button(getActivity());
                btn.setBackgroundColor(Color.CYAN);
                btn.setLayoutParams(btnParams);
                btn.setOnLongClickListener(removeClick);
                buttons.addView(btn);
                disable(Color.CYAN);
            }
        });
        yellow = (Button) getView().findViewById(R.id.yellow);
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorChoice yellow = new ColorChoice(Color.YELLOW);
                mCallbacks.onChoiceSelected(yellow);
                Button btn = new Button(getActivity());
                btn.setBackgroundColor(Color.YELLOW);
                btn.setLayoutParams(btnParams);
                btn.setOnLongClickListener(removeClick);
                buttons.addView(btn);
                disable(Color.YELLOW);
            }
        });
        next = (Button) getView().findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineChoiceFragment lineChoiceFragment = new LineChoiceFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.choice_containter, lineChoiceFragment).addToBackStack(null).commit();
            }
        });


        int margin = (int) (width*10.0/276);
        int barHeight = (int) (height*40/366.0);
        int barWidth = width - (2*margin);
        colorBar = new ColorBar(getActivity(), this);
        colorBar.addBars(barWidth, barHeight);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        colorBar.setLayoutParams(rlp);



        bar.addView(colorBar);
//        rl.addView(cpv);


    }

    private void disable(int color) {
        switch (color){
            case Color.RED : red.setEnabled(false);
                break;
            case Color.GREEN : green.setEnabled(false);
                break;
            case Color.BLUE : blue.setEnabled(false);
                break;
            case Color.CYAN : cyan.setEnabled(false);
                break;
            case Color.YELLOW : yellow.setEnabled(false);
                break;

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        resetColorButtons(mCallbacks.getColors());

    }

    private void resetColorButtons(ArrayList<ColorChoice> colors) {
        for (int i=0; i<colors.size(); i++) {
            ColorChoice color = colors.get(i);
            Button btn = new Button(getActivity());
            btn.setBackgroundColor(color.getColor());
            btn.setId(color.getColor());
            btn.setLayoutParams(btnParams);
            btn.setOnLongClickListener(removeClick);
            buttons.addView(btn);
        }
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
        return inflater.inflate(R.layout.fragment_color_bar_choice, container, false);
    }

}
