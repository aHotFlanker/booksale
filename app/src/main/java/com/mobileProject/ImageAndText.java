package com.mobileProject;

public class ImageAndText {
    private int imgId;
    private String txt;
    public ImageAndText(String txt,int imgId){
        this.txt = txt;
        this.imgId= imgId;
    }
    public int getImgId(){
        return imgId;
    }
    public String getTxt(){
        return txt;
    }
}
