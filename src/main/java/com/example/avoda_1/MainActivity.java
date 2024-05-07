package com.example.avoda_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    String userChoice;
    Context context;
    TextView num1, num2;
    TextView result;

    Button calculate;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.editTextNum1);
        num2 = findViewById(R.id.editTextNum2);
        result = findViewById(R.id.result);

        context = this;

        spinner = (Spinner) findViewById(R.id.spinner_Calc);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.arrayOP,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userChoice = (String) parent.getItemAtPosition(position);
                Toast.makeText(context, userChoice, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //לא צריך לכתוב כלום
            }
        });

        calculate = findViewById(R.id.calcbutton);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculation();
            }
        });

    }

    private void Calculation() {
            try {
                int number1 = Integer.parseInt(num1.getText().toString());
                int number2 = Integer.parseInt(num2.getText().toString());
                int resultValue;

                switch (userChoice) {
                    case "+":
                        resultValue = number1 + number2;
                        break;
                    case "-":
                        resultValue = number1 - number2;
                        break;
                    case "*":
                        resultValue = number1 * number2;
                        break;
                    case "/":
                        if (number2 == 0) {
                            Toast.makeText(this, "Can not divide by 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        resultValue = number1 / number2;
                        break;
                    case "^":
                        resultValue = (int) Math.pow(number1, number2);
                        break;
                    default:
                        throw new IllegalArgumentException("Illegal operation");
                }

                result.setText("Result: " + resultValue);


            } catch (NumberFormatException e) {
                Toast.makeText(this, "Only int numbers", Toast.LENGTH_SHORT).show();
                return;
            }
        num1.setText("");
        num2.setText("");
    }

}