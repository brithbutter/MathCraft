package com.example.mac.mathcraft;

import android.net.Uri;
import android.app.Service;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.ImageView;
import android.media.Image;
import android.view.animation.*;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    ImageView image;
    Button[] optionButtons;
   // Button[] answerButtons;
    int questionAnswer;
    Button previousButton;
    Button nextButton;
    TextView formula;
    ImageView element1;
    ImageView element2;
    ImageView notation;
    ImageView equals;
    FlyAnimation FlyElement1;
    FlyAnimation FlyElement2;
    int number1;
    int number2;
    String note;
    Handler handler = new Handler();
    int noteFlag;
    Question ques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteFlag=0;
        ques = new Question(1);// Establish a QuestionList in size 1
        ques.setQues();//generate the list
        number1= this.getResources().getInteger((int)ques.queV1.get(0));
        number2= this.getResources().getInteger((int)ques.queV2.get(0));
        //dirctly get quesiton parameter


        formula=(TextView) findViewById(R.id.textbasket);
        element1=(ImageView) findViewById(R.id.imageView2);
        element2=(ImageView) findViewById(R.id.imageView4);
        notation=(ImageView)findViewById(R.id.imageView3);
        equals=(ImageView)findViewById(R.id.imageView5);

        note=switchNotation(noteFlag);
        questionAnswer=calculateAnswer(noteFlag);

        setImageAccordingToNumber(number1, number2);
//        System.out.println("*"+number1);
//        System.out.println("#"+number2);
//        System.out.println(questionAnswer);
       // System.out.println(this.getResources().getInteger(para1));//this R.integer.Q11is stored in list of Question.

      //Question quetion();
        TextView tex = (TextView) findViewById(R.id.textView);
        note="plus";

        //get all option buttons
        optionButtons = new Button[4];
        optionButtons[0] = (Button) findViewById(R.id.imageButton);
        optionButtons[1] = (Button) findViewById(R.id.imageButton2);
        optionButtons[2] = (Button) findViewById(R.id.imageButton3);
        optionButtons[3] = (Button) findViewById(R.id.imageButton4);
//
//        answerButtons  = new Button[4];
//        answerButtons[0] = (Button) findViewById(R.id.imageButtonback);
//        answerButtons[1] = (Button) findViewById(R.id.imageButton2back);
//        answerButtons[2] = (Button) findViewById(R.id.imageButton3back);
//        answerButtons[3] = (Button) findViewById(R.id.imageButton4back);
        //get Question answer from xml;here is just a test number
        //questionAnswer = 9;

        previousButton=(Button)findViewById(R.id.button);
        nextButton=(Button)findViewById(R.id.button2);
        nextButton.setEnabled(false);

        displayImage(questionAnswer);
