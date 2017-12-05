package com.example.we25.jinju;

import android.view.Menu;

import com.example.we25.easycafe.R;
import java.io.Serializable;

/**
 * Created by we25 on 2017-06-28.
 */

//메뉴 이미지, 가격 HS , 가격 HL, 가격 IS, 가격 IL, 메뉴 상세정보

public class MenuData implements Serializable {
    private int image;
    private String price;
    private String price2;
    private String price3;
    private String price4;
    private String content;


    public MenuData(int image, String price, String price2, String price3, String price4, String content) {
        this.image = image;
        this.price = price;
        this.price2 = price2;
        this.price3 = price3;
        this.price4 = price4;

        this.content = content;

    }

    public MenuData() {}


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public String getPrice3() {
        return price3;
    }

    public void setPrice3(String price3) {
        this.price3 = price3;
    }

    public String getPrice4() {
        return price4;
    }

    public void setPrice4(String price4) {
        this.price4 = price4;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

