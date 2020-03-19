package com.jnn.bots;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class KICKCommand implements COMMANDS {
	public static String[] paramHelp = { "Direction", "Power"};
	public float x,y,dist;
	public static Random rand = new Random(System.currentTimeMillis());
	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	public float distance(float x1, float y1, float x2, float y2)
	{
		float dx= Math.abs(x2-x1);
		float dy= Math.abs(y2-y1);
		
		float tot = (float) (Math.pow(dx, 2) + Math.pow(dy, 2));
		float r= (float) Math.sqrt(tot);
		return r;
		
		
		
	}
	public boolean operate(Bot b,ArrayList<String> params,
			Hashtable<String, RegEntry> regs,  PlayerCode code) {
		// TODO Auto-generated method stub
		if (b.isReversed)
		{
			x= CMath.revX(regs.get("POSX").mValue);
			y = CMath.revY(regs.get("POSY").mValue);
		}
		else
		{
			x= regs.get("POSX").mValue;
			y = regs.get("POSY").mValue;
		}
			
		dist = distance(x,y,Soccer.b.x,Soccer.b.y);//(float) Math.sqrt(Math.pow(regs.get("POSX").mValue - game.b.x,2) - Math.pow(regs.get("POSY").mValue - game.b.y444444,2));
		
		if (dist < 15 )
			{
			if (regs.get(params.get(0)) == null)
				return false;
			
			if (params.get(1) == null)
				return false;
			
			int speed = Integer.valueOf(params.get(1));
		
			if (!BotLoader.mSoundManager.mAudioManager.isMusicActive())
				BotLoader.mSoundManager.playSound(3);
			
			BotLoader.mSoundManager.playSound(1);
		//	fsg
			
			float rawDirection = 0;
			if (b.isReversed)
				rawDirection =  CMath.revA(regs.get(params.get(0)).mValue);
			else
				rawDirection =  regs.get(params.get(0)).mValue;
			
			int accuracy = rand.nextInt(100);
			
			if (accuracy > 90)
			{
				Soccer.b.direction=rawDirection;			
				Soccer.b.speed =  speed;
			}
			else
			{
				
				float cSpeed = Soccer.b.speed;
				float offSet= (CMath.ANGLE_90/4)- ((CMath.ANGLE_90/2)*(cSpeed/10));
				rawDirection += offSet;
				
				Soccer.b.direction=rawDirection;
				Soccer.b.speed =  speed;
			}
			
			}
		
		
		return false;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return "KICK";
	}
	public int getNumParams() {
		// TODO Auto-generated method stub
		return 2;
	}

	public String getParamHelp(int paramNum) {
		// TODO Auto-generated method stub
		return paramHelp[paramNum];
	}

	public String getCommandHelp() {
		// TODO Auto-generated method stub
		return "KICK ANGLE POWER - Kicks the ball at the angle in param1 at the power in param2.";
	}

}
