package com.maxim.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView display ,operationDisplay;
    private Button zero,one,two,three,four,five,six,seven,eight,nine,clear,point,addition,subtraction,multiplication,division,modulo,equal,sign;

    private float firstValue = 0,secondValue = 0,result;
    private char operation = '\u0000';

    private int newInputFlag ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        display = findViewById(R.id.display);
        operationDisplay = findViewById(R.id.operationDisplay);

        addition = findViewById(R.id.addition);
        subtraction = findViewById(R.id.subtraction);
        multiplication = findViewById(R.id.multiplication);
        division = findViewById(R.id.division);
        modulo = findViewById(R.id.modulo);
        equal = findViewById(R.id.equal);

        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);

        clear = findViewById(R.id.clear);
        point = findViewById(R.id.point);
        sign = findViewById(R.id.sign);


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current = display.getText().toString();
                if (current.startsWith("-")){
                    display.setText(current.substring(1));
                }else if(!current.isEmpty()){
                    display.setText("-" + current);
                }

            }
        });



        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String secondValueText = display.getText().toString();
                if(secondValueText.isEmpty()){
                    secondValue = 0;
                }else if(secondValueText.equals("error") || secondValueText.equals("overflow")){
                    secondValue = 0;
                }else {
                    secondValue = Float.parseFloat(display.getText().toString());
                }


                switch (operation) {
                    case '+':
                        result = firstValue + secondValue;
                        break;
                    case '-':
                        result = firstValue - secondValue;
                        break;
                    case '*':
                        result = firstValue * secondValue;
                        break;
                    case '/':
                        if (secondValue != 0) {
                            result = firstValue / secondValue;
                        } else {
                            display.setText("error");
                            newInputFlag = 1;
                            return;
                        }
                        break;
                    case '%':
                        if (secondValue != 0) {
                            result = firstValue % secondValue;
                        } else {
                            display.setText("error");
                            newInputFlag = 1;
                            return;
                        }
                        break;
                    default:
                        display.setText("error");
                        newInputFlag = 1;
                        return;
                }

                String resultString;
                int negative =0;

                if(result < 0){
                    negative = 1;
                }


                if(result == (int) result){
                    resultString = String.valueOf((int) result);
                }else {
                    resultString = String.valueOf(result);
                }

                if(negative == 1 && resultString.length() > 11){
                    display.setText("overflow");
                } else if (negative == 0 && resultString.length()>10) {
                    display.setText("overflow");
                }else {
                    display.setText(resultString);
                }

                newInputFlag = 1;
            }
        });

        modulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstValueText = display.getText().toString();
                if(firstValueText.isEmpty()){
                    secondValue = 0;
                }else if(firstValueText.equals("error") || firstValueText.equals("overflow")){
                    secondValue = 0;
                }else {
                    firstValue = Float.parseFloat(display.getText().toString());
                }

                operationDisplay.setText("modulo");
                operation ='%';
                newInputFlag = 1;

            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstValueText = display.getText().toString();
                if(firstValueText.isEmpty()){
                    secondValue = 0;
                }else if(firstValueText.equals("error")|| firstValueText.equals("overflow")){
                    secondValue = 0;
                }else {
                    firstValue = Float.parseFloat(display.getText().toString());
                }
                operationDisplay.setText("division");
                operation ='/';
                newInputFlag = 1;
            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstValueText = display.getText().toString();
                if(firstValueText.isEmpty()){
                    secondValue = 0;
                }else if(firstValueText.equals("error")|| firstValueText.equals("overflow")){
                    secondValue = 0;
                }else {
                    firstValue = Float.parseFloat(display.getText().toString());
                }
                operationDisplay.setText("multiplication");
                operation ='*';
                newInputFlag = 1;
            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstValueText = display.getText().toString();
                if(firstValueText.isEmpty()){
                    secondValue = 0;
                }else if(firstValueText.equals("error")|| firstValueText.equals("overflow")){
                    secondValue = 0;
                }else {
                    firstValue = Float.parseFloat(display.getText().toString());
                }
                operationDisplay.setText("subtraction");
                operation ='-';
                newInputFlag = 1;
            }
        });

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstValueText = display.getText().toString();
                if(firstValueText.isEmpty()){
                    secondValue = 0;
                }else if(firstValueText.equals("error")|| firstValueText.equals("overflow")){
                    secondValue = 0;
                }else {
                    firstValue = Float.parseFloat(display.getText().toString());
                }
                operationDisplay.setText("addition");
                operation ='+';
                newInputFlag = 1;
            }
        });


        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = zero.getText().toString();
                String current = display.getText().toString();

                if (newInputFlag == 1){
                    display.setText(input);
                    newInputFlag = 0;

                }else if(current.equals("0")){
                    display.setText(input);
                }else if(current.contains(".") && current.length()<10 ){
                    display.setText(display.getText().toString() + input);
                }else if(current.length()<10){
                    display.setText(display.getText().toString() + input);
                }
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = one.getText().toString();
                String current = display.getText().toString();

                if (newInputFlag == 1) {
                    display.setText(input);
                    newInputFlag = 0;

                } else if (current.equals("0")) {
                    display.setText(input);
                } else if(current.length()<10){
                    display.setText(display.getText().toString() + input);
                }

            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = two.getText().toString();
                String current = display.getText().toString();

                if (newInputFlag == 1){
                    display.setText(input);
                    newInputFlag = 0;

                }else if(current.equals("0")){
                    display.setText(input);
                }else if(current.length()<10){
                    display.setText(display.getText().toString() + input);
                }
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = three.getText().toString();
                String current = display.getText().toString();
                if (newInputFlag == 1){
                    display.setText(input);
                    newInputFlag = 0;

                }else if(current.equals("0")){
                    display.setText(input);
                }else if(current.length()<10){
                    display.setText(display.getText().toString() + input);
                }
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = four.getText().toString();
                String current = display.getText().toString();
                if (newInputFlag == 1){
                    display.setText(input);
                    newInputFlag = 0;

                }else if(current.equals("0")){
                    display.setText(input);
                }else if(current.length()<10){
                    display.setText(display.getText().toString() + input);
                }
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = five.getText().toString();
                String current = display.getText().toString();
                if (newInputFlag == 1){
                    display.setText(input);
                    newInputFlag = 0;

                }else if(current.equals("0")){
                    display.setText(input);
                }else if(current.length()<10){
                    display.setText(display.getText().toString() + input);
                }
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = six.getText().toString();
                String current = display.getText().toString();
                if (newInputFlag == 1){
                    display.setText(input);
                    newInputFlag = 0;

                }else if(current.equals("0")){
                    display.setText(input);
                }else if(current.length()<10){
                    display.setText(display.getText().toString() + input);
                }
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = seven.getText().toString();
                String current = display.getText().toString();
                if (newInputFlag == 1){
                    display.setText(input);
                    newInputFlag = 0;

                }else if(current.equals("0")){
                    display.setText(input);
                }else if(current.length()<10){
                    display.setText(display.getText().toString() + input);
                }
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = eight.getText().toString();
                String current = display.getText().toString();
                if (newInputFlag == 1){
                    display.setText(input);
                    newInputFlag = 0;

                }else if(current.equals("0")){
                    display.setText(input);
                }else if(current.length()<10){
                    display.setText(display.getText().toString() + input);
                }
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = nine.getText().toString();
                String current = display.getText().toString();
                if (newInputFlag == 1){
                    display.setText(input);
                    newInputFlag = 0;

                }else if(current.equals("0")){
                    display.setText(input);
                }else if(current.length()<10){
                    display.setText(display.getText().toString() + input);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("");
                operationDisplay.setText("operation");
                firstValue = 0;
                secondValue = 0;
                result = 0;
                operation = '\u0000';
                newInputFlag = 0;


            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = point.getText().toString();
                String current = display.getText().toString();


                if(!current.isEmpty() && !current.contains(".") && newInputFlag == 0){
                    display.setText(display.getText().toString() + input);
                }

            }
        });

    }
}