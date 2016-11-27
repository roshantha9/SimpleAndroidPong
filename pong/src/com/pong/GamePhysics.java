package com.pong;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;
import android.view.View;


public class GamePhysics{
	
    // dimensions of the walls
    private int intWall_MaxY=0;
    private int intWall_MaxX=0;
    private int intWall_MinY=0;
    private int intWall_MinX=0;
    
    // velocities
    private float intBall_VelocityX=5;
    private float intBall_VelocityY=5;    
    
    public GamePhysics(int intWall_MaxY, int intWall_MaxX, int intWall_MinX, int intWall_MinY) {
       	this.intWall_MaxX = intWall_MaxX;
       	this.intWall_MaxY = intWall_MaxY;
       	this.intWall_MinX = intWall_MinX;
       	this.intWall_MinY = intWall_MinY;        
    }
        
    
    public void AnimateP2Paddle(Paddle objP2Paddle, Ball objBall){
    	   	
    	// get ball x and mirror on p2 paddle
    	// but give a slight offset to the left
    	objP2Paddle.MoveX(objBall.getX()-(Consts.P1_PADDLE_WIDTH/3));     	
    }
    
    
    public void ResetBall(Ball objBall){
    	objBall.MoveXY(Consts.getScrWidth()/2, Consts.getScrHeight()/2);
    	
    	//intBall_VelocityX = 0;
    	//intBall_VelocityY = 0;
    }
    
    
    /*
     * AnimateBall
     * Return values:
     * 0 - valid hit
     * 1 - bottom wall hit (P1 miss, P2 scores)
     * 2 - top wall hit (P2 miss, P1 scores)
     */
    public int AnimateBall(Ball objBall, Paddle objP1Paddle, Paddle objP2Paddle){
    	
    	int intHitResult = 0;
    	
    	float intNewBallX = 0;
    	float intNewBallY = 0;
    	
    	    	
    	/* ************************ */
    	/* paddle p1 hit checking   */
    	/* ************************ */
    	// if hit - dir change same as hit bottom wall
    	if(((int)objBall.getX() > (int)objP1Paddle.getX()) && ((int)objBall.getX() < ((int)objP1Paddle.getX()+Consts.P1_PADDLE_WIDTH)) &&
    	   ((int)objBall.getY() == (int)objP1Paddle.getY()-(int)Consts.P1_PADDLE_HEIGHT)){
    				
    	    		intBall_VelocityY = intBall_VelocityY * -1;
    	    		intHitResult = 0;
    	}
    	else    	
    	
    	/* ************************ */
    	/* paddle p2 hit checking   */
    	/* ************************ */
    	// if hit - dir change same as hitting top wall
    	if(((int)objBall.getX() > (int)objP2Paddle.getX()) && ((int)objBall.getX() < ((int)objP2Paddle.getX()+Consts.P2_PADDLE_WIDTH)) &&
    	   ((int)objBall.getY() == (int)objP2Paddle.getY()+(int)Consts.P2_PADDLE_HEIGHT*2)){
    	    		   				   		
    	    		intBall_VelocityY = intBall_VelocityY * -1; 
    	    		intHitResult = 0;
    	}
    	else
    	
    	
    	/* ************************ */
    	/* ball wall hit checking   */
    	/* ************************ */
    	
    	// check if ball hit *bottom* wall
    	if((int)objBall.getY()+(int)Consts.BALL_RADIUS > intWall_MaxY){
    		//intBall_VelocityY = intBall_VelocityY * -1;
    		intHitResult = 1;
    		
    		// reset ball
    		this.ResetBall(objBall);
    		
    	}
    	else
    	// check if ball hit *top* wall
    	if ((int)objBall.getY() < intWall_MinY){
    		//intBall_VelocityY = intBall_VelocityY * -1;
    		intHitResult = 2;
    		
    		// reset ball
    		this.ResetBall(objBall);    		
    	}
    	else
    	// check if ball hit *left* wall
    	if ((int)objBall.getX()-(int)Consts.BALL_RADIUS < intWall_MinX){
    		intBall_VelocityX= intBall_VelocityX * -1;
    		intHitResult = 0;
    	}
    	else
   		// check if ball hit *right* wall
    	if((int)objBall.getX()+(int)Consts.BALL_RADIUS > intWall_MaxX){
    		intBall_VelocityX = intBall_VelocityX * -1;
    		intHitResult = 0;
    	}
    	else{
    		// do nothing;
    	}
    	
    	// apply new ball vectors
    	intNewBallX = objBall.getX() + intBall_VelocityX;
    	intNewBallY = objBall.getY() + intBall_VelocityY;
    	
    	objBall.MoveXY(intNewBallX, intNewBallY);    	
    	
    	return intHitResult;    	
    }
        
}
