// Code with bindings
package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplecalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // creating Widget reference
//    EditText edtFnumber;
//    EditText edtSnumber;
//    TextView textResult;
//    Button btnSum,btnProduct;

    // Define the binding class
    ActivityMainBinding binding;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//        setContentView(R.layout.activity_main);

        //Instantiating widget references
//        edtFnumber = (EditText) findViewById(R.id.edtFirstNumber);
//        edtSnumber = (EditText) findViewById(R.id.edtSecondNumber);
//        textResult = (TextView) findViewById(R.id.txtResult);
//        btnSum = (Button) findViewById(R.id.btnFindSum);
//        btnProduct = (Button) findViewById(R.id.btnFindProduct);

        binding.btnFindSum.setOnClickListener(this);
        binding.btnFindProduct.setOnClickListener(this);
    } // end of OnCreate method

    @Override
    public void onClick(View v) {

        if (validateForm()) {
            if(v.getId()==R.id.btnFindSum) {
                int num1,num2,result;
                num1 = Integer.parseInt(String.valueOf((binding.edtFirstNumber.getText())));
                num2 = Integer.parseInt(String.valueOf(binding.edtSecondNumber.getText()));
                result = num1+num2;
                binding.txtResult.setText((String.valueOf(result)));
                // this will add a block of message that will float for a while displaying what text is placed.
                Toast.makeText(getApplicationContext(),"The sum is:"+ result,Toast.LENGTH_LONG).show();
            } else if (v.getId()==R.id.btnFindProduct) {
                int num1,num2,result;
                num1 = Integer.parseInt(String.valueOf(binding.edtFirstNumber.getText()));
                num2 = Integer.parseInt(String.valueOf(binding.edtSecondNumber.getText()));
                result= num1 * num2;
                binding.txtResult.setText(String.valueOf(result));
            }
        }

    } // end of MainActivity class

    private boolean validateForm () {
        if(binding.edtFirstNumber.length()==0) {
            binding.edtFirstNumber.setError("This field is required");
            return false;
        }

        if (binding.edtSecondNumber.length() == 0) {
            binding.edtSecondNumber.setError("This field is required");
            return false;
        }

        return true;
    }
}