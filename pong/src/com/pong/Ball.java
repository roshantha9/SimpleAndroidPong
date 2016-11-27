package com.pong;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;
import android.view.View;


public class Ball extends View {
	private float x;
    private float y;
    private final int r;
    private final int fill;
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    
    
    public Ball(Context context, float x, float y, int r, int fill) {
        super(context);
        //mPaint.setColor(0xFFFF0000);
        mPaint.setColor(fill);
        this.x = x;
        this.y = y;
        this.r = r;
        this.fill=fill;
    }
    
    // setter
    public void MoveXY(float x, float y){
    	this.x=x;
    	this.y=y;    	
    }
    
    // getters
    public float getX(){
    	return this.x;
    }
    public float getY(){
    	return this.y;
    }
    
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, r, mPaint);
    }       
        
}
