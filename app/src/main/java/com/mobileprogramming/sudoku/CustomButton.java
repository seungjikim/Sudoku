package com.mobileprogramming.sudoku;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CustomButton extends FrameLayout {
    int row;
    int col;
    int value;
    TextView textView;
    TableLayout tableLayout;
    TextView[] hints = new TextView[9];
    TableRow[] tableRows = new TableRow[3];

    public CustomButton (Context context, int row, int col) {
         super(context);
        row = this.row;
        col = this.col;
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        tableLayout = (TableLayout)layoutInflater.inflate(R.layout.hint_layout, null);
        textView = new TextView(context);
        textView.setTextSize(30);
       // textView.setGravity(vertical);
        textView.setPadding(40, 20, 30, 20);
        tableLayout.setPadding(20, 15, 10, 15);
        addView(textView);
        addView(tableLayout);
        setClickable(true);
        hints[0] = (TextView)findViewById(R.id.textView);
        hints[1] = (TextView)findViewById(R.id.textView2);
        hints[2] = (TextView)findViewById(R.id.textView3);
        hints[3] = (TextView)findViewById(R.id.textView4);
        hints[4] = (TextView)findViewById(R.id.textView5);
        hints[5] = (TextView)findViewById(R.id.textView6);
        hints[6] = (TextView)findViewById(R.id.textView7);
        hints[7] = (TextView)findViewById(R.id.textView8);
        hints[8] = (TextView)findViewById(R.id.textView9);

        for (int i = 0 ; i < 9; i++) {
            hints[i].setVisibility(INVISIBLE);
        }
    }
    public void checkOnPad(ToggleButton[] hb) {
        tableLayout.setVisibility(VISIBLE);
        textView.setVisibility(INVISIBLE);
        for (int i = 0; i < 9; i++) {
            if (hb[i].isChecked()) {
                int ro = i / 3;
                int co = i % 3;
                TableRow r = (TableRow)tableLayout.getChildAt(ro);
                hints[i] = (TextView)r.getChildAt(co);
                hints[i].setVisibility(VISIBLE);
            }
        }
    }
    public void deleteOnPad(ToggleButton[] hb) {
         for (int i = 0; i < 9; i++) {
             if (hb[i].isChecked()) {
                hb[i].setChecked(false);
             }
             for (int j = 0 ; j < 9; j++) {
                 hints[j].setVisibility(INVISIBLE);
             }
         }
         tableLayout.setVisibility(INVISIBLE);
    }
    public void set(int a) {
        textView.setVisibility(VISIBLE);
         tableLayout.setVisibility(INVISIBLE);
        if (a == 0) {
            textView.setText("");
            value = 0;
        }
        else {
            value = a;
            textView.setText(String.valueOf(value));
        }
    }
    public void setConflict() {
        //CustomButton의 background color 변경
        this.setBackgroundColor(Color.RED);
    }
    public void unsetConflict() {
        // CustomButton의 background color 돌려놈
        this.setBackgroundColor(Color.WHITE);
    }

}
