package edu.sjsu.android.mortgagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBarPercentage;
    private TextView seekBarTextPercent;
    private TextView amountBorrowed;
    private TextView monthlypayment;
    private TextView paymentTitle;
    private RadioGroup radioGroup;
    private CheckBox taxCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountBorrowed = (TextView) findViewById(R.id.amountBorrowed);
        monthlypayment = (TextView) findViewById(R.id.monthlypayment);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        taxCheckBox = (CheckBox) findViewById(R.id.taxCheckBox);

        seekBarPercentage = (SeekBar) findViewById(R.id.seekBarPercentage);
        seekBarTextPercent = (TextView) findViewById(R.id.textpercentage);
        seekBarPercentage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //seekBar_progress = (float)progress;
                seekBarTextPercent.setText(Float.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void onClick(View view){
        if(view.getId() == R.id.calbutton){
            //Error: No text is entered for the amount borrowed
            if(amountBorrowed.length()==0){
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show();
                return;
            }

            //Error: No radio button is checked for the term year
            if(radioGroup.getCheckedRadioButtonId() == -1){
                Toast.makeText(this, "Please select a term year", Toast.LENGTH_LONG).show();
                return;
            }
            //Radio Button (Term)is selected so no error, proceed to calculating Mortgage
            else{
                double amount = Float.parseFloat(amountBorrowed.getText().toString());

                //Get the selected radiobutton from radio group         Source:https://stackoverflow.com/questions/18179124/android-getting-value-from-selected-radiobutton
                int selectedButtonID = radioGroup.getCheckedRadioButtonId();

                //Finding selected Radio Button from id returned
                RadioButton selectedRadioButtonTerm = (RadioButton) findViewById(selectedButtonID);

                int selectedTerm = Integer.parseInt(selectedRadioButtonTerm.getText().toString());
                System.out.println(selectedTerm);
                System.out.println(amount);
                //String selectedRate = seekBarTextPercent.getText().toString();
                double rate = seekBarPercentage.getProgress();
                System.out.println(rate);
                //Taxes and Insurance are supposed to be included in the Mortgage Calculation
                double tax = 0;
                if(taxCheckBox.isChecked()){
                    tax = 0.1*amount;
                }
                String mortgageamountpayment = String.valueOf((MortgageCalc.calcMortgage(amount, rate ,selectedTerm , tax)));
                paymentTitle = (TextView) findViewById(R.id.paymenttitle);
                paymentTitle.setText("Your Monthly Payment");
                monthlypayment.setText("$" + mortgageamountpayment);
            }
        }
    }
}