package com.example.omnia.MyCalculator;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.omnia.MyCalculator.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

public class MainActivity extends AppCompatActivity {

    //---------------------------------start of frontend variables declaration---------------------------------------------

    EditText edittextUp;
    EditText edittext;
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
    Button buttontogglesign;
    Button buttonexpression;

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

    private int negativeUp=0;
    private int negative=0;

    //----------------------------end of backend variable declaration------------------------------

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = getIntent();
//        String action = intent.getAction();
//        String type = intent.getType();
//
//        if (Intent.ACTION_SEND.equals(action) && type != null) {
//            if ("text/plain".equals(type)) {
//                handleSendText(intent); // Handle text being sent
//            }
//        }

        //----------------------binding front end variables to front end components----------------------

        edittextUp = (EditText) findViewById(R.id.editTextUp);
        edittext = (EditText) findViewById(R.id.editText);
        infotextView = (TextView) findViewById(R.id.infoTextView);

        edittext.setShowSoftInputOnFocus(false);
        edittextUp.setShowSoftInputOnFocus(false);

        edittext.setText("  ");
        edittextUp.setText("  ");

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
        buttontogglesign=(Button) findViewById(R.id.buttonToggleSign);
        buttonexpression=(Button) findViewById(R.id.buttonExpression);



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

