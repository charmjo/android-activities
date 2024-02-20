package com.example.webymax;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.webymax.databinding.ActivityMainBinding;
import com.example.webymax.models.HostingCost;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
        , AdapterView.OnItemClickListener,View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    double planCost = 0;
    double additionalCost = 0;
    double dbCost = 0;
    double stagingCost = 0;
    String webSpace;
    double webSpaceCost = 0;
    String province;
    double totalCost = 0;
    ActivityMainBinding binding;

    DatePickerDialog datePicker;
    HostingCost hCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        // setting up the binding instance
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.rdStartUp.setChecked(false);
        binding.rdGrowBig.setChecked(false);
        binding.rdPremium.setChecked(false);

        ArrayAdapter<CharSequence> adapterSpace = ArrayAdapter.createFromResource(this
                ,R.array.webspace_array
                , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        binding.spnSpace.setAdapter(adapterSpace);

        binding.acProvince.setThreshold(2);
        binding.acProvince.setInputType(InputType.TYPE_NULL);

        ArrayAdapter<CharSequence> adapterProvince = ArrayAdapter.createFromResource(this
                , R.array.province_array
                , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.acProvince.setAdapter(adapterProvince);

        binding.edtDate.setInputType(InputType.TYPE_NULL);

        setListeners();
    } //End of onCreate

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
        webSpace = binding.spnSpace.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(),webSpace,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        province = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),province,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.chkDb.getId()){
            if(binding.chkDb.isChecked()) {
                dbCost = 13.20;
            } else {
                dbCost =0;
            }
        } else if (v.getId() == R.id.chkStaging) {
            if (binding.chkStaging.isChecked()) {
                stagingCost = 8.90;
            } else {
                stagingCost = 0;
            }
        } else if (v.getId()==R.id.edtDate) {
            Calendar cal = Calendar.getInstance();
            int dayofSales = cal.get(Calendar.DAY_OF_MONTH);
            int monthofSales = cal.get(Calendar.MONTH);
            int yearofSales = cal.get(Calendar.YEAR);

            datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    binding.edtDate.setText(day+"/"+(month+1)+"/"+year);
                }
            },yearofSales,monthofSales,dayofSales);
            datePicker.show();
        } else if (v.getId()==R.id.rdPremium) {
            if (binding.rdPremium.isChecked()) {
                binding.rdPremium.setChecked(true);
            }
        } else if (v.getId()== R.id.btnSubmit) {
            // do validation before data gets processed after submission
            if (validateForm()) {
                hCost = new HostingCost(binding.edtName.getText().toString(),province,webSpace, binding.edtDate.getText().toString(),dbCost,stagingCost,planCost);
                String result = hCost.getHostingCost();

                // Snackbar with a "Show Bill" action
                Snackbar.make(binding.ltHost,"Your order has been submitted successfully.",Snackbar.LENGTH_INDEFINITE)
                        .setAction("Show Bill", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
                            }
                        }).show();
            }
        }
    } //end of onClick

    private void setListeners () {
        binding.spnSpace.setOnItemSelectedListener(this);
        binding.acProvince.setOnItemClickListener(this);
        binding.chkDb.setOnClickListener(this);
        binding.chkStaging.setOnClickListener(this);
        binding.rgHostingPlan.setOnCheckedChangeListener(this);
        binding.edtDate.setOnClickListener(this);
        binding.btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (binding.rgHostingPlan.getCheckedRadioButtonId()==R.id.rdStartUp) {
            planCost=25.38;
        } else if (binding.rgHostingPlan.getCheckedRadioButtonId()==R.id.rdGrowBig) {
            planCost=30.10;
        } else {
            planCost=32.65;
        }
    }

    private boolean validateForm() {
        if (binding.edtName.length() == 0) { // validates customer name field
            binding.edtName.setError("Please Enter your name");
            return false;
        } else if (binding.rgHostingPlan.getCheckedRadioButtonId() == -1){ // validates hosting plan radio group
            Toast.makeText(getApplicationContext(),"Please select at least 1 Hosting Plan",Toast.LENGTH_LONG).show();
            return false;
        } else if (!binding.chkStaging.isChecked() == !binding.chkDb.isChecked()) { // validates Additional checkboxes
            Toast.makeText(getApplicationContext(),"Please select at least 1 additional",Toast.LENGTH_LONG).show();
            return false;
        } else if (binding.acProvince.length() < 2) { // validates number of characters inside province field
            binding.acProvince.setError("Please Enter your province");
            return false;
        } else if (isDateNotValid()) { // validates date format.
            // placed date validation in another function as it involves string pattern matching done in more than 1 line.
            Toast.makeText(getApplicationContext(),"Please enter Sales Date",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isDateNotValid () {
        // regex pattern to validate dates in the pattern 12/34/4567
        String dateRegEx = "^([0-9]{1,2}\\/){2}[0-9]{4}$";
        return !Pattern.matches(dateRegEx, binding.edtDate.getText().toString());
    }




}