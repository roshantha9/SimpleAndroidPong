package com.pong;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.FrameLayout;
import android.view.MotionEvent;
import android.view.Display;
//import android.os.Build;



public class Pong extends Activity {
	
	/*** Local Variable declaration ***/
	
	// objects - ball, paddles, UI elements etc
	Ball objPongBall;
	Paddle objPaddleP1;
	Paddle objPaddleP2;
	GamePhysics GamePhy;
	private Button btnStart;
	private TextView lblScore;
	View MainView;
	
	int intP1Score =0;
	int intP2Score =0;
	
	
	
	// screen dimensions
	private int intScreenWidth=0;
	private int intScreenHeight=0;
	
	
	// timer related
	private Handler mHandler = new Handler();
	
	// booleans
	private boolean blnBallMoveEnabled = true;
	
	// for testing
	private int intTimerTick=0; 
	
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // debug
        System.out.println(android.os.Build.MODEL);
        System.out.println(android.os.Build.VERSION.RELEASE);
               
        // create main frame
        FrameLayout main = (FrameLayout) findViewById(R.id.main_view);
        
        // create start button
        btnStart = (Button)this.findViewById(R.id.btnStart);
        
        // create score label
        lblScore = (TextView)this.findViewById(R.id.lblScore);
        
        // get screen dimensions
        Display display = getWindowManager().getDefaultDisplay(); 
        intScreenWidth = display.getWidth();
        intScreenHeight = display.getHeight();
        
        // set in constants class
        Consts.setScrWidth(intScreenWidth);
        Consts.setScrHeight(intScreenHeight);
        
        
        // create and add Ball - center of screen
        objPongBall = new Ball(this, Math.round(intScreenWidth/2),
        							Math.round(intScreenHeight/2),
        							Consts.BALL_RADIUS,
        							Consts.BALL_COLOUR);
        main.addView(objPongBall);
        
        // create and add player 1
        objPaddleP1 = new Paddle(this, Math.round(intScreenWidth/2), 
        								Math.round(intScreenHeight)-Consts.P1_PADDLE_START_Y, 
        								Consts.P1_PADDLE_HEIGHT, 
        								Consts.P1_PADDLE_WIDTH,
        								Consts.P1_PADDLE_COLOUR);
        main.addView(objPaddleP1);
                
        // create and add player 2
        objPaddleP2 = new Paddle(this, Math.round(intScreenWidth/2), 
        							Consts.P2_PADDLE_START_Y, 
        							Consts.P2_PADDLE_HEIGHT, 
        							Consts.P2_PADDLE_WIDTH,
        							Consts.P2_PADDLE_COLOUR);
        main.addView(objPaddleP2);        
        
        // create game physics engine
        GamePhy = new GamePhysics((int)intScreenHeight,(int)intScreenWidth, 0, 0);
        
        // copy of screen view
        MainView = main;
        
        
        main.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent e) {
            	float x = e.getX();
            	float y = e.getY();
            	
            	//System.out.println(x);
            	//System.out.println(y);           
        		
            	objPaddleP1.MoveX(x);
            	v.invalidate();
        		return true;
            }
        });
        
    }  
    
    public void btnStart_OnClick(View view) {    	
    	System.out.println("btnStart_OnClick::Enter");
    	
    	// hide start button
    	//btnStart.setVisibility(View.GONE);
    	    	
    	if(blnBallMoveEnabled==false){
    		blnBallMoveEnabled = true;
    		mHandler.removeCallbacks(mUpdateBallMovement);
    		btnStart.setText("START");
    	}
    	else{
    		blnBallMoveEnabled = false;
    		btnStart.setText("PAUSE");
    		
    		// start the ball movement    		
        	mHandler.removeCallbacks(mUpdateBallMovement);
            mHandler.postDelayed(mUpdateBallMovement, 100); 
    	}   	
    }
    
    private Runnable mUpdateBallMovement = new Runnable() {
    	   public void run() {
    	       
    		   int intHitResult;
    		   
    		   //System.out.println("intTimerTick="+intTimerTick);
    		   //intTimerTick=intTimerTick+1;
    		   
    		   //System.out.println("Ball: "+objPongBall.getX()+","+objPongBall.getY());
    		   
    		   GamePhy.AnimateP2Paddle(objPaddleP2, objPongBall);
    		   
    		   intHitResult = GamePhy.AnimateBall(objPongBall,objPaddleP1,objPaddleP2);
    		   
    		   /* check if score needs to change */
    		   if(intHitResult != 0){
    			   // increment P2 score
    			   if (intHitResult == 1){
    				   intP2Score++;
    			   }
    			   else
    			   // increment P1 score
    			   if (intHitResult == 2){
    				   intP1Score++;
    			   }
    			   
    			   // update scores label
    			   lblScore.setText(intP1Score+":"+intP2Score);    			   
    		   }
    		     		   
    		   MainView.invalidate();
    		   
    		   mHandler.removeCallbacks(mUpdateBallMovement);
    	       mHandler.postDelayed(mUpdateBallMovement, Consts.GAME_MAIN_TIMER_TICK);
    	   }
    	};
    
    
    
}

