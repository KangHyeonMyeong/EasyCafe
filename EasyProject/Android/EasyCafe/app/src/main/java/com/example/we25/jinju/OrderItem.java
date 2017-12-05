package com.example.we25.jinju;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by com on 2017-10-26.
 */

public class OrderItem implements Serializable {

    String order_date;
    String order_status;
    String order_content;

    public OrderItem(String order_date, String order_status, String order_content) {
        this.order_date = order_date;
        this.order_status = order_status;
        this.order_content = order_content;
    }


    public String getOrder_date() {
        return order_date;
    }


    public String getOrder_status() {
        return order_status;
    }


    public String getOrder_content() {
        return order_content;
    }
}

