package customclass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by we25 on 2017-07-05.
 */

public class FillView extends View {


    private Paint paint;
    private Canvas canvas;
    private String[] seat;


	public FillView(Context context , String[] seat){
        super(context);
        paint = new Paint();
        this.seat = seat;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        int i = 0 ;

        // 왼쪽 테이블 + 의자1 / 의자2
        if(Integer.parseInt(seat[i]) == 1) drawCircle(260,80,40);
        i++;
        if(Integer.parseInt(seat[i]) == 1)drawCircle(260,190,40);
        i++;

        // 왼쪽 테이블 + 의자3 / 의자4
        if(Integer.parseInt(seat[i]) == 1)drawCircle(260,370,40);
        i++;
        if(Integer.parseInt(seat[i]) == 1)drawCircle(260,480,40);
        i++;

        // 테이블 + 의자 5 / 의자6 / 의자7 / 의자8
        if(Integer.parseInt(seat[i]) == 1)drawCircle(470,120,40);
        i++;
        if(Integer.parseInt(seat[i]) == 1)drawCircle(590,120,40);
        i++;

        if(Integer.parseInt(seat[i]) == 1)drawCircle(470,430,40);
        i++;
        if(Integer.parseInt(seat[i]) == 1)drawCircle(590,430,40);
        i++;

        // 테이블 + 의자9 / 의자10 / 의자11 / 의자12
        if(Integer.parseInt(seat[i]) == 1)drawCircle(770,120,40);
        i++;
        if(Integer.parseInt(seat[i]) == 1)drawCircle(890,120,40);
        i++;

        if(Integer.parseInt(seat[i]) == 1)drawCircle(770,430,40);
        i++;
        if(Integer.parseInt(seat[i]) == 1)drawCircle(890,430,40);
        i++;

        // 테이블 + 의자13 / 의자14
        if(Integer.parseInt(seat[i]) == 1)drawCircle(470,570,40);
        i++;

        if(Integer.parseInt(seat[i]) == 1)drawCircle(470,800,40);
        i++;
        // 테이블 + 의자15 / 의자16
        if(Integer.parseInt(seat[i]) == 1)drawCircle(630,570,40);
        i++;
        if(Integer.parseInt(seat[i]) == 1)drawCircle(630,800,40);
        i++;

        // 테이블 + 의자17 / 의자18
        if(Integer.parseInt(seat[i]) == 1)drawCircle(790,570,40);
        i++;
        if(Integer.parseInt(seat[i]) == 1)drawCircle(790,800,40);
        i++;
        // 테이블 + 의자19 / 의자20
        if(Integer.parseInt(seat[i]) == 1)drawCircle(950,570,40);
        i++;
        if(Integer.parseInt(seat[i]) == 1)drawCircle(950,800,40);
        i++;


        // 휴지통
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawRect(20,600,140,800,paint);



    }


    public void drawCircle(int x,int y , int R){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(x,y,R,paint);
    }

    public void drawRect(int x , int y , int w, int h){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(x,y,w,h,paint);
    }



}