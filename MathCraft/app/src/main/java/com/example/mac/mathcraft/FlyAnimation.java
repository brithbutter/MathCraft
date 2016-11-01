package com.example.mac.mathcraft;

import android.widget.ImageView;
import android.view.animation.TranslateAnimation;
/**
 * Created by mac on 16/10/29.
 */
public class FlyAnimation {
    ImageView currentImage;
    int duration;
    int endPosition;

    public FlyAnimation(ImageView image, int imageNumber){
        currentImage=image;
        endPosition=imageNumber*300;
    }
    void startFly(){
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 300);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        currentImage.startAnimation(animation);
    }

}
