package com.example.calculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    double firstOperand = 0;
    double secondOperand = 0;
    String operation = "";
    String currentEntry = "";
    String displayText = "";
    boolean firstFlag = true;
    boolean decimalUsed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void buttonClicked(View v) {
        //get the contents of the button that was clicked
        String id = (v.getResources().getResourceName(v.getId())).split("/")[1];
        TextView t = (TextView) findViewById(R.id.CalcView);
        Button b = (Button)v;
        String buttonContent = b.getText().toString();

        //t.setText(buttonContent);

        //if it was an operation, append the operation to the display string and clear the current entry string, update text
        if(id.equals("addition")) {
            functionChosen(id,buttonContent);
        }
        else if(id.equals("subtraction")) {
            functionChosen(id,buttonContent);
        }
        else if(id.equals("multiplication")) {
            functionChosen(id,buttonContent);
        }
        else if(id.equals("division")) {
            functionChosen(id,buttonContent);
        }
        else if(id.equals("percent")) {
            if(!currentEntry.equals("") && !firstFlag) {
                t.setText(displayText + currentEntry + "% ");
                currentEntry = Double.toString(firstOperand * Double.parseDouble(currentEntry) * 0.01);

            }
        }
        else if(id.equals("sign")) {
            if (!currentEntry.equals("")) {
                double d;
                d = Double.parseDouble(currentEntry);
                d = d*-1;
                currentEntry = Double.toString(d);
                t.setText(displayText + currentEntry);
            }
            //figure out how to update the view correctly
        }
        else if(id.equals("clear")) {
            firstOperand = 0;
            secondOperand = 0;
            operation = "";
            currentEntry = "";
            displayText = "";
            t.setText("0");
            firstFlag = true;
            decimalUsed = false;
        }
        else if(id.equals("sqrt")) {
            if (!currentEntry.equals("")) {
                double d;
                d = Double.parseDouble(currentEntry);
                d = Math.sqrt(d);
                currentEntry = Double.toString(d);
                t.setText(displayText + currentEntry);
            }
        }
        //if it was equals, append "=" to the display text, perform the operation corresponding to the operation string on the operand(s)
        else if(id.equals("equals") && !currentEntry.equals("")) {
            displayText += currentEntry + " " + buttonContent + " ";
            executeFunction();
        }
        //if it was a number or a decimal, append it to the display string and the current entry string, update text
        else {
            if(!buttonContent.equals(".")) {
                currentEntry += buttonContent;
                t.setText(displayText + currentEntry);
            }
            else {
                if(decimalUsed == false) {
                    currentEntry += buttonContent;
                    decimalUsed = true;
                    t.setText(displayText + currentEntry);
                }
            }
        }

    }

    public void executeFunction() {
        TextView t = (TextView) findViewById(R.id.CalcView);
        if (currentEntry != "") {
            secondOperand = Double.parseDouble(currentEntry);
            currentEntry = "";
        }
        else {
            secondOperand = 0.0;
        }
        if(operation.equals("addition")) {
            firstOperand = firstOperand + secondOperand;
        }
        else if(operation.equals("subtraction")) {
            firstOperand = firstOperand - secondOperand;
        }
        else if(operation.equals("multiplication")) {
            firstOperand = firstOperand * secondOperand;
        }
        else if(operation.equals("division")) {
            firstOperand = firstOperand / secondOperand;
        }

        else{
            t.setText("ERR");
        }
        displayText += firstOperand;
        t.setText(displayText);
        decimalUsed = false;
    }


    //functionChosen(id,buttonContent);
    public void functionChosen(String id, String buttonContent) {


        if(currentEntry != "") {
            if (firstFlag || operation.equals("addition")) {
                firstOperand += Double.parseDouble(currentEntry);
                currentEntry = "";
                displayText = firstOperand + " " + buttonContent + " ";
            }
            else if (operation.equals("subtraction")) {
                firstOperand -= Double.parseDouble(currentEntry);
                currentEntry = "";
                displayText = firstOperand + " " + buttonContent + " ";
            }
            else if (operation.equals("division")) {
                firstOperand /= Double.parseDouble(currentEntry);
                currentEntry = "";
                displayText = firstOperand + " " + buttonContent + " ";
            }
            else if (operation.equals("multiplication")) {
                firstOperand *= Double.parseDouble(currentEntry);
                currentEntry = "";
                displayText = firstOperand + " " + buttonContent + " ";
            }
            else if (operation.equals("sqrt")) {
                firstOperand = Math.sqrt(firstOperand);
                currentEntry = "";
                displayText = firstOperand + " " + buttonContent + " ";
            }
        }

        operation = id;
        TextView t = (TextView) findViewById(R.id.CalcView);
        t.setText(displayText);
        firstFlag = false;
        decimalUsed = false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
