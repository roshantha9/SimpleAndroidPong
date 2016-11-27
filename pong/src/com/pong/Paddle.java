package com.pong;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;


public class Paddle extends View {
	private float x;
    private float y;
    private final int height;
    private final int width;
    private final int fill; 
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    
    public Paddle(Context context, float x, float y, int height, int width, int fill) {
        super(context);
        mPaint.setColor(fill);
        
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.fill=fill;
    }
    
    
    // setters
    public void MoveXY(float x, float y){
    	this.x=x;
    	this.y=y;    	
    }
    
    public void MoveX(float x){
    	this.x=x;
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
        //canvas.drawCircle(x, y, r, mPaint);
        canvas.drawRect(x - 0.5f, y - 0.5f, x + width + 0.5f, y + height + 0.5f, mPaint);
    }       
        
}
