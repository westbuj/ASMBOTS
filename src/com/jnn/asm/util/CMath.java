package com.jnn.asm.util;

import java.util.Random;

import com.jnn.asm.bots.Bot;

public class CMath {
	public static final float MAX_X = 500;
	public static final float MAX_Y = 1000;
	public static final float ANGLE_0 = 0;
	public static final float ANGLE_90 = (float) (Math.PI/2);
	public static final float ANGLE_180 = (float) (Math.PI);
	public static final float ANGLE_270 = (float) (Math.PI + ( Math.PI/2));
	public static final float ANGLE_360 = (float) (Math.PI * 2);
	public static Random rnd = new Random();
	//temp holders
	public static float floatValue = 0;
	
	
	public static float dx;
	public static float dy;
	
	public static float tot;
	public static float r;
	public static float ret;
	
	
	public static float revX(float xi)
	{
		return MAX_X - xi;
		
	}
	
	public static float revY(float yi)
	{
		return MAX_Y - yi;
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
		dx= Math.abs(x2-x1);
		dy= Math.abs(y2-y1);
		
		tot = (float) (Math.pow(dx, 2) + Math.pow(dy, 2));
		r= (float) Math.sqrt(tot);
		return r;
		
		
		
	}
	
	
public static float calcAngle(float sX, float sY, float tX, float tY){
		
		dy = (tY-sY);
		dx = (tX-sX);
		
	//	return (float) Math.atan(tan);
		
		float tan;
		if (dx==0)
			tan=999999999f;
		else
			tan=Math.abs(dy)/Math.abs(dx);
		
		ret = (float) Math.atan(tan);
		
		if (dx < 0 & dy < 0)
			ret = (float) (.75 * (2 * Math.PI) -ret);//direction = direction;
		
		if (dx < 0 & dy > 0)
			ret -= Math.PI/2;
		if (dx > 0 & dy < 0)
			ret += Math.PI/2;
		if (dx > 0 & dy > 0)
			ret = (float) (Math.PI/2 - ret);
		
		return ret;
		
	}

public static float getValueFromParam(Bot b, String tParam)
{
	float ret = 0;
	if (b.regs.get(tParam) != null)
		ret=b.regs.get(tParam).mValue;
	else
		ret=Float.valueOf(tParam);
	
	return ret;
}
}
