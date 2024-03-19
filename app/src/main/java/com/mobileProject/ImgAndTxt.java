package com.mobileProject;

public class ImgAndTxt {
    private String txt;
    private int imgId;

    public ImgAndTxt(String txt, int imgId) {

        this.imgId = imgId;
        this.txt = txt;

    }

    public String getTxt() {
        return txt;
    }

    public int getImgId() {
        return imgId;
    }

}

