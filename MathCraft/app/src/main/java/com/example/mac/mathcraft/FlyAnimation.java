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

    public FlyAnimation(ImageView image, int imageNumber, int imageIndex){
        currentImage=image;
        endPosition=1100/imageNumber*imageIndex;
    }
    void startFly(){
        TranslateAnimation animation = new TranslateAnimation(0, endPosition, 0, 500);
        animation.setDuration(1000);
        animation.setFillAfter(false);
        currentImage.startAnimation(animation);
    }

}