//        optionButtons[1].setBackgroundResource(R.drawable.apple3);
//        optionButtons[1].setText(String.valueOf(3));

        optionButtons[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean flag=checkAnswer(questionAnswer, optionButtons[0]);
                if(flag){
                    rightFeedback(questionAnswer, optionButtons[0]);
                }
                else{
                    wrongFeedback(optionButtons[0]);
                }
            }
        });
        optionButtons[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean flag=checkAnswer(questionAnswer,optionButtons[1] );
                if(flag){
                    rightFeedback(questionAnswer,optionButtons[1]);
                }
                else{
                    wrongFeedback(optionButtons[1]);
                }
            }
        });
        optionButtons[2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean flag=checkAnswer(questionAnswer, optionButtons[2]);
                if(flag){
                    rightFeedback(questionAnswer,  optionButtons[2]);
                }
                else{
                    wrongFeedback( optionButtons[2]);
                }
            }
        });
        optionButtons[3].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean flag=checkAnswer(questionAnswer, optionButtons[3]);
                if(flag){
                    rightFeedback(questionAnswer, optionButtons[3]);
                }
                else{
                    wrongFeedback( optionButtons[3]);
                }
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //switchImage();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i=0;i<4;i++) {
                    optionButtons[i].setBackgroundColor(Color.TRANSPARENT);
                }
                notation.setBackgroundColor(Color.TRANSPARENT);
                formula.setVisibility(View.INVISIBLE);
                //add I/O code for these 3 lines!!!!!!!!!!
                //note="plus";
                //number1=1;
                //number2=1;
                noteFlag=noteFlag+1;
                ques = new Question(1);
                ques.setQues();
                ques.getQes();

                number1=getResources().getInteger(ques.quev1);
                System.out.println("*"+number1);
                number2=getResources().getInteger(ques.quev2);
                System.out.println("*"+number2);

                note=switchNotation(noteFlag);
                System.out.println(note);
                questionAnswer=calculateAnswer(noteFlag);
                System.out.println("*"+questionAnswer);


                //notation.setImageResource(R.drawable.plus);
                setImageAccordingToNumber(number1, number2);
                //element1.setImageResource(R.drawable.apple1);
                //element2.setImageResource(R.drawable.apple1);
                element1.setTranslationY(-300f);
                element2.setTranslationY(-300f);
                notation.setVisibility(View.VISIBLE);
                equals.setVisibility(View.VISIBLE);


                displayImage(questionAnswer);
                //add set all image background transparent

                handler.postDelayed(new Runnable() {
                    public void run() {
                        playInstructionSound(number1);
                    }
                }, (long) 600);
                handler.postDelayed(new Runnable() {
                    public void run() {
                        playInstructionNotationSound(note);
                    }
                }, (long) 1200);
                handler.postDelayed(new Runnable() {
                    public void run() {
                        playInstructionSound(number2);
                    }
                }, (long) 1800);
                handler.postDelayed(new Runnable() {
                    public void run() {
                        playInstructionNotationSound("equals");
                    }
                }, (long) 2400);

            }
        });


        FlyElement1=new FlyAnimation(element1,1);
        FlyElement2=new FlyAnimation(element2,2);
       // number1=1;
       // number2=2;
        //playAlertSound(R.raw.dong);
       // playAlertSound(R.raw.beep);

        handler.postDelayed(new Runnable() {
            public void run() {
                playInstructionSound(number1);
            }
        }, (long) 600);
        handler.postDelayed(new Runnable() {
            public void run() {
                playInstructionNotationSound(note);
            }
        }, (long) 1200);
        handler.postDelayed(new Runnable() {
            public void run() {
                playInstructionSound(number2);
            }
        }, (long) 1800);
        handler.postDelayed(new Runnable() {
            public void run() {
                playInstructionNotationSound("equals");
            }
        }, (long) 2400);
    }
    public int getRandomWithExclusion(Random rnd, int start, int end, int... exclude) {
        int random = start + rnd.nextInt(end - start + 1 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }
    //randomlly assign four option images to the screen
    public void displayImage(int answer) {


        //generating 4 distinct numbers in the array in the range of 0 to 9
        int[] randomNumbers = new int[4];
        //generate 4 distinct numbers as the options index, this is used to
        int[] fournumbers = new int[4];
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        //range from 0 to 9
        for (int i = 0; i < 10; i++) {

            list.add(new Integer(i));
        }

        //range from 0 to 3
        for (int i = 0; i < 4; i++) {

            list2.add(new Integer(i));
        }

        //shuffle the collection
        Collections.shuffle(list);
        Collections.shuffle(list2);

        System.out.println(list.toString() + " " + list.size());

        //answer -1, as the array index is 1 lower than the  answer
        list.remove(new Integer(answer-1));
        System.out.println("answer is "+ answer + " "+list.toString() + " " + list.size());

        //extract 4 digit from the list to the array
        for (int i = 0; i < 4; i++) {

            randomNumbers[i] = list.get(i);
            System.out.println(" Random numbers" + randomNumbers[i] + " ");
            fournumbers[i] = list2.get(i);
            System.out.println(list2.toString() + " " + list2.size());
        }


        //generating four options, 1 is the question answer, the other 3 are the randomly generated digits
        for(int i = 0; i < 4; i ++){

            switch(randomNumbers[i])
            {

                case 0:
                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
                    optionButtons[fournumbers[i]].setBackgroundResource(R.drawable.apple1);
                    optionButtons[fournumbers[i]].setText(String.valueOf(1));
                    System.out.println("Assign options"+randomNumbers[i] +"  "+fournumbers[i]);

                    break;

                case 1:
                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
                    optionButtons[fournumbers[i]].setBackgroundResource(R.drawable.apple2);
                    optionButtons[fournumbers[i]].setText(String.valueOf(2));
                    System.out.println("Assign options"+randomNumbers[i] +"  "+fournumbers[i]);
                    break;

                case 2:
                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
                    optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple3);
                    optionButtons[fournumbers[i] ].setText(String.valueOf(3));
                    System.out.println("Assign options"+randomNumbers[i] +"  "+fournumbers[i]);
                    break;

                case 3:
                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
                    optionButtons[fournumbers[i]].setBackgroundResource(R.drawable.apple4);
                    optionButtons[fournumbers[i] ].setText(String.valueOf(4));
                    System.out.println("Assign options"+randomNumbers[i] +"  "+fournumbers[i]);
                    break;
                case 4:
                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
                    optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple5);
                    optionButtons[fournumbers[i] ].setText(String.valueOf(5));
                    System.out.println("Assign options"+randomNumbers[i] +"  "+fournumbers[i]);
                    break;
                case 5:
                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
                    optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple6);
                    optionButtons[fournumbers[i] ].setText(String.valueOf(6));
                    System.out.println("Assign options"+randomNumbers[i] +"  "+fournumbers[i]);
                    break;
                case 6:
                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
                    optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple7);
                    optionButtons[fournumbers[i] ].setText(String.valueOf(7));
                    System.out.println("Assign options"+randomNumbers[i] +"  "+fournumbers[i]);
                    break;
                case 7:
                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
                    optionButtons[fournumbers[i]].setBackgroundResource(R.drawable.apple8);
                    optionButtons[fournumbers[i]].setText(String.valueOf(8));
                    System.out.println("Assign options"+randomNumbers[i] +"  "+fournumbers[i]);
                    break;
                case 8:
                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
                    optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple9);
                    optionButtons[fournumbers[i] ].setText(String.valueOf(9));
                    System.out.println("Assign options"+randomNumbers[i] +"  "+fournumbers[i]);
                    break;
                case 9:
                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
                    optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple10);
                    optionButtons[fournumbers[i] ].setText(String.valueOf(10));
                    System.out.println("Assign options"+randomNumbers[i] +"  "+fournumbers[i]);
                    break;

            }//end switch
        }//end for each

        //Reassign question answer to a image
        switch(answer)
        {
            case 1:
                optionButtons[fournumbers[0]].setBackgroundResource(R.drawable.apple1);
                optionButtons[fournumbers[0]].setText(String.valueOf(1));
                System.out.println("Assign answer"+fournumbers[0] +" " + String.valueOf(1));
                break;

            case 2:
                optionButtons[fournumbers[0]].setBackgroundResource(R.drawable.apple2);
                optionButtons[fournumbers[0]].setText(String.valueOf(2));
                System.out.println("Assign answer"+fournumbers[0] +" " + String.valueOf(2));
                break;

            case 3:
                optionButtons[fournumbers[0]].setBackgroundResource(R.drawable.apple3);
                optionButtons[fournumbers[0]].setText(String.valueOf(3));
                System.out.println("Assign answer"+fournumbers[0] +" " + String.valueOf(3));

                break;
            case 4:
                optionButtons[fournumbers[0]].setBackgroundResource(R.drawable.apple4);
                optionButtons[fournumbers[0]].setText(String.valueOf(4));
                System.out.println("Assign answer"+fournumbers[0] +" " + String.valueOf(4));
                break;
            case 5:
                optionButtons[fournumbers[0]].setBackgroundResource(R.drawable.apple5);
                optionButtons[fournumbers[0]].setText(String.valueOf(5));
                System.out.println("Assign answer"+fournumbers[0] +" " + String.valueOf(5));
                break;
            case 6:
                optionButtons[fournumbers[0]].setBackgroundResource(R.drawable.apple6);
                optionButtons[fournumbers[0]].setText(String.valueOf(6));
                System.out.println("Assign answer"+fournumbers[0] +" " + String.valueOf(6));
                break;
            case 7:
                optionButtons[fournumbers[0]].setBackgroundResource(R.drawable.apple7);
                optionButtons[fournumbers[0]].setText(String.valueOf(7));
                System.out.println("Assign answer "+fournumbers[0] +" " + String.valueOf(7));
                break;
            case 8:
                optionButtons[fournumbers[0]].setBackgroundResource(R.drawable.apple8);
                optionButtons[fournumbers[0]].setText(String.valueOf(8));
                System.out.println("Assign answer"+fournumbers[0] +" " + String.valueOf(8));
                break;
            case 9:
                optionButtons[fournumbers[0]].setBackgroundResource(R.drawable.apple9);
                optionButtons[fournumbers[0]].setText(String.valueOf(9));
                System.out.println("Assign answer"+fournumbers[0] +" " + String.valueOf(9));
                break;

            case 10:
                optionButtons[fournumbers[0]].setBackgroundResource(R.drawable.apple10);
                optionButtons[fournumbers[0]].setText(String.valueOf(10));
                System.out.println("Assign answer"+fournumbers[0] +" " + String.valueOf(10));

                break;
        }//end switch
    }


