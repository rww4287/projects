package com.ktds.ramentimer.model;

import android.graphics.drawable.Drawable;



public class Ramen {

    private String ramenName;
    private Drawable img;
    private int time;

    public String getRamenName() {
        return ramenName;
    }

    public void setRamenName(String ramenName) {
        this.ramenName = ramenName;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
