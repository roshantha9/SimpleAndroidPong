package com.pong;

import android.view.Display;


public final class Consts{
		
	// constants for the ball
	public static final int BALL_START_X = 0;
	public static final int BALL_START_Y = 0;
	public static final int BALL_RADIUS = 12;
	public static final int BALL_COLOUR = 0xFFFF0000;
	
	// constants for the player 1 - paddle
	public static final int P1_PADDLE_START_X = 0;
	public static final int P1_PADDLE_START_Y = 90;
	public static final int P1_PADDLE_HEIGHT = 10;
	public static final int P1_PADDLE_WIDTH = 90;
	public static final int P1_PADDLE_COLOUR = 0xFF0000FF;
	
	// constants for the player 2 - paddle
	public static final int P2_PADDLE_START_X = 0;
	public static final int P2_PADDLE_START_Y = 70;
	public static final int P2_PADDLE_HEIGHT = 10;
	public static final int P2_PADDLE_WIDTH = 90;
	public static final int P2_PADDLE_COLOUR = 0xFF0000FF;
		
	// constant for the animation speed
	public static final int GAME_MAIN_TIMER_TICK = 50;
	
	// screen dimensions, initially set to zero, but needs
	// to be set to correct values at startup
	public static int intScreenWidth 	=0;
	public static int intScreenHeight	=0;
	
	
	// getters and setters for the screen dimensions
	public static int getScrWidth(){
		return intScreenWidth;
	}
	public static int getScrHeight(){
		return intScreenHeight;
	}
	public static void setScrWidth(int w){
		intScreenWidth = w;
	}
	public static void setScrHeight(int h){
		intScreenHeight = h;
	}
	
	
    private Consts(){
    	throw new AssertionError();
    }        
}