//    //randomly assign four option images to the screen
//    public void displayImage() {
//
//        Random rnd = new Random();
//        //generating 4 distinct numbers in the array in the range of 0 to 9
//        int[] randomNumbers = new int[4];
//        int[] fournumbers = new int[4];
////        for (int i = 0; i < 4; i++) {
////
////            randomNumbers[i] = getRandomWithExclusion(rnd, 0,9,8);
////        }
////
//        ArrayList<Integer> list = new ArrayList<>();
//
//        ArrayList<Integer> list2 = new ArrayList<>();
//
//        //range of 0 to 9
//        for (int i = 0; i < 10; i++) {
//
//            list.add(new Integer(i));
//        }
//
//        //range of 0 to 3
//        for (int i = 0; i < 4; i++) {
//
//            list2.add(new Integer(i));
//        }
//
//        Collections.shuffle(list);
//        Collections.shuffle(list2);
//
//        System.out.println(list.toString() + " " + list.size());
//
//        int index = questionAnswer-1;
//            list.remove(new Integer(questionAnswer-1));
//            System.out.println(list.toString() + " " + list.size());
//
//        for (int i = 0; i < 4; i++) {
//
//            randomNumbers[i] = list.get(i);
//
//        }
//        for (int i = 0; i < 4; i++) {
//
//            fournumbers[i] = list2.get(i);
//
//        }
//
//
//        //generating four options, 1 is the question answer, the other 3 are the randomly generated digits
//        for(int i = 0; i < 4; i ++){
//
//            //System.out.println(randomNumbers[i] +"  "+fournumbers[i]);
//
//           // if (randomNumbers[i] + 1 != questionAnswer) {
//
//                switch(randomNumbers[i])
//                {
//
//                case 0:
//                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
//                    optionButtons[fournumbers[i]].setBackgroundResource(R.drawable.apple1);
//                    optionButtons[fournumbers[i]].setText(String.valueOf(1));
//                    System.out.println(randomNumbers[i] +"  "+fournumbers[i]);
//
//                    break;
//
//                case 1:
//                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
//                    optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple2);
//                    optionButtons[fournumbers[i] ].setText(String.valueOf(2));
//                    System.out.println(randomNumbers[i] +"  "+fournumbers[i]);
//                    break;
//
//                case 2:
//                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
//                    optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple3);
//                    optionButtons[fournumbers[i] ].setText(String.valueOf(3));
//                    System.out.println(randomNumbers[i] +"  "+fournumbers[i]);
//                    break;
//
//                case 3:
//                    optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
//                    optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple4);
//                    optionButtons[fournumbers[i] ].setText(String.valueOf(4));
//                    System.out.println(randomNumbers[i] +"  "+fournumbers[i]);
//                    break;
//                    case 4:
//                        optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
//                        optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple5);
//                        optionButtons[fournumbers[i] ].setText(String.valueOf(5));
//                        System.out.println(randomNumbers[i] +"  "+fournumbers[i]);
//                        break;
//                    case 5:
//                        optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
//                        optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple6);
//                        optionButtons[fournumbers[i] ].setText(String.valueOf(6));
//                        System.out.println(randomNumbers[i] +"  "+fournumbers[i]);
//                        break;
//                    case 6:
//                        optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
//                        optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple7);
//                        optionButtons[fournumbers[i] ].setText(String.valueOf(7));
//                        System.out.println(randomNumbers[i] +"  "+fournumbers[i]);
//                        break;
//                    case 7:
//                        optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
//                        optionButtons[fournumbers[i]].setBackgroundResource(R.drawable.apple8);
//                        optionButtons[fournumbers[i]].setText(String.valueOf(8));
//                        System.out.println(randomNumbers[i] +"  "+fournumbers[i]);
//                        break;
//                    case 8:
//                        optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
//                        optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple9);
//                        optionButtons[fournumbers[i] ].setText(String.valueOf(9));
//                        System.out.println(randomNumbers[i] +"  "+fournumbers[i]);
//                        break;
//                    case 9:
//                        optionButtons[fournumbers[i]].setBackgroundColor(Color.TRANSPARENT);
//                        optionButtons[fournumbers[i] ].setBackgroundResource(R.drawable.apple10);
//                        optionButtons[fournumbers[i] ].setText(String.valueOf(10));
//                        break;
//
//            }//end switch
//
//
//
//           // }//end if
//
//
//        }//end for each
//
//        optionButtons[fournumbers[0]].setBackgroundResource(R.drawable.apple9);
//        optionButtons[fournumbers[0]].setText(String.valueOf(9));
//        System.out.println("  "+fournumbers[0] +" " + String.valueOf(9));
//
//    }

    //switch the initial apple image
