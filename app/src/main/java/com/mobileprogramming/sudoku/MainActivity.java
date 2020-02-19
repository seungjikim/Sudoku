package com.mobileprogramming.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    Random random = new Random();
    TableLayout table;
    TableLayout pad;
    TableLayout hintPad;
    CustomButton selected;
    CustomButton[][] buttons = new CustomButton[9][9];
    BoardGenerator board = new BoardGenerator();
    boolean[][] check = new boolean[9][9];
   ToggleButton[] hintButtons = new ToggleButton[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        table = (TableLayout) findViewById(R.id.tableLayout);
        pad = (TableLayout) findViewById(R.id.padTableLayout);
        hintPad = (TableLayout)findViewById(R.id.hintTableLayout);
        TableRow[] tableRow = new TableRow[9];

        for (int i = 0; i < 9; i++) {
            TableLayout.LayoutParams rowLine;
            tableRow[i] = new TableRow(this);
            if (i % 3 == 2 && i != 8) {
                rowLine = new TableLayout.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1.0f);
                rowLine.setMargins(1, 1, 1, 15);
            }
            else {
                rowLine = new TableLayout.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1.0f);
                rowLine.setMargins(1, 1, 1, 1);
            }
            table.addView(tableRow[i]);
            tableRow[i].setLayoutParams(rowLine);
        }


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TableRow.LayoutParams layoutParams;
                if ( j % 3 == 2 && j != 8) {
                    layoutParams = new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT,
                            1.0f);
                    layoutParams.setMargins(1, 1, 15, 1);
                }
                else {
                    layoutParams = new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT,
                            1.0f);
                    layoutParams.setMargins(1, 1, 1, 1);
                }
                buttons[i][j] = new CustomButton(this, i, j);
                buttons[i][j].setLayoutParams(layoutParams);
                buttons[i][j].setBackgroundResource(R.drawable.button_selector);
               buttons[i][j].setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        hintPad.setVisibility(VISIBLE);
                        selected = (CustomButton) v;
                        //text view custom
                        return true;
                    }
                });
                int number = board.get(i, j);
                if (random.nextInt(9) > 2) {
                    buttons[i][j].set(number);
                }
                tableRow[i].addView(buttons[i][j]);
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        pad.setVisibility(VISIBLE);
                        selected = (CustomButton) view;
                    }
                });
            }
        }

        Button.OnClickListener listener = new Button.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button1:
                        selected.set(1);
                        pad.setVisibility(INVISIBLE);
                        find();
                        break;
                    case R.id.button2:
                        selected.set(2);
                        pad.setVisibility(INVISIBLE);
                        find();
                        break;
                    case R.id.button3:
                        selected.set(3);
                        pad.setVisibility(INVISIBLE);
                        find();
                        break;
                    case R.id.button4:
                        selected.set(4);
                        pad.setVisibility(INVISIBLE);
                        find();
                        break;
                    case R.id.button5:
                        selected.set(5);
                        pad.setVisibility(INVISIBLE);
                        find();
                        break;
                    case R.id.button6:
                        selected.set(6);
                        pad.setVisibility(INVISIBLE);
                        find();
                        break;
                    case R.id.button7:
                        selected.set(7);
                        pad.setVisibility(INVISIBLE);
                        find();
                        break;
                    case R.id.button8:
                        selected.set(8);
                        pad.setVisibility(INVISIBLE);
                        find();
                        break;
                    case R.id.button9:
                        selected.set(9);
                        pad.setVisibility(INVISIBLE);
                        find();
                        break;
                    case R.id.buttonCancel:
                        pad.setVisibility(INVISIBLE);
                        find();
                        break;
                    case R.id.buttonDelete:
                        selected.set(0);
                        pad.setVisibility(INVISIBLE);
                        find();
                        break;
                    case R.id.hintOk:
                        selected.checkOnPad(hintButtons);
                        hintPad.setVisibility(INVISIBLE);
                        break;
                    case R.id.hintDelete:
                        selected.deleteOnPad(hintButtons);
                        hintPad.setVisibility(INVISIBLE);
                        break;
                    case R.id.hintCancel:
                        hintPad.setVisibility(INVISIBLE);
                        break;
                }
            }
        };



        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);

        hintButtons[0] = (ToggleButton) findViewById(R.id.hint1);
       // hintButtons[0].setText("1");
        hintButtons[1] = (ToggleButton) findViewById(R.id.hint2);
        hintButtons[1].setText("2");
        hintButtons[2] = (ToggleButton) findViewById(R.id.hint3);
        hintButtons[2].setText("3");
        hintButtons[3] = (ToggleButton) findViewById(R.id.hint4);
        hintButtons[3].setText("4");
        hintButtons[4]= (ToggleButton) findViewById(R.id.hint5);
        hintButtons[4].setText("5");
        hintButtons[5] = (ToggleButton) findViewById(R.id.hint6);
        hintButtons[5].setText("6");
        hintButtons[6] = (ToggleButton) findViewById(R.id.hint7);
        hintButtons[6].setText("7");
        hintButtons[7] = (ToggleButton) findViewById(R.id.hint8);
        hintButtons[7].setText("8");
        hintButtons[8] = (ToggleButton) findViewById(R.id.hint9);
        hintButtons[8].setText("9");
        Button hintOk = (Button) findViewById(R.id.hintOk);
        Button hintCancel = (Button) findViewById(R.id.hintCancel);
        Button hintDelete = (Button) findViewById(R.id.hintDelete);

        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonCancel.setOnClickListener(listener);
        buttonDelete.setOnClickListener(listener);
        hintOk.setOnClickListener(listener);
        hintCancel.setOnClickListener(listener);
        hintDelete.setOnClickListener(listener);

    }
    public void find() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (check[i][j]) {
                    if (buttons[i][j].value == 0) {
                        buttons[i][j].unsetConflict();
                    }
                }
            }
        }//빈 버튼 해제

        for (int i = 0; i < 9; i++) {
            int[]rowCheck = new int[10];
            for (int j = 0; j < 9; j++) {
                if (buttons[i][j].value != 0) {
                    rowCheck[buttons[i][j].value]++;
                }
            }
            for (int k = 1; k <= 9; k++) {
                if (rowCheck[k] <= 1) {
                    for (int h = 0; h < 9; h++) {
                        if (buttons[i][h].value == k) {
                            if (check[i][h]){
                                check[i][h] = false;
                                buttons[i][h].unsetConflict();
                            }
                        }
                    }
                }
            }
        }//행 버튼 해제

        for (int j = 0; j < 9; j++) {
            int[]colCheck = new int[10];
            for (int i = 0; i < 9; i++) {
                if (buttons[i][j].value != 0) {
                    colCheck[buttons[i][j].value]++;
                }
            }
            for (int k = 1; k <= 9; k++) {
                if (colCheck[k] <= 1) {
                    for (int h = 0; h < 9; h++) {
                        if (buttons[h][j].value == k) {
                            if (check[h][j]){
                                check[h][j] = false;
                                buttons[h][j].unsetConflict();
                            }
                        }
                    }
                }
            }
        }//열 버튼 해제


        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                int[] box = new int[10];
                for (int k = 0; k < 3; k++) {
                    for (int h = 0; h < 3; h++) {
                        if (buttons[i + k][j + h].value != 0) {
                            box[buttons[i + k][j + h].value]++;
                        }
                    }
                }
                for (int k = 1; k <= 9; k++) {
                    if (box[k] <= 1) {
                        for (int h = 0; h < 3; h++) {
                            for (int n = 0; n < 3; n++) {
                                if (buttons[i + h][j + n].value == k){
                                    if (check[i + h][j + n]){
                                        check[i + h][j + n] = false;
                                        buttons[i + h][j + n].unsetConflict();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        for (int i = 0; i < 9; i++) {
            int[] rowCheck = new int[10];
            for (int j = 0; j < 9; j++) {
                if (buttons[i][j].value != 0) {
                    rowCheck[buttons[i][j].value]++;
                }
                for (int k = 1; k <= 9; k++) {
                    if (rowCheck[k] > 1) {
                        for (int h = 0; h < 9; h++){
                            if (buttons[i][h].value == k) {
                                buttons[i][h].setConflict();
                                check[i][h] = true;
                            }
                        }
                    }
                }
            }
        }
        //열을 탐색한다.
        for (int i = 0; i < 9; i++) {
            int[] colCheck = new int[10];
            for (int j = 0; j < 9; j++) {
                if (buttons[j][i].value != 0) {
                    colCheck[buttons[j][i].value]++;
                }
                for (int k = 1; k <= 9; k++) {
                    if (colCheck[k] > 1) {
                        for (int h = 0; h < 9; h++){
                            if (buttons[h][i].value == k) {
                                buttons[h][i].setConflict();
                                check[h][i] = true;
                            }
                        }
                    }
                }
            }
        }
        //3x3 선택
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                int[] box = new int[10];
                for (int k = 0; k < 3; k++) {
                    for (int h = 0; h < 3; h++) {
                        if (buttons[i + k][j + h].value != 0) {
                            box[buttons[i + k][j + h].value]++;
                        }
                    }
                }
                for (int k = 1; k <= 9; k++) {
                    if (box[k] > 1) {
                        for (int h = 0; h < 3; h++) {
                            for (int n = 0; n < 3; n++) {
                                if (buttons[i + h][j + n].value == k){
                                        buttons[i + h][j + n].setConflict();
                                        check[i + h][j + n] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
