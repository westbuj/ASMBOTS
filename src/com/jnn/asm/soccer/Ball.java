package com.jnn.asm.soccer;

import com.jnn.asm.bots.GamePiece;
import com.jnn.asm.bots.PlayerCode;
import com.jnn.asm.util.CMath;

import android.R.color;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball implements GamePiece {
	
	public float x;
	public float y;
	public float speed;
	public float direction;
	//GameField mParent;
	Paint ballPaint = new Paint();
	
	float dx;
	float dy;
	
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
			
	
		dx=  (float) (speed * Math.sin(direction));
		dy=  (float) (speed * Math.cos(direction));
		
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
				if (x >= Soccer.goalMinX && x <= Soccer.goalMaxX)
				{
				//BotLoader.mSoundManager.playSound(2);
				Soccer.GOAL=true;
				if (Soccer.team1.goaly==0)
					Soccer.team1.points += 1;
				
				if (Soccer.team2.goaly==0)
					Soccer.team2.points += 1;
				}
				 
				}
			
			if(y >= Soccer.height)
	{
				
				if (direction > CMath.ANGLE_270)
					direction = CMath.ANGLE_270 - (direction - CMath.ANGLE_270);
				
				else
					direction = CMath.ANGLE_180 - direction;
				
				
				if (x >= Soccer.goalMinX && x <= Soccer.goalMaxX)
				{//BotLoader.mSoundManager.playSound(2);
					Soccer.GOAL=true;
					if (Soccer.team1.goaly==Soccer.height)
						Soccer.team1.points += 1;
					
					if (Soccer.team2.goaly==Soccer.height)
						Soccer.team2.points += 1;
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
