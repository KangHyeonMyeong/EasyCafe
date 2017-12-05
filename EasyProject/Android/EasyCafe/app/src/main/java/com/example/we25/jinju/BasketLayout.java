package com.example.we25.jinju;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.we25.easycafe.R;

/**
 * Created by we25 on 2017-07-04.
 */

public class BasketLayout extends LinearLayout {
    TextView menuName, info, num , price;
    public BasketLayout(Context context) {
        super(context);
        init(context);
    }

    public BasketLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.basket_item, this);

        menuName = (TextView) findViewById(R.id.coffeeName);
        info = (TextView) findViewById(R.id.hot_size_price);
        num = (TextView) findViewById(R.id.quantity);
        price = (TextView) findViewById(R.id.total_price);
    }

    public void setMenuName(String menuName) {
        this.menuName.setText(menuName);
    }

    public void setInfo(String info) {
        this.info.setText(info);
    }

    public void setNum(String num) {
        this.num.setText(num+" 개");
    }

    public void setPrice(String price) {
        this.price.setText("￦ "+price);
    }
}