//    public void switchImage() {
//
//        //switch the number of the apple image
//
//        displayImage();
//
//    }

    public void rightFeedback(int answer,Button button){
        button.setBackgroundColor(Color.GREEN);
        playAlertSound(R.raw.dong);
        nextButton.setEnabled(true);
        notation.setVisibility(View.INVISIBLE);
        equals.setVisibility(View.INVISIBLE);
        FlyElement1.startFly();
        FlyElement2.startFly();
        formula.setText("1+2=3");
        handler.postDelayed(new Runnable() {
            public void run() {
                playInstructionSound(number1);
            }
        }, (long) 600);
        handler.postDelayed(new Runnable() {
            public void run() {
                playInstructionNotationSound("plus");
            }
        }, (long) 1200);
        handler.postDelayed(new Runnable() {
            public void run() {
                playInstructionSound(number2);
            }
        }, (long) 1800);
        handler.postDelayed(new Runnable() {
            public void run() {
                playInstructionNotationSound("equals");
            }
        }, (long) 2400);
        handler.postDelayed(new Runnable() {
            public void run() {
                playInstructionSound(questionAnswer);
            }
        }, (long) 3000);

    }
    public void wrongFeedback(Button button){
        button.setBackgroundColor(Color.RED);
        playAlertSound(R.raw.beep);

    }
    public void playAlertSound(int sound) {

        MediaPlayer mp = MediaPlayer.create(getBaseContext(), sound);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }
    public boolean checkAnswer(int answer, Button button){

        boolean check;
        int text = Integer.parseInt(button.getText().toString());

        if(text == answer){

            check=true;
        }
        else{
            check=false;
        }
        return check;
    }

    public void playInstructionSound(int num){
        switch (num){

            case 1:  playAlertSound(R.raw.one);
                break;
            case 2:  playAlertSound(R.raw.two);
                break;
            case 3:  playAlertSound(R.raw.three);
                break;
            case 4:  playAlertSound(R.raw.four);
                break;
            case 5:  playAlertSound(R.raw.five);
                break;
            case 6:  playAlertSound(R.raw.six);
                break;
            case 7:  playAlertSound(R.raw.seven);
                break;
            case 8:  playAlertSound(R.raw.eight);
                break;
            case 9:  playAlertSound(R.raw.nine);
                break;
            case 10: playAlertSound(R.raw.ten);
                break;
        }

    }
    public void playInstructionNotationSound(String note) {

        switch (note) {

            case "plus":
                playAlertSound(R.raw.plus);
                break;
            case "minus":
                playAlertSound(R.raw.minus);
                break;
            case "multiply":
                playAlertSound(R.raw.multiply);
                break;
            case "divide":
                playAlertSound(R.raw.divide);
                break;
            case "equals":
                playAlertSound(R.raw.equals);
                break;

        }

    }

    public String switchNotation(int flag){
        String textnote=" ";
        switch (flag){
            case 0: textnote="plus";
                notation.setBackgroundResource(R.drawable.plus);
                //questionAnswer=number1+number2;
                break;
            case 1: textnote="minus";
                notation.setBackgroundResource(R.drawable.minus);
                //questionAnswer=number1-number2;
                break;
            case 2: textnote="multiply";
                notation.setBackgroundResource(R.drawable.multiply);
                //questionAnswer=number1*number2;
                break;
            case 3: textnote="divide";
                notation.setBackgroundResource(R.drawable.divide);
                //questionAnswer=number1/number2;
                break;
        }
        return textnote;
    }

    public int calculateAnswer(int flag){
        int answer=0;
        switch (flag){
            case 0: answer=number1+number2;
                break;
            case 1: answer=number1-number2;
                break;
            case 2: answer=number1*number2;
                break;
            case 3: answer=number1/number2;
                break;
        }
        return answer;
    }

    public void setImageAccordingToNumber(int num1, int num2){
        switch (num1){
            case 1: element1.setImageResource(R.drawable.apple1);
                break;
            case 2: element1.setImageResource(R.drawable.apple2);
                break;
            case 3: element1.setImageResource(R.drawable.apple3);
                break;
            case 4: element1.setImageResource(R.drawable.apple4);
                break;
            case 5: element1.setImageResource(R.drawable.apple5);
                break;
            case 6: element1.setImageResource(R.drawable.apple6);
                break;
            case 7: element1.setImageResource(R.drawable.apple7);
                break;
            case 8: element1.setImageResource(R.drawable.apple8);
                break;
            case 9: element1.setImageResource(R.drawable.apple9);
                break;
            case 10: element1.setImageResource(R.drawable.apple10);
                break;
        }
        switch (num2){
            case 1: element2.setImageResource(R.drawable.apple1);
                break;
            case 2: element2.setImageResource(R.drawable.apple2);
                break;
            case 3: element2.setImageResource(R.drawable.apple3);
                break;
            case 4: element2.setImageResource(R.drawable.apple4);
                break;
            case 5: element2.setImageResource(R.drawable.apple5);
                break;
            case 6: element2.setImageResource(R.drawable.apple6);
                break;
            case 7: element2.setImageResource(R.drawable.apple7);
                break;
            case 8: element2.setImageResource(R.drawable.apple8);
                break;
            case 9: element2.setImageResource(R.drawable.apple9);
                break;
            case 10: element2.setImageResource(R.drawable.apple10);
                break;
        }
    }
}
