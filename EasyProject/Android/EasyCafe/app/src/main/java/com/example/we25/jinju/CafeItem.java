package com.example.we25.jinju;

import com.example.we25.easycafe.R;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-06-25.
 */

public class CafeItem implements Serializable {
    private String menu_image;
    private String menu_name;
    private String menu_category;
    private String menu_info;
    private String price_HS;
    private String price_HL;
    private String price_IS;
    private String price_IL;

    public CafeItem(String menu_image, String menu_name, String menu_category, String menu_info, String price_HS, String price_HL, String price_IS, String price_IL) {
        this.menu_image = menu_image;
        this.menu_name = menu_name;
        this.menu_category = menu_category;
        this.menu_info = menu_info;
        this.price_HS = price_HS;
        this.price_HL = price_HL;
        this.price_IS = price_IS;
        this.price_IL = price_IL;
    }

    public String getImage() {
        return menu_image;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public String getMenu_category() {
        return menu_category;
    }

    public String getMenu_info() {
        return menu_info;
    }

    public String getPrice_HS() {
        return price_HS;
    }

    public String getPrice_HL() {
        return price_HL;
    }

    public String getPrice_IS() {
        return price_IS;
    }

    public String getPrice_IL() {
        return price_IL;
    }
}
