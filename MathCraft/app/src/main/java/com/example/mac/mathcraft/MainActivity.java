package com.example.mac.mathcraft;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image=(ImageView) findViewById(R.id.imageView2);
        FlyAnimation test=new FlyAnimation(image,3,1);
        test.startFly();
        //Question quetion();
        TextView tex = (TextView) findViewById(R.id.textView);

        //System.out.println(que.question);
    }
}
