package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_implement extends AppCompatActivity implements View.OnClickListener {
    // creating Widget reference
    EditText edtFnumber;
    EditText edtSnumber;
    TextView textResult;
    Button btnSum,btnProduct;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiating widget references
        edtFnumber = (EditText) findViewById(R.id.edtFirstNumber);
        edtSnumber = (EditText) findViewById(R.id.edtSecondNumber);
        textResult = (TextView) findViewById(R.id.txtResult);
        btnSum = (Button) findViewById(R.id.btnFindSum);
        btnProduct = (Button) findViewById(R.id.btnFindProduct);

        btnProduct.setOnClickListener(this);
        btnSum.setOnClickListener(this);
    } // end of OnCreate method

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnFindSum) {
            int num1,num2,result;
            num1 = Integer.parseInt(String.valueOf((edtFnumber.getText())));
            num2 = Integer.parseInt(String.valueOf(edtSnumber.getText()));
            result = num1+num2;
            textResult.setText((String.valueOf(result)));
            // this will add a block of message that will float for a while displaying what text is placed.
            Toast.makeText(getApplicationContext(),"The sum is:"+ result,Toast.LENGTH_LONG).show();
        } else if (v.getId()==R.id.btnFindProduct) {
            int num1,num2,result;
            num1 = Integer.parseInt(String.valueOf(edtFnumber.getText()));
            num2 = Integer.parseInt(String.valueOf(edtSnumber.getText()));
            result= num1 * num2;
            textResult.setText(String.valueOf(result));
        }
    } // end of MainActivity class
}