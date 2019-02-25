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
    double answer;

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
        if(id.equals("addition") && operation.equals("")) {
            functionChosen(id,buttonContent);
        }
        else if(id.equals("subtraction") && operation.equals("")) {
            functionChosen(id,buttonContent);
        }
        else if(id.equals("multiplication") && operation.equals("")) {
            functionChosen(id,buttonContent);
        }
        else if(id.equals("division") && operation.equals("")) {
            functionChosen(id,buttonContent);
        }
        else if(id.equals("percent") && operation.equals("")) {
            functionChosen(id, buttonContent);
        }
        else if(id.equals("sign") && !currentEntry.equals("")) {
            double d;
            d = Double.parseDouble(currentEntry);
            d = d*-1;
            currentEntry = Double.toString(d);
            //figure out how to update the view correctly
        }
        else if(id.equals("clear")) {
            firstOperand = 0;
            secondOperand = 0;
            operation = "";
            currentEntry = "";
            displayText = "";
            t.setText("0");
        }
        else if(id.equals("sqrt")) {
            functionChosen(id,buttonContent);
        }
        //if it was equals, append "=" to the display text, perform the operation corresponding to the operation string on the operand(s)
        else if(id.equals("equals") || !currentEntry.equals("")) {
            displayText += currentEntry + " " + buttonContent + " ";
            executeFunction();
        }
        //if it was a number or a decimal, append it to the display string and the current entry string, update text
        else {
            currentEntry += buttonContent;
            t.setText(displayText + currentEntry);
        }

    }

    public void executeFunction() {
        TextView t = (TextView) findViewById(R.id.CalcView);
        if (currentEntry != "") {
            secondOperand = Double.parseDouble(currentEntry);
        }
        else {
            secondOperand = 0.0;
        }
        if(operation.equals("addition")) {
            answer = firstOperand + secondOperand;
        }
        else if(operation.equals("subtraction")) {
            answer = firstOperand - secondOperand;
        }
        else if(operation.equals("multiplication")) {
            answer = firstOperand * secondOperand;
        }
        else if(operation.equals("division")) {
            answer = firstOperand / secondOperand;
        }
        else if(operation.equals("sqrt")) {
            answer = Math.sqrt(firstOperand);
        }
        else{
            t.setText("ERR");
        }
        displayText += answer;
        t.setText(displayText);
    }

    public void functionChosen(String id, String buttonContent) {
        operation = id;
        firstOperand += Double.parseDouble(currentEntry);
        displayText += currentEntry + " " + buttonContent + " ";
        TextView t = (TextView) findViewById(R.id.CalcView);
        t.setText(displayText);
        currentEntry = "";
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
