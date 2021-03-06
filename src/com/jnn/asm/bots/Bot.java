package com.jnn.asm.bots;

import java.util.ArrayList;
import java.util.Hashtable;

import com.jnn.asm.soccer.Soccer;
import com.jnn.asm.util.CMath;
import com.jnn.bots.command.COMMANDS;

import android.graphics.Color;
import android.graphics.Paint;

public class Bot implements GamePiece {
    public PlayerCode mCode;
    public boolean[] codeStat = new boolean[1000];
    int ip;
    
    public float[] stack = new float[31] ;
	
	//Soccer mParent;
	public Paint mPaint = new Paint();
	public boolean isReversed=false;
	public Hashtable<String, RegEntry> regs = new Hashtable<String, RegEntry>();
	public int team=0;
	public int id=0;
	//Temp vars
	float x;
	float y;
	float speed;
	float direction;
	
	static float cx=0;
	static float cy=0;
	
	String thisCom;
	ArrayList<String> params;
	
	String cCommand=null;
	COMMANDS thisCommand;
	
	public boolean isReg(String iName){
		return regs.containsKey(iName);
	}
	public float getReg(String iName)
	{
		return regs.get(iName).mValue;
	}
	public Bot(PlayerCode iCode,  float goalx, float goaly, float startx, float starty)
	{
		mCode=iCode;
		regs.put("POSX",new RegEntry(startx));
		regs.put("POSY",new RegEntry(starty));
		regs.put("TRGX",new RegEntry(144));
		regs.put("TRGY",new RegEntry(241));
		regs.put("BALX",new RegEntry(144));
		regs.put("BALY",new RegEntry(241));
		regs.put("BALR",new RegEntry(241));
		regs.put("BALD",new RegEntry(241));
		regs.put("SPD",new RegEntry(3));
		regs.put("DIR",new RegEntry(241));
		regs.put("IP",new RegEntry(0));
		regs.put("NMOD",new RegEntry(0));
		regs.put("GOLR",new RegEntry(5));
		regs.put("GOLX",new RegEntry(goalx));
		regs.put("GOLY",new RegEntry(goaly));
		regs.put("AUX1",new RegEntry(startx));
		regs.put("AUX2",new RegEntry(startx));
		regs.put("AUX3",new RegEntry(startx));
		regs.put("AUX4",new RegEntry(startx));
		regs.put("AUX5",new RegEntry(startx));
		regs.put("AUX6",new RegEntry(startx));
		regs.put("AUX7",new RegEntry(startx));
		regs.put("AUX8",new RegEntry(startx));
		regs.put("AUX9",new RegEntry(startx));
		regs.put("AUX10",new RegEntry(startx));
		regs.put("RET1",new RegEntry(startx));
		regs.put("RET2",new RegEntry(startx));
		regs.put("RET3",new RegEntry(startx));
		regs.put("RET4",new RegEntry(startx));
		regs.put("RET5",new RegEntry(startx));
		
		for (int j=0;j<1000;j++)
			this.codeStat[j]=true;
		

		//mParent =g;
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		mPaint.setColor(Color.BLUE);
		mPaint.setTextSize(32);
		
	}
	public int getX() {
		// TODO Auto-generated method stub
		return regs.get("POSX").intValue();
	}

	public int getY() {
		// TODO Auto-generated method stub
		return regs.get("POSY").intValue();
	}

	public int getSpeed() {
		// TODO Auto-generated method stub
		return regs.get("SPD").intValue();
	}

