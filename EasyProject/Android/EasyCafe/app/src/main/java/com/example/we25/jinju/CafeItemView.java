package com.example.we25.jinju;

import com.example.we25.easycafe.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-06-25.
 */

public class CafeItemView extends LinearLayout {
    TextView textView;
    ImageView imageView;


    public CafeItemView(Context context) {
        super(context);
        init(context);

    }

    public CafeItemView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cafe_item,this,true);

        textView = (TextView)findViewById(R.id.textView);
        imageView = (ImageView)findViewById(R.id.imageView);


    }



    public void setName(String name) {
        textView.setText(name);
    }

    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }



}
