package com.example.omnia.MyCalculator;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.omnia.MyCalculator.R;
import com.udojava.evalex.Expression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class ExpressionActivity extends AppCompatActivity {

    //---------------------------------start of frontend variables declaration---------------------------------------------

    EditText edittextup;
    TextView infotextView;

    Button buttonseven;
    Button buttoneight ;
    Button buttonnine;
    Button buttonfour ;
    Button buttonfive ;
    Button buttonsix;
    Button buttonone ;
    Button buttontwo;
    Button buttonthree;
    Button buttondot ;
    Button buttonzero ;
    Button buttonequal ;
    Button buttonmultiply ;
    Button buttondivide ;
    Button buttonsubtract;
    Button buttonadd ;
    Button buttoncross ;
    Button buttonclear ;
    Button buttonans;
    Button buttonExpressionActivity;
    Button buttonfirstleft ;
    Button buttonfirstright ;
    Button buttonsecondleft ;
    Button buttonsecondright ;
    Button buttonthirdleft ;
    Button buttonthirdright ;

    //-------------------end of frontend variables declaration-----------------------------

    //-----------------------start of backend variable declaration------------------------------

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    public char CURRENT_ACTION='n';

    private double valueOne =0.0;
    private double valueTwo=0.0;
    private double result;

    //----------------------------end of backend variable declaration------------------------------

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expression);

        //----------------------binding front end variables to front end components----------------------

        edittextup = (EditText) findViewById(R.id.editTextUp);
        infotextView = (TextView) findViewById(R.id.infoTextView);
        
        edittextup.setShowSoftInputOnFocus(false);
        
        edittextup.setText("  ");

        buttonseven = (Button) findViewById(R.id.buttonSeven);
        buttoneight = (Button) findViewById(R.id.buttonEight);
        buttonnine = (Button) findViewById(R.id.buttonNine);
        buttonfour = (Button) findViewById(R.id.buttonFour);
        buttonfive = (Button) findViewById(R.id.buttonFive);
        buttonsix = (Button) findViewById(R.id.buttonSix);
        buttonone = (Button) findViewById(R.id.buttonOne);
        buttontwo = (Button) findViewById(R.id.buttonTwo);
        buttonthree = (Button) findViewById(R.id.buttonThree);
        buttondot = (Button) findViewById(R.id.buttonDot);
        buttonzero = (Button) findViewById(R.id.buttonZero);
        buttonequal = (Button) findViewById(R.id.buttonEqual);
        buttonmultiply = (Button) findViewById(R.id.buttonMultiply);
        buttondivide = (Button) findViewById(R.id.buttonDivide);
        buttonsubtract = (Button) findViewById(R.id.buttonSubtract);
        buttonadd = (Button) findViewById(R.id.buttonAdd);
        buttoncross = (Button) findViewById(R.id.buttonCross);
        buttonclear = (Button) findViewById(R.id.buttonClear);
        buttonans = (Button) findViewById(R.id.buttonAns);
        buttonExpressionActivity=(Button) findViewById(R.id.buttonExpression);

        buttonfirstleft = (Button) findViewById(R.id.buttonFirstLeft);
        buttonfirstright = (Button) findViewById(R.id.buttonFirstRight);
        buttonsecondleft = (Button) findViewById(R.id.buttonSecondLeft);
        buttonsecondright = (Button) findViewById(R.id.buttonSecondRight);
        buttonthirdleft = (Button) findViewById(R.id.buttonThirdLeft);
        buttonthirdright=(Button) findViewById(R.id.buttonThirdRight);

        try {
            File test=new File("/data/data/com.example.omnia.MyCalculator/mytextfile.txt");
            if(!test.exists())
            {
                test.createNewFile();
                FileOutputStream fileout=new FileOutputStream(new File("/data/data/com.example.omnia.MyCalculator/mytextfile.txt"));
                OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                outputWriter.write("0.0");
                result=0.0;
                outputWriter.close();
                fileout.close();
            }
            else
            {

                FileInputStream fileIn=new FileInputStream(new File("/data/data/com.example.omnia.MyCalculator/mytextfile.txt"));
                InputStreamReader InputRead= new InputStreamReader(fileIn);

                char[] inputBuffer= new char[100];
                String s="";
                int charRead;

                while ((charRead=InputRead.read(inputBuffer))>0) {
                    // char to string conversion
                    String readstring=String.copyValueOf(inputBuffer,0,charRead);
                    s +=readstring;
                }
                InputRead.close();
                fileIn.close();
                result=Double.parseDouble(s);
            }

        } catch (Exception e) {
            Log.e("ReadWriteFile", "Unable to write to the TestFile.txt file.");
        }

        //----------------------end of binding front end variables to front end components----------------------

        buttonfirstleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" (");
                }
            }
        });

        buttonfirstright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" )");
                }
            }
        });

        buttonsecondleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" (");
                }
            }
        });

        buttonsecondright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" )");
                }
            }
        });

        buttonthirdleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" (");
                }
            }
        });

        buttonthirdright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" )");
                }
            }
        });

        buttonExpressionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayMain();
            }
        });

        buttonans.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                        edittextup.setText(edittextup.getText().toString()+" "+result);
                }
            }
        });

        buttondot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" .");

                }
            }
        });

        buttonzero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" 0");

                }
            }
        });

        buttonone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" 1");

                }
            }
        });

        buttontwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" 2");

                }
            }
        });

        buttonthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" 3");

                }
            }
        });

        buttonfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" 4");

                }
            }
        });

        buttonfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" 5");

                }
            }
        });

        buttonsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" 6");

                }
            }
        });

        buttonseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" 7");

                }
                else edittextup.setText("hj");
            }
        });

        buttoneight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" 8");

                }
            }
        });

        buttonnine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" 9");

                }
            }
        });

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" +");

                }
            }
        });

        buttonsubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" -");

                }
            }
        });

        buttonmultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" *");

                }
            }
        });

        buttondivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    edittextup.setText(edittextup.getText().toString()+" /");

                }
            }
        });

        buttonequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String primarytTextUp=edittextup.getText().toString();

                if(primarytTextUp.length()>2 )computeCalculation(primarytTextUp);


                    infotextView.setText(String.valueOf(result));

                valueOne=0.0;
                valueTwo=0.0;
                edittextup.setText("  ");

                edittextup.clearFocus();

            }
        });

        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittextup.setText("  ");
                infotextView.setText("  ");

            }
        });
        buttoncross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextup.hasFocus())
                {
                    String primarytTextUp=edittextup.getText().toString();

                    if(primarytTextUp.length()>2)
                    {
                        edittextup.setText(primarytTextUp.substring(0,primarytTextUp.length()-1));
                    }

                }
                infotextView.setText("  ");
            }
        });
    }

    private void displayMain() {

        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    private void computeCalculation(String primarytTextUp) {
        String primaryTextUpWithoutSpace=primarytTextUp.replaceAll(" ","");
        result= new Expression("("+primaryTextUpWithoutSpace+")").eval().doubleValue();

        try
        {
            FileOutputStream fileout=new FileOutputStream(new File("/data/data/com.example.omnia.MyCalculator/mytextfile.txt"));
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(String.valueOf(result));
            outputWriter.close();
            fileout.close();

        }
        catch (Exception e)
        {

        }

    }

}

