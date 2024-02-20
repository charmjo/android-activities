package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_old extends AppCompatActivity{
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

        btnSum .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int num1,num2,result;
          //   typecast text to integer
              num1 = Integer.parseInt(String.valueOf(edtFnumber.getText()));
              num2 = Integer.parseInt(String.valueOf(edtSnumber.getText()));
              result=num1+num2;
              textResult.setText(String.valueOf(result));
                Toast.makeText(getApplicationContext(),"The sum is: "+ result, Toast.LENGTH_LONG).show();
            }
        });

        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1,num2,result;
                num1 = Integer.parseInt(String.valueOf(edtFnumber.getText()));
                num2 = Integer.parseInt(String.valueOf(edtSnumber.getText()));
                result=num1 * num2;
                textResult.setText(String.valueOf(result));
                Toast.makeText(getApplicationContext(),"The product is: "+ result, Toast.LENGTH_LONG).show();
            }
        });
    }



}