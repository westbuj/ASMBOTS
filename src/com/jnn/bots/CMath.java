package com.jnn.bots;

import java.util.Random;

public class CMath {
	public static final float MAX_X = 500;
	public static final float MAX_Y = 1000;
	public static final float ANGLE_0 = 0;
	public static final float ANGLE_90 = (float) (Math.PI/2);
	public static final float ANGLE_180 = (float) (Math.PI);
	public static final float ANGLE_270 = (float) (Math.PI + ( Math.PI/2));
	public static final float ANGLE_360 = (float) (Math.PI * 2);
	public static Random r = new Random();
	//temp holders
	public static float floatValue = 0;
	public static float revX(float x)
	{
		return MAX_X - x;
		
	}
	
	public static float revY(float y)
	{
		return MAX_Y - y;
	}
	
	public static float revA(float angle)
	{
		floatValue = angle + ANGLE_180;
//		if (angle < ANGLE_180)
//			floatValue = ANGLE_180 - angle;
//		else
//			floatValue = ANGLE_90 -( ANGLE_180 - angle);
//			
			
		if (floatValue > ANGLE_360)
			floatValue -= ANGLE_360;
		
		return floatValue;
	}
	public static float distance(float x1, float y1, float x2, float y2)
	{
		float dx= Math.abs(x2-x1);
		float dy= Math.abs(y2-y1);
		
		float tot = (float) (Math.pow(dx, 2) + Math.pow(dy, 2));
		float r= (float) Math.sqrt(tot);
		return r;
		
		
		
	}
}
