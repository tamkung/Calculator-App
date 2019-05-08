package com.example.myapplication;

import java.text.DecimalFormat;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener {

    private TextView display;
    private Boolean Number = false;
    private CalculatorBrain mCalculator;
    private static final String DIGITS = "0123456789.";

    private Button Per;
    private double var1;
    private double var2;
    private String mWaitingOperator;

    DecimalFormat df = new DecimalFormat("@###########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mCalculator = new CalculatorBrain();
        display = (TextView) findViewById(R.id.result);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);

        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);

        findViewById(R.id.btnadd).setOnClickListener(this);
        findViewById(R.id.btnsub).setOnClickListener(this);
        findViewById(R.id.btnmul).setOnClickListener(this);
        findViewById(R.id.btndiv).setOnClickListener(this);
        findViewById(R.id.btnBL).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btnequal).setOnClickListener(this);
        findViewById(R.id.btnCE).setOnClickListener(this);
//        findViewById(R.id.btnper).setOnClickListener(this);
        findViewById(R.id.btnx2).setOnClickListener(this);


        if (findViewById(R.id.btnsin) != null) {
            findViewById(R.id.btnsin).setOnClickListener(this);
        }
        if (findViewById(R.id.btncos) != null) {
            findViewById(R.id.btncos).setOnClickListener(this);
        }
        if (findViewById(R.id.btntan) != null) {
            findViewById(R.id.btntan).setOnClickListener(this);
        }
        if (findViewById(R.id.btnrt) != null) {
            findViewById(R.id.btnrt).setOnClickListener(this);
        }
        if (findViewById(R.id.btnlog) != null) {
            findViewById(R.id.btnlog).setOnClickListener(this);
        }
        if (findViewById(R.id.btnexp) != null) {
            findViewById(R.id.btnexp).setOnClickListener(this);
        }
        if (findViewById(R.id.btnmod) != null) {
            findViewById(R.id.btnmod).setOnClickListener(this);
        }
        if (findViewById(R.id.btnxp) != null) {
            findViewById(R.id.btnxp).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {

        String buttonPressed = ((Button) v).getText().toString();

        if (DIGITS.contains(buttonPressed)) {



            if (Number) {

                if (buttonPressed.equals(".") && display.getText().toString().contains(".")) {

                } else {
                    display.append(buttonPressed);
                }

            } else {

                if (buttonPressed.equals(".")) {

                    display.setText(0 + buttonPressed);
                } else {
                    display.setText(buttonPressed);
                }

                Number = true;
            }

        } else {

            if (Number) {

                mCalculator.setOperand(Double.parseDouble(display.getText().toString()));
                Number = false;
            }



            mCalculator.performOperation(buttonPressed);
            display.setText(df.format(mCalculator.getResult()));

        }

        Per = (Button)findViewById(R.id.btnper) ;
        Per.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(display.getText().length() > 0){
                    CharSequence name = display.getText().toString();
                    display.setText(name.subSequence(0, name.length()-1));
                }
                else{
                    var1 = 0;
                    var2 = 0;
                    mWaitingOperator = "";
                    display.setText("0");
                }
            }
        });


    }



    public class CalculatorBrain {

//        private double var1;
//        private double var2;
//        private String mWaitingOperator;


        public static final String ADD = "+";
        public static final String SUBTRACT = "-";
        public static final String MULTIPLY = "*";
        public static final String DIVIDE = "/";
        public static final String CLEAR = "C" ;
        public static final String root = "v";
        public static final String SQUARED = "x2";
        public static final String TOGGLESIGN = "+-";
        public static final String SINE = "Sin";
        public static final String COSINE = "Cos";
        public static final String TANGENT = "Tan";
        public static final String Log = "Log";
        public static final String EXP = "exp";
        public static final String Mod = "Mod";
        public static final String Xplus = "x^";
//        public static final String Per= "%";



        public CalculatorBrain() {

            var1 = 0;
            var2 = 0;
            mWaitingOperator = "";

        }

        public void setOperand(double operand) {
            var1 = operand;
        }

        public double getResult() {
            return var1;
        }


        public String toString() {
            return Double.toString(var1);
        }


        protected double performOperation(String operator) {


            if (operator.equals(CLEAR)) {
                var1 = 0;
                mWaitingOperator = "";
                var2 = 0;

            } else if (operator.equals(TOGGLESIGN)) {
                var1 = -var1;
//            } else if (operator.equals(Per)) {
//                var1 = var1 * var2 / 100;
            } else if (operator.equals(SQUARED)) {
                var1 = var1 * var1;

            } else if (operator.equals(Log)) {
                var1 = Math.log10(var1);
            } else if (operator.equals(EXP)) {
                var1 = Math.E * (var1);
            } else if (operator.equals(root)) {
                var1 = Math.sqrt(var1);
//            } else if (operator.equals(Mod)) {
//                var1 = Math.PI*var1;


//------------------------------- sin cos tan ------------------------------------------------------
            } else if (operator.equals(SINE)) {
                var1 = Math.sin(Math.toRadians(var1));

            } else if (operator.equals(COSINE)) {
                var1 = Math.cos(Math.toRadians(var1));

            } else if (operator.equals(TANGENT)) {
                var1 = Math.tan(Math.toRadians(var1));

            } else {
                performWaitingOperation();
                mWaitingOperator = operator;
                var2 = var1;
            }
            return var1;
        }
        //---------------------------------------------- + - * / -------------------------------------------
        protected void performWaitingOperation() {

            if (mWaitingOperator.equals(ADD)) {
                var1 = var2 + var1;

            } else if (mWaitingOperator.equals(SUBTRACT)) {
                var1 = var2 - var1;

            } else if (mWaitingOperator.equals(MULTIPLY)) {
                var1 = var2 * var1;

            } else if (mWaitingOperator.equals(Xplus)) {
                var1 = Math.pow(var2,var1);

            } else if (mWaitingOperator.equals(Mod)) {
                var1 = var2 % var1;

            } else if (mWaitingOperator.equals(DIVIDE)) {
                if (var1 != 0) {
                    var1 = var2 / var1;
                }
            }
        }
    }
}
