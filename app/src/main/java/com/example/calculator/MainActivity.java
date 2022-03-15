package com.example.calculator;



import android.widget.TextView;
import javax.script.*;

import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuPresenter;

public class MainActivity extends AppCompatActivity {

    Button  buttonC, buttonEqual,buttonBackSpace;
    EditText mathOperation;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonC = (Button) findViewById(R.id.buttonc);
        buttonEqual = (Button) findViewById(R.id.buttoneql);
        buttonBackSpace = (Button) findViewById(R.id.buttonbackspace);
        mathOperation = (EditText) findViewById(R.id.edt1);
        resultTextView = (TextView)findViewById(R.id.resultTextView);

        View.OnTouchListener otl = new View.OnTouchListener() {
            public boolean onTouch (View v, MotionEvent event) {
                return true; 
            }
        };
        mathOperation.setOnTouchListener(otl);


        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = mathOperation.getText().toString();
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                double result = 0;
                try {
                    //resultTextView.setText("   "+engine.eval(currentText));
                    result = (double)engine.eval(currentText);
                    if(result % 1 !=0)
                    {
                        resultTextView.setText("  "+result);
                    }
                    else
                    {
                        resultTextView.setText("  "+(int)result);
                    }

                }
                catch(Exception e)
                {
                    resultTextView.setText("  Please Enter Valid Input");
                }

            }
        });
        buttonBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = mathOperation.getText().toString();
                if(currentText.length() > 0)
                {
                    mathOperation.setText(currentText.substring(0,currentText.length() - 1));
                }
                if(currentText.length() == 1)
                {
                    resultTextView.setText("");
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathOperation.setText("");
                resultTextView.setText("");
            }
        });


    }

    public void buttonClicked(View view) {
        Button b = (Button) view;
        mathOperation.setText(mathOperation.getText() + b.getText().toString());
    }
}