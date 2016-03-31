/**
 Program to calculate tip.
 Author: Kim Young Song.
 E-mail Address: infall346@gmail.com.
 Programming homework #1-B
 Last Changed: March 28, 2016
 */

package com.example.user.hw1_b;

import android.app.Activity;
import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button calBtn;
    EditText other;
    EditText answer;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    Boolean radio3_checked;
    static String STATE_EDIT="edit";
    static String STATE_EDIT2="edit2";
    static String STATE_RADIO3="radio3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calBtn = (Button) findViewById(R.id.calBtn);
        calBtn.setOnClickListener(this);

        answer = (EditText) findViewById(R.id.answer);
        other = (EditText) findViewById(R.id.other);

        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        radio1.setOnClickListener(this);
        radio2.setOnClickListener(this);
        radio3.setOnClickListener(this);
        radio3_checked = radio3.isChecked();

        if (savedInstanceState != null) {
      // Restore value of members from saved state

            answer.setText(savedInstanceState.getString(STATE_EDIT));
            other.setText(savedInstanceState.getString(STATE_EDIT2));
            radio3_checked = savedInstanceState.getBoolean(STATE_RADIO3);
        }

        if(radio3_checked == false) {
            other.setVisibility(View.GONE);
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString(STATE_EDIT,answer.getText().toString());
        savedInstanceState.putString(STATE_EDIT2,other.getText().toString());
        savedInstanceState.putBoolean(STATE_RADIO3, radio3.isChecked());
        super.onSaveInstanceState(savedInstanceState);
    }

/*    public void onRestoreInstanceState(Bundle savedInstanceState) {
// Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
// Restore state members from saved instance
        answer.setText(savedInstanceState.getString(STATE_EDIT));
        other.setText(savedInstanceState.getString(STATE_EDIT2));
        radio3_checked = savedInstanceState.getBoolean(STATE_RADIO3);
    }
*/
    public void onClick(View view){

        if(radio3.isChecked() == false)
            other.setVisibility(View.GONE);
        else
            other.setVisibility(View.VISIBLE);

        if(view.getId() == calBtn.getId())
        {
            if(radio1.isChecked())   //15%
            {
                if(isStringDouble(answer.getText().toString()) == false)    //first check answer is number or not
                {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                double result = Double.parseDouble(answer.getText().toString()); //convert string to double

                if(result < 0 )      //if value is less than zero (exception)
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Total:"+result+"\n"+"Tip:"+(result*0.15),Toast.LENGTH_LONG).show();

            }
            else if(radio2.isChecked())   //20%
            {
                if(isStringDouble(answer.getText().toString()) == false)    //first check answer is number or not
                    {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        return;
                    }

                    double result = Double.parseDouble(answer.getText().toString()); //convert string to double

                    if(result < 0 )      //if value is less than zero (exception)
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(),"Total:"+result+"\n"+"Tip:"+(result*0.2),Toast.LENGTH_LONG).show();
            }

            else if(radio3.isChecked())    //other
            {

                if(isStringDouble(answer.getText().toString()) == false)    //first check answer is number or not
                {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                double result = Double.parseDouble(answer.getText().toString()); //convert string to double

                if(result < 0 )      //if TOTAL value is less than zero (exception)
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                else{
                    if(isStringDouble(other.getText().toString()) == false)  //when other tip value is not number
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

                    else{
                        double other_result = Double.parseDouble(other.getText().toString());
                        if(other_result<0)    //other value is less than zero
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(),"Total:"+result+"\n"+"Tip:"+(result*0.01*Double.parseDouble(other.getText().toString())),Toast.LENGTH_LONG).show();
                    }

                    }
            }
            else   //when noting is selected from radio group
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();

        }
    }

    //This method checks string is number or not
    public static boolean isStringDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}

