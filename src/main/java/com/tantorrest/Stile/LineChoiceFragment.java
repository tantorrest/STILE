package com.tantorrest.Stile;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by tony on 8/19/13.
 */
public class LineChoiceFragment extends Fragment {

    private ChoiceCallbacks mCallbacks;
    private Button one, two, three, four, five, next;
    private View.OnClickListener click;

    public LineChoiceFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button color = (Button) v;
                int lines = Integer.parseInt(color.getText().toString());
                disable();
                mCallbacks.setLines(lines);
            }
        };
        one = (Button) getView().findViewById(R.id.button1);
        one.setOnClickListener(click);
        two = (Button) getView().findViewById(R.id.button2);
        two.setOnClickListener(click);
        three = (Button) getView().findViewById(R.id.button3);
        three.setOnClickListener(click);
        four = (Button) getView().findViewById(R.id.button4);
        four.setOnClickListener(click);
        five = (Button) getView().findViewById(R.id.button5);
        five.setOnClickListener(click);
        next = (Button) getView().findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.create();
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof ChoiceCallbacks)) {
            throw new IllegalStateException(
                    "Activity must implement fragment's callbacks.");
        }

        mCallbacks = (ChoiceCallbacks) activity;
    }

    private void disable() {
        one.setEnabled(false);
        two.setEnabled(false);
        three.setEnabled(false);
        four.setEnabled(false);
        five.setEnabled(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_line_choice, container, false);
    }
}
