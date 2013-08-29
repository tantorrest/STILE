package com.tantorrest.Stile;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by tony on 8/19/13.
 */
public class ColorChoiceFragment extends Fragment {

    private ChoiceCallbacks mCallbacks;
    private Button red, green, blue, cyan, yellow, next;

    public ColorChoiceFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        red = (Button) getView().findViewById(R.id.red);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorChoice red = new ColorChoice(Color.RED);
                mCallbacks.onChoiceSelected(red);
                disable(Color.RED);
            }
        });
        green = (Button) getView().findViewById(R.id.green);
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorChoice green = new ColorChoice(Color.GREEN);
                mCallbacks.onChoiceSelected(green);
                disable(Color.GREEN);
            }
        });
        blue = (Button) getView().findViewById(R.id.blue);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorChoice blue = new ColorChoice(Color.BLUE);
                mCallbacks.onChoiceSelected(blue);
                disable(Color.BLUE);
            }
        });
        cyan = (Button) getView().findViewById(R.id.cyan);
        cyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorChoice cyan = new ColorChoice(Color.CYAN);
                mCallbacks.onChoiceSelected(cyan);
                disable(Color.CYAN);
            }
        });
        yellow = (Button) getView().findViewById(R.id.yellow);
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorChoice yellow = new ColorChoice(Color.YELLOW);
                mCallbacks.onChoiceSelected(yellow);
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
        Button pick = (Button) getView().findViewById(R.id.pick);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialog colorPickerDialog = new ColorPickerDialog(getActivity(),new ColorPickerDialog.OnColorChangedListener() {
                    @Override
                    public void colorChanged(String key, int color) {
                        ColorChoice cc = new ColorChoice(color);
                        mCallbacks.onChoiceSelected(cc);
                    }
                },"KEY",Color.WHITE, Color.BLACK);
                colorPickerDialog.show();
            }
        });

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
        return inflater.inflate(R.layout.fragment_color_choice, container, false);
    }
}