        buttonexpression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayExpression();
            }
        });


        buttonans.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    String primarytTextUp=edittextUp.getText().toString();

                    String spaceUp="  ";
                    if(primarytTextUp.startsWith(" "))spaceUp="";
                    String textUp=spaceUp+primarytTextUp + result;

                    if(primarytTextUp.contains("."))
                    {
                        if(floor(result)==ceil(result))
                        {
                            textUp=spaceUp+primarytTextUp + (int)(result);
                            edittextUp.setText(textUp);
                        }
                    }
                    else
                    {
                        edittextUp.setText(textUp);
                    }

                }
                else if(edittext.hasFocus())
                {

                    String primarytText=edittext.getText().toString();

                    String op="";
                    String space="  ";

                    String text=primarytText;
                    infotextView.setText("  ");

                    if(CURRENT_ACTION!='n')
                    {
                        op=op+CURRENT_ACTION;
                        space=" ";
                    }


                    if(primarytText.charAt(1)==' ')
                    {
                        text=primarytText.substring(2);
                    }
                    else text=primarytText;




                    if(primarytText.contains("."))
                    {
                        if(floor(result)==ceil(result))
                        {

                            text=op+space+text + (int)result;
                            edittext.setText(text);
                        }
                    }
                    else
                    {
                        text=op+space+text + result;
                        edittext.setText(text);
                    }

                }
            }
        });

        buttontogglesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    if(negativeUp==0)
                    {
                        String primarytTextUp=edittextUp.getText().toString();
                        String spaceUp="  ";
                        if(primarytTextUp.startsWith(" ")){
                            primarytTextUp="-"+primarytTextUp.substring(2);
                        }
                        else primarytTextUp="-"+primarytTextUp;
                        String textUp=spaceUp+primarytTextUp;

                        edittextUp.setText(textUp);
                        negativeUp=1;
                    }
                    else
                    {
                        edittextUp.setText("  "+edittextUp.getText().toString().substring(3));
                        negativeUp=0;
                    }

                }
                else if(edittext.hasFocus())
                {
                    if(negative==0)
                    {
                        String primarytText=edittext.getText().toString();

                        String op="";
                        String space="  ";

                        if(primarytText.charAt(1)==' '){
                            primarytText="-"+primarytText.substring(2);
                        }
                        else primarytText="-"+primarytText;
                        String text="";

                        infotextView.setText("  ");

                        if(CURRENT_ACTION!='n')
                        {
                            op=op+CURRENT_ACTION;
                            space=" ";
                        }

                        text=op+space+primarytText;

                        edittext.setText(text);
                        negative=1;
                    }
                    else
                    {
                        String primarytText=edittext.getText().toString();

                        String op="";
                        String space="  ";

                        if(primarytText.charAt(1)==' '){
                            primarytText=primarytText.substring(3);
                        }

                        String text="";

                        infotextView.setText("  ");

                        if(CURRENT_ACTION!='n')
                        {
                            op=op+CURRENT_ACTION;
                            space=" ";
                        }

                        text=op+space+primarytText;

                        edittext.setText(text);
                        negative=0;
                    }

                }
            }
        });

        buttondot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    String primarytTextUp=edittextUp.getText().toString();
                    String spaceUp="  ";
                    if(primarytTextUp.startsWith(" "))spaceUp="";
                    String textUp=spaceUp+primarytTextUp + ".";

                    if(!primarytTextUp.contains("."))edittextUp.setText(textUp);

                }
                else if(edittext.hasFocus())
                {

                    String primarytText=edittext.getText().toString();

                    String op="";
                    String space="  ";

                    String text=primarytText;
                    infotextView.setText("  ");

                    if(CURRENT_ACTION!='n')
                    {
                        op=op+CURRENT_ACTION;
                        space=" ";
                    }


                    if(primarytText.charAt(1)==' ')
                    {
                        text=primarytText.substring(2);
                    }
                    else text=primarytText;


                    text=op+space+text + ".";

                    if(!primarytText.contains("."))edittext.setText(text);

                }
            }
        });

        buttonzero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    String primarytTextUp=edittextUp.getText().toString();
                    String spaceUp="  ";
                    if(primarytTextUp.startsWith(" "))spaceUp="";
                    String textUp=spaceUp+primarytTextUp + "0";

                    edittextUp.setText(textUp);

                }
                else if(edittext.hasFocus())
                {

                    String primarytText=edittext.getText().toString();

                    String op="";
                    String space="  ";

                    String text=primarytText;
                    infotextView.setText("  ");

                    if(CURRENT_ACTION!='n')
                    {
                        op=op+CURRENT_ACTION;
                        space=" ";
                    }


                    if(primarytText.charAt(1)==' ')
                    {
                        text=primarytText.substring(2);
                    }
                    else text=primarytText;


                    text=op+space+text + "0";

                    edittext.setText(text);

                }
            }
        });

        buttonone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    String primarytTextUp=edittextUp.getText().toString();
                    String spaceUp="  ";
                    if(primarytTextUp.startsWith(" "))spaceUp="";
                    String textUp=spaceUp+primarytTextUp + "1";

                    edittextUp.setText(textUp);

                }
                else if(edittext.hasFocus())
                {

                    String primarytText=edittext.getText().toString();

                    String op="";
                    String space="  ";

                    String text=primarytText;
                    infotextView.setText("  ");

                    if(CURRENT_ACTION!='n')
                    {
                        op=op+CURRENT_ACTION;
                        space=" ";
                    }


                    if(primarytText.charAt(1)==' ')
                    {
                        text=primarytText.substring(2);
                    }
                    else text=primarytText;


                    text=op+space+text + "1";

                    edittext.setText(text);

                }
            }
        });

        buttontwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    String primarytTextUp=edittextUp.getText().toString();
                    String spaceUp="  ";
                    if(primarytTextUp.startsWith(" "))spaceUp="";
                    String textUp=spaceUp+primarytTextUp + "2";

                    edittextUp.setText(textUp);

                }
                else if(edittext.hasFocus())
                {

                    String primarytText=edittext.getText().toString();

                    String op="";
                    String space="  ";

                    String text=primarytText;
                    infotextView.setText("  ");

                    if(CURRENT_ACTION!='n')
                    {
                        op=op+CURRENT_ACTION;
                        space=" ";
                    }


                    if(primarytText.charAt(1)==' ')
                    {
                        text=primarytText.substring(2);
                    }
                    else text=primarytText;


                    text=op+space+text + "2";

                    edittext.setText(text);

                }
            }
        });

        buttonthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    String primarytTextUp=edittextUp.getText().toString();
                    String spaceUp="  ";
                    if(primarytTextUp.startsWith(" "))spaceUp="";
                    String textUp=spaceUp+primarytTextUp + "3";

                    edittextUp.setText(textUp);

                }
                else if(edittext.hasFocus())
                {

                    String primarytText=edittext.getText().toString();

                    String op="";
                    String space="  ";

                    String text=primarytText;
                    infotextView.setText("  ");

                    if(CURRENT_ACTION!='n')
                    {
                        op=op+CURRENT_ACTION;
                        space=" ";
                    }


                    if(primarytText.charAt(1)==' ')
                    {
                        text=primarytText.substring(2);
                    }
                    else text=primarytText;


                    text=op+space+text + "3";

                    edittext.setText(text);

                }
            }
        });

        buttonfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    String primarytTextUp=edittextUp.getText().toString();
                    String spaceUp="  ";
                    if(primarytTextUp.startsWith(" "))spaceUp="";
                    String textUp=spaceUp+primarytTextUp + "4";

                    edittextUp.setText(textUp);

                }
                else if(edittext.hasFocus())
                {

                    String primarytText=edittext.getText().toString();

                    String op="";
                    String space="  ";

                    String text=primarytText;
                    infotextView.setText("  ");

                    if(CURRENT_ACTION!='n')
                    {
                        op=op+CURRENT_ACTION;
                        space=" ";
                    }


                    if(primarytText.charAt(1)==' ')
                    {
                        text=primarytText.substring(2);
                    }
                    else text=primarytText;


                    text=op+space+text + "4";

                    edittext.setText(text);

                }
            }
        });

        buttonfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    String primarytTextUp=edittextUp.getText().toString();
                    String spaceUp="  ";
                    if(primarytTextUp.startsWith(" "))spaceUp="";
                    String textUp=spaceUp+primarytTextUp + "5";

                    edittextUp.setText(textUp);

                }
                else if(edittext.hasFocus())
                {

                    String primarytText=edittext.getText().toString();

                    String op="";
                    String space="  ";

                    String text=primarytText;
                    infotextView.setText("  ");

                    if(CURRENT_ACTION!='n')
                    {
                        op=op+CURRENT_ACTION;
                        space=" ";
                    }


                    if(primarytText.charAt(1)==' ')
                    {
                        text=primarytText.substring(2);
                    }
                    else text=primarytText;


                    text=op+space+text + "5";

                    edittext.setText(text);

                }
            }
        });

        buttonsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    String primarytTextUp=edittextUp.getText().toString();
                    String spaceUp="  ";
                    if(primarytTextUp.startsWith(" "))spaceUp="";
                    String textUp=spaceUp+primarytTextUp + "6";

                    edittextUp.setText(textUp);

                }
                else if(edittext.hasFocus())
                {

                    String primarytText=edittext.getText().toString();

                    String op="";
                    String space="  ";

                    String text=primarytText;
                    infotextView.setText("  ");

                    if(CURRENT_ACTION!='n')
                    {
                        op=op+CURRENT_ACTION;
                        space=" ";
                    }


                    if(primarytText.charAt(1)==' ')
                    {
                        text=primarytText.substring(2);
                    }
                    else text=primarytText;


                    text=op+space+text + "6";

                    edittext.setText(text);

                }
            }
        });

        buttonseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    String primarytTextUp=edittextUp.getText().toString();
                    String spaceUp="  ";
                    if(primarytTextUp.startsWith(" "))spaceUp="";
                    String textUp=spaceUp+primarytTextUp + "7";

                    edittextUp.setText(textUp);

                }
                else if(edittext.hasFocus())
                {

                    String primarytText=edittext.getText().toString();

                    String op="";
                    String space="  ";

                    String text=primarytText;
                    infotextView.setText("  ");

                    if(CURRENT_ACTION!='n')
                    {
                        op=op+CURRENT_ACTION;
                        space=" ";
                    }


                    if(primarytText.charAt(1)==' ')
                    {
                        text=primarytText.substring(2);
                    }
                    else text=primarytText;


                    text=op+space+text + "7";

                    edittext.setText(text);

                }
            }
        });

        buttoneight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    String primarytTextUp=edittextUp.getText().toString();
                    String spaceUp="  ";
                    if(primarytTextUp.startsWith(" "))spaceUp="";
                    String textUp=spaceUp+primarytTextUp + "8";

                    edittextUp.setText(textUp);

                }
                else if(edittext.hasFocus())
                {

                    String primarytText=edittext.getText().toString();

                    String op="";
                    String space="  ";

                    String text=primarytText;
                    infotextView.setText("  ");

                    if(CURRENT_ACTION!='n')
                    {
                        op=op+CURRENT_ACTION;
                        space=" ";
                    }


                    if(primarytText.charAt(1)==' ')
                    {
                        text=primarytText.substring(2);
                    }
                    else text=primarytText;


                    text=op+space+text + "8";

                    edittext.setText(text);

                }
            }
        });

        buttonnine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    String primarytTextUp=edittextUp.getText().toString();
                    String spaceUp="  ";
                    if(primarytTextUp.startsWith(" "))spaceUp="";
                    String textUp=spaceUp+primarytTextUp + "9";

                    edittextUp.setText(textUp);

                }
                else if(edittext.hasFocus())
                {

                    String primarytText=edittext.getText().toString();

                    String op="";
                    String space="  ";

                    String text=primarytText;
                    infotextView.setText("  ");

                    if(CURRENT_ACTION!='n')
                    {
                        op=op+CURRENT_ACTION;
                        space=" ";
                    }


                    if(primarytText.charAt(1)==' ')
                    {
                        text=primarytText.substring(2);
                    }
                    else text=primarytText;


                    text=op+space+text + "9";

                    edittext.setText(text);

                }
            }
        });

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String primarytText=edittext.getText().toString();

                CURRENT_ACTION = ADDITION;
                String op="";
                op=op+CURRENT_ACTION;
                String text=op+" "+primarytText.substring(2);

                edittext.setText(text);

                infotextView.setText("  ");
            }
        });

        buttonsubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String primarytText=edittext.getText().toString();

                CURRENT_ACTION = SUBTRACTION;
                String op="";

                op=op+CURRENT_ACTION;
                edittext.setText(op+" "+primarytText.substring(2));
                infotextView.setText("  ");
            }
        });

        buttonmultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String primarytText=edittext.getText().toString();

                CURRENT_ACTION = MULTIPLICATION;
                String op="";

                op=op+CURRENT_ACTION;
                edittext.setText(op+" "+primarytText.substring(2));
                infotextView.setText("  ");
            }
        });

        buttondivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String primarytText=edittext.getText().toString();

                CURRENT_ACTION = DIVISION;
                String op="";

                op=op+CURRENT_ACTION;
                edittext.setText(op+" "+primarytText.substring(2));
                infotextView.setText("  ");
            }
        });

        buttonequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String primarytTextUp=edittextUp.getText().toString();
                String primarytText=edittext.getText().toString();

                if(CURRENT_ACTION!='n' && primarytTextUp.length()>2 && primarytText.length()>2)computeCalculation();




                String operation="";
                infotextView.setText("  ");

                if(CURRENT_ACTION==ADDITION)
                {
                    operation=operation+"+";
                }

                if(CURRENT_ACTION==SUBTRACTION)
                {
                    operation=operation+"-";
                }

                if(CURRENT_ACTION==MULTIPLICATION)
                {
                    operation=operation+"*";
                }

                if(CURRENT_ACTION==DIVISION)
                {
                    operation=operation+"/";
                }

                if (CURRENT_ACTION=='n'|| primarytTextUp.length()<3 || primarytText.length()<3);
                else if(!operation.equals("/")  || valueTwo!=0.0)
                {
                    infotextView.setText(primarytTextUp.substring(2) + operation +primarytText.substring(2)+"\n"+"="+result );
                }
                else
                {
                    infotextView.setText("Math Error");
                }
                valueOne=0.0;
                valueTwo=0.0;
                edittextUp.setText("  ");
                edittext.setText("  ");
                CURRENT_ACTION='n';

                edittext.clearFocus();
                edittextUp.clearFocus();

            }
        });

        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CURRENT_ACTION='n';
                valueOne = 0.0;
                valueTwo =  0.0;
                edittextUp.setText("  ");
                edittext.setText("  ");
                infotextView.setText("  ");

            }
        });
        buttoncross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittextUp.hasFocus())
                {
                    String primarytTextUp=edittextUp.getText().toString();

                    if(primarytTextUp.length()>2)
                    {
                        edittextUp.setText(primarytTextUp.substring(0,primarytTextUp.length()-1));
                    }

                }
                else if(edittext.hasFocus())
                {
                    String primarytText=edittext.getText().toString();
                    if(primarytText.length()>2)
                    {
                        edittext.setText(primarytText.substring(0,primarytText.length()-1));
                    }

                }

                infotextView.setText("  ");
            }
        });
    }

    private void displayExpression() {

        Intent intent = new Intent(this, ExpressionActivity.class);
        this.startActivity(intent);
    }

    private void computeCalculation() {

        valueOne=  Double.parseDouble(edittextUp.getText().toString().substring(2));
        valueTwo = Double.parseDouble(edittext.getText().toString().substring(2));

        result=0.0;

        if(CURRENT_ACTION == ADDITION)
            result = this.valueOne + valueTwo;
        else if(CURRENT_ACTION == SUBTRACTION)
            result = this.valueOne - valueTwo;
        else if(CURRENT_ACTION == MULTIPLICATION)
            result = this.valueOne * valueTwo;
        else if(CURRENT_ACTION == DIVISION)
        {
            if(valueTwo!=0.0)result = this.valueOne / valueTwo;
        }

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


    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            try
            {
                FileOutputStream fileout=new FileOutputStream(new File("/data/data/com.example.omnia.MyCalculator/mytextfile.txt"));
                OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                outputWriter.write(String.valueOf(sharedText));
                outputWriter.close();
                fileout.close();
            }
            catch(Exception e)
            {

            }
            // Update UI to reflect text being shared
        }
    }

}

