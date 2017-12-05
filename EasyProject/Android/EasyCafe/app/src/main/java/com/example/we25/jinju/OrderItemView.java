package com.example.we25.jinju;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.we25.easycafe.R;

/**
 * Created by com on 2017-11-13.
 */

public class OrderItemView extends LinearLayout {
    TextView orderNum;
    TextView orderDate;
    TextView orderStatus;


    public OrderItemView(Context context) {
        super(context);
        init(context);
    }


    public OrderItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.order_list_item,this,true);

        orderNum = (TextView)findViewById(R.id.orderNum);
        orderDate = (TextView)findViewById(R.id.orderDate);
        orderStatus = (TextView)findViewById(R.id.orderStatus);

    }

    public void setOrderNum(String orderNum) {
        this.orderNum.setText(orderNum);
    }

    public void setOrderDate(String orderDate) {
        this.orderDate.setText(orderDate);

    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus.setText(orderStatus);
    }





}
