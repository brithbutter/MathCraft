package com.example.mac.mathcraft;

import android.util.Xml;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static android.R.attr.tint;
import static android.R.id.list;

/**
 * Created by 潼关夜 on 2016/10/30.
 */



public class Question {
    public List question = new ArrayList();
    public int [] Option =new int [4];
    public List quesmark = new ArrayList();
    public List AnswerId = new ArrayList();
    // suppose we will need two integer values to demo question
    public List queV1 = new ArrayList();
    public List queV2 = new ArrayList();
    int quev1;
    int quev2;
    int size;
    public Question(){
        question.add(1);
        size=1;
        //AnswerId = new int[1];
    }
    public Question(int n){

        //AnswerId = new int[n];
        for (int i=0; i<n; i++){
            question.add((Integer)(i+1));

        }
        size = n;
    }
    public void getQes(){
        //text.setText((int) this.quesmark.get(0));
        quev1=(int)this.queV1.get(0);
        quev2=(int)this.queV2.get(0);
        this.queV1.remove(0);
        this.queV2.remove(0);
    }
    public void getAns(Button mybut){

    }
    public void setQues(){
        Integer mark =(int)Math.random()*this.size;
        if (!this.question.contains(mark)){
            quesmark.add((Integer)(R.string.Q0+mark));
            AnswerId.add((Integer)(R.integer.AS1+mark));
            queV1.add(R.integer.Q00+10*mark+1);
            queV2.add(R.integer.Q00+10*mark+2);
            for (int i =0; i<4; i++){
                this.Option[i]=R.string.A1_0+mark;
            }
            this.question.remove(mark);
        }else{
            System.out.println("error");
        }
    }
}
