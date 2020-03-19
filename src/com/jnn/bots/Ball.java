package com.jnn.bots;

import android.R.color;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball implements GamePiece {
	
	float x;
	float y;
	float speed;
	float direction;
	//GameField mParent;
	Paint ballPaint = new Paint();
	
	public Ball()
	{
		x=244;
		y=225;
		speed=3;
		direction=CMath.ANGLE_180 + CMath.ANGLE_90/3;
	//	mParent =f;
		ballPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		ballPaint.setColor(Color.WHITE);
	}
	
	public void makeMove()
	
	{
		//double directionRads = direction*(Math.PI/180) ;
		
		if (Math.abs(speed)>10)
			speed=10;
			
	
		float dx=  (float) (speed * Math.sin(direction));
		float dy=  (float) (speed * Math.cos(direction));
		
		y += dy; //speed * Math.sin(direction);		
		x += dx; //speed * Math.cos(direction);
		
		
		
		if (x >= Soccer.width || x <= 0 || y >= Soccer.height || y <= 0)
		{
			
			
			
			if(x >= Soccer.width)
			{	
			
				if (direction < CMath.ANGLE_90)
					direction = (float) (CMath.ANGLE_360 - direction);
				
				else
					direction = CMath.ANGLE_270 - (direction - CMath.ANGLE_90); 
				
				
			}
			
			
			if(x <= 0 )
			{	
				if (direction < CMath.ANGLE_270)
					direction  = CMath.ANGLE_90 + (CMath.ANGLE_270 - direction);
				
				else
					direction = CMath.ANGLE_90 -(direction - CMath.ANGLE_270);
		
		} 
			
			if(y <= 0 )
				{
				
				if (direction < CMath.ANGLE_180)
					direction = CMath.ANGLE_180 - direction;
				
				else
					direction = CMath.ANGLE_360 -(direction - CMath.ANGLE_180);
				
				//GOAL
				if (x>100 && x< 250)
				{
				BotLoader.mSoundManager.playSound(2);
				Soccer.GOAL=true;
				
				Soccer.team1Score += 1;
				}
				 
				}
			
			if(y >= Soccer.height)
	{
				
				if (direction > CMath.ANGLE_270)
					direction = CMath.ANGLE_270 - (direction - CMath.ANGLE_270);
				
				else
					direction = CMath.ANGLE_180 - direction;
				
				
				if (x>100 && x< 250)
				{BotLoader.mSoundManager.playSound(2);
				Soccer.GOAL=true;
				Soccer.team2Score += 1;
				}
				
				}
			
			y -= dy;//speed * Math.sin(direction);
			x -= dx; //speed * Math.cos(direction);
		
			if (direction > CMath.ANGLE_360)
				direction -= CMath.ANGLE_360;
			x +=  (float) (speed * Math.sin(direction));
			y +=  (float) (speed * Math.cos(direction));
		
		}
		
	}

	public int getX() {
		// TODO Auto-generated method stub
		return (int)x;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return (int)y;
	}

	public int getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getDirection() {
		// TODO Auto-generated method stub
		return (int) direction;
	}

	public int setX(int iInt) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int setY(int iInt) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int setSpeed(int iInt) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int setDirection(int iInt) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void run() {
		// TODO Auto-generated method stub
		
		
	}

	public PlayerCode getCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