	public int getDirection() {
		// TODO Auto-generated method stub
		return regs.get("DIR").intValue();
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
		return mCode;
		
	}
	public static ArrayList<String> tokenizeString(String iString){
		
		ArrayList<String> rList = new ArrayList<String>();
		
		int cPos=iString.indexOf(' ',0)+1;
		int newPos;
		while (cPos > 0)
		{
			newPos= iString.indexOf(' ', cPos);
			if (newPos > 0)
				{rList.add(iString.substring(cPos,newPos));
				 cPos=newPos+1;
				}
			else
				{rList.add(iString.substring(cPos,iString.length()));
				cPos=-1;
				}
		}
		
		return rList;
	}
	
public static float distance2(float x1, float y1, float x2, float y2)
{
	float dx= Math.abs(x2-x1);
	float dy= Math.abs(y2-y1);
	
	float tot = (float) (Math.pow(dx, 2) + Math.pow(dy, 2));
	float r= (float) Math.sqrt(tot);
	return r;
	
	
	
}
public static boolean isOccupied(float x, float y, int ignoreBot)
{
	if (CMath.rnd.nextInt(100) < 15 )
		return false;

//	if (Soccer.players.get(ignoreBot).isReversed)
//	{
//		x = CMath.revX(x);
//		y = CMath.revY(y);
//	}
	
	for(int j=0; j < Soccer.players.size();j++)
	{
		if(j != ignoreBot && Soccer.players.get(j).team != Soccer.players.get(ignoreBot).team)
		{
			cx = Soccer.players.get(j).regs.get("POSX").mValue;
			cy = Soccer.players.get(j).regs.get("POSY").mValue;
			
			if (Soccer.players.get(j).isReversed)
			{
				cx = CMath.revX(Soccer.players.get(j).regs.get("POSX").mValue);
				cy = CMath.revY(Soccer.players.get(j).regs.get("POSY").mValue);
			}
			
			
			  if (CMath.distance(x,y,cx,cy) < 4)
				{BotLoader.mSoundManager.playSound(4);
				  return true;
				
				}
			
		}
	}
	return false;
}

public COMMANDS getNextStep(){
	
	int ip = (int) regs.get("IP").mValue;
	
	if (ip >= this.mCode.code.size())
	{regs.get("IP").mValue=0;
	 ip=0;
	}
	
	String thisCom = this.mCode.code.get(ip);
	ArrayList<String> params = tokenizeString(thisCom);
	
	String cCommand=null;
	if (thisCom.indexOf(' ',0) >= 0)
	{
		cCommand=thisCom.substring(0,thisCom.indexOf(' ',0));
	}
	else
		cCommand=thisCom;
	
	COMMANDS thisCommand= Soccer.commands.get(cCommand);
	
	if (thisCommand == null)
		thisCommand=Soccer.commands.get("NOP");
	
	return thisCommand;
}
	public void makeMove() {
		// TODO Auto-generated method stub
		
	if (this.isReversed)
	{
		regs.get("BALX").mValue=CMath.revX(Soccer.b.x);
		regs.get("BALY").mValue=CMath.revY(Soccer.b.y);
		regs.get("BALR").mValue=CMath.calcAngle(regs.get("POSX").mValue,regs.get("POSY").mValue,regs.get("BALX").mValue,regs.get("BALY").mValue);
		
		regs.get("BALD").mValue=CMath.distance(regs.get("POSX").mValue,regs.get("POSY").mValue,regs.get("BALX").mValue,regs.get("BALY").mValue);
		
		x = CMath.revX(regs.get("POSX").mValue);
		y = CMath.revY(regs.get("POSY").mValue);
		
		
		//regs.get("GOLR").mValue = CMath.revA(calcAngle(x,y,regs.get("GOLX").mValue,regs.get("GOLY").mValue));
		regs.get("GOLR").mValue = (CMath.calcAngle(regs.get("POSX").mValue,regs.get("POSY").mValue,regs.get("GOLX").mValue,regs.get("GOLY").mValue));
		
		speed = regs.get("SPD").mValue;
		direction = CMath.revA(regs.get("DIR").mValue);
		
	}
	else
	{
		regs.get("BALX").mValue=Soccer.b.x;
		regs.get("BALY").mValue=Soccer.b.y;
		regs.get("BALR").mValue=CMath.calcAngle(regs.get("POSX").mValue,regs.get("POSY").mValue,Soccer.b.x,Soccer.b.y);
		regs.get("BALD").mValue=CMath.distance(regs.get("POSX").mValue,regs.get("POSY").mValue,Soccer.b.x,Soccer.b.y);
		
		y = regs.get("POSY").mValue;
		x = regs.get("POSX").mValue;
		regs.get("GOLR").mValue = CMath.calcAngle(x,y,regs.get("GOLX").mValue,regs.get("GOLY").mValue);
		
		speed = regs.get("SPD").mValue;
		direction = regs.get("DIR").mValue;
	}
		
	
	if (speed > 5)
		speed =5;
	
	if (speed < -5)
		speed =-5;
	
	ip = regs.get("IP").intValue();
	
	if (ip >= this.mCode.code.size())
	{regs.get("IP").mValue=0;
	 ip=0;
	}
	
	thisCom = this.mCode.code.get(ip);
	params = tokenizeString(thisCom);
	
	cCommand=null;
	if (thisCom.indexOf(' ',0) >= 0)
	{
		cCommand=thisCom.substring(0,thisCom.indexOf(' ',0));
	}
	else 
		cCommand=thisCom;
	
	thisCommand= Soccer.commands.get(cCommand);
	
	if (thisCommand != null)
		this.codeStat[ip] = thisCommand.operate(this,params);
	else
		this.codeStat[ip] = false;
	
	
	
	regs.get("IP").mValue += 1;
	
	

	if (regs.get("NMOD").mValue == 0) 
	
		{
		if (this.isReversed)
		direction = CMath.revA(CMath.calcAngle( regs.get("POSX").mValue,regs.get("POSY").mValue,regs.get("TRGX").mValue, regs.get("TRGY").mValue));
	else
		direction = CMath.calcAngle( regs.get("POSX").mValue,regs.get("POSY").mValue,regs.get("TRGX").mValue, regs.get("TRGY").mValue);
	
		}
	
	
	
	
	y += speed * Math.cos(direction);
		
		x += speed * Math.sin(direction);
		
		if (x > CMath.MAX_X || x < 0 || y > CMath.MAX_Y || y < 0 || isOccupied(x, y, this.id))
		{
			y -= speed * Math.cos(direction);			
			x -= speed * Math.sin(direction);
			//BotLoader.mSoundManager.playSound(4);
			
		}
		
		if (this.isReversed)
		{
			regs.get("POSX").mValue=CMath.revX(x);
			regs.get("POSY").mValue=CMath.revY(y);
			regs.get("DIR").mValue=CMath.revA(direction);
		}
		else
		{
			regs.get("POSX").mValue = x;
			regs.get("POSY").mValue = y;
			regs.get("DIR").mValue=direction;
		}
	}
	
	public float getBallDistanceReg()
	{
		return 0;//(float) Math.sqrt(Math.pow(x - this.mParent.b.x,2) + Math.pow(y - this.mParent.b.y,2)); 
	}
	
}