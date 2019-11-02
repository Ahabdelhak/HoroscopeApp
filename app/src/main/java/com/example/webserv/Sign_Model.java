package com.example.webserv;

/**
 * Created by ah_abdelhak on 11/1/2019.
 */

public class Sign_Model {

    private String sign;
    private String date;
    private int imgId;
    private String sign_eng_name;

    public Sign_Model(String sign, String date, int imgId, String sign_eng_name) {
        this.sign = sign;
        this.date = date;
        this.imgId = imgId;
        this.sign_eng_name=sign_eng_name;
    }

    public Sign_Model() {
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getSign_eng_name() {
        return sign_eng_name;
    }

    public void setSign_eng_name(String sign_eng_name) {
        this.sign_eng_name = sign_eng_name;
    }
}
