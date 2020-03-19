package com.jnn.bots;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;



import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Soccer implements GameField {

	public static String cStep="";
	public static int gameTime=0;
	public static int cDebugBot=1;
	public static Team team1;
	public static Team team2;
	public static Ball b=new Ball();
	public static float width=500;
	public static float height=1000;
	public static ArrayList<Bot> players = new ArrayList<Bot>();
	public static boolean GOAL=true;
	MyView mView = null;
	public static Hashtable<String, COMMANDS> commands = new Hashtable<String, COMMANDS>();
	public static ArrayList<String> registers = new ArrayList<String>();
	//public static int intensity =0;
	Paint strokePaint=new Paint();
	public static int team1Score;
	public static int team2Score;
	//temps
	float newX;
	float newY;
	float x;
	float y;
	public Soccer(MyView iView){
		mView=iView;
		commands.put("RSET",new RSETCommand());
		commands.put("KICK", new KICKCommand());
		commands.put("INC", new INCCommand());
		commands.put("JMPA", new JMPACommand());
		commands.put("JULT", new JULTCommand());
		commands.put("NOP", new NOPCommand());
		
		registers.add("AUX1");
		registers.add("AUX2");
		registers.add("AUX3");
		registers.add("AUX4");
		registers.add("AUX5");
		registers.add("AUX6");
		registers.add("AUX7");
		registers.add("AUX8");
		registers.add("AUX9");
		registers.add("AUX10");
		
		registers.add("POSX");
		registers.add("POSY");
		registers.add("TRGX");
		registers.add("TRGY");
		registers.add("BALX");
		registers.add("BALY");
		registers.add("BALR");
		registers.add("BALD");
		registers.add("SPD");
		registers.add("DIR");
		registers.add("IP");
		registers.add("GOLR");
		registers.add("GOLX");
		registers.add("GOLY");
	
		
		strokePaint.setStyle(Paint.Style.STROKE);
		strokePaint.setColor(Color.WHITE);
		
		team1=new Team();
		team2=new Team();
	
		
	}
	
	
	public void initTeams()
	{
		
		String[] botSel = new String[5];
		
		for(int j=0;j<5;j++)
		{
			team1.getBot(j).team=1;
			team2.getBot(j).team=2;
			team1.getBot(j).id=addPlayer(team1.getBot(j),1);
			team2.getBot(j).id=addPlayer(team2.getBot(j),2);
			team2.getBot(j).isReversed=true;
			
			team1.getBot(j).mPaint.setColor(Color.BLUE);
			team2.getBot(j).mPaint.setColor(Color.RED);
			
			botSel[j]=Integer.toString(team1.getBot(j).id);
			
		}
		cDebugBot = team1.getBot(0).id;
		
		
		ListAdapter la=new ListAdapter(BotLoader.mContext);
	    la.setArray(botSel);
	    
	    BotLoader.botSelect.setAdapter(la);
	  
		
	}
	
	public int addPlayer(Bot g, int team){
		g.team=team;
		players.add(g);
		return players.size()-1;
	}

	public void movePlayersToStart(){
		
		float x=0;
		float y=0;
		b.x=CMath.MAX_X/2;
		b.y=CMath.MAX_Y/2;
		b.speed=0;
	//	Random randomGenerator = new Random();
		
		for(int j=0; j < players.size();j++)
		{
		  //REverseSIdes
		//  players.get(j).isReversed= !players.get(j).isReversed;
			
		 players.get(j).regs.get("POSX").mValue=CMath.revX(players.get(j).regs.get("POSX").mValue);
		 players.get(j).regs.get("POSY").mValue=CMath.revY(players.get(j).regs.get("POSY").mValue);
		  players.get(j).isReversed= !players.get(j).isReversed;
		  
		  players.get(j).regs.get("TRGX").mValue= players.get(j).regs.get("POSX").mValue;
		  players.get(j).regs.get("TRGY").mValue= 750;
		}
		
		boolean moving = true;
		while (moving)
		{	moving=false;	
		for(int j=0; j < players.size();j++)
		{
			if (CMath.distance(players.get(j).regs.get("POSX").mValue,players.get(j).regs.get("POSY").mValue,players.get(j).regs.get("TRGX").mValue,players.get(j).regs.get("TRGY").mValue) > 5)
				moving=true;
		
			 
			
		
			
		float direction=0;
		if (players.get(j).isReversed)
			direction = CMath.revA(this.calcAngle( players.get(j).regs.get("POSX").mValue,players.get(j).regs.get("POSY").mValue,players.get(j).regs.get("TRGX").mValue, players.get(j).regs.get("TRGY").mValue));
		else
			direction = this.calcAngle( players.get(j).regs.get("POSX").mValue,players.get(j).regs.get("POSY").mValue,players.get(j).regs.get("TRGX").mValue, players.get(j).regs.get("TRGY").mValue);
		
		
		float speed=2.12f;
		if (players.get(j).isReversed)
			{x = CMath.revX(players.get(j).regs.get("POSX").mValue);
			y = CMath.revY(players.get(j).regs.get("POSY").mValue);
			}
		else
		{
			x = (players.get(j).regs.get("POSX").mValue);
			y = (players.get(j).regs.get("POSY").mValue);
		}
		
		
		y += speed * Math.cos(direction);
			
			x += speed * Math.sin(direction);
			
//			if (x > CMath.MAX_X || x < 0 || y > CMath.MAX_Y || y < 0)
//			{
//				y -= speed * Math.cos(direction);			
//				x -= speed * Math.sin(direction);
//				
//				
//			}
			
			if (players.get(j).isReversed)
			{
				players.get(j).regs.get("POSX").mValue=CMath.revX(x);
				players.get(j).regs.get("POSY").mValue=CMath.revY(y);
				players.get(j).regs.get("DIR").mValue=CMath.revA(direction);
			}
			else
			{
				players.get(j).regs.get("POSX").mValue = x;
				players.get(j).regs.get("POSY").mValue = y;
				players.get(j).regs.get("DIR").mValue=direction;
			}
		
			mView.updateImage.sendEmptyMessage(1);
		}
		
		}
		

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			
		}
	}
	public void run() {
		// TODO Auto-generated method stub
		
       
	
    	
		GOAL=false;
		movePlayersToStart();
		 BotLoader.mSoundManager.playSound(4);
		 BotLoader.mSoundManager.playSound(4);
		
		//boolean playing =true;
		while (Soccer.gameTime <= 9999)
		{
			//BotLoader.mSoundManager.playSound(4);
			if(BotLoader.speed > 1 )
				try{Thread.sleep(BotLoader.speed);}catch (Exception e){}
				
				
			b.makeMove();
			if (!GOAL)
			{
				Soccer.gameTime += 1;
			for(int j=0; j < players.size();j++)
				{
				
				if(BotLoader.isDebug & j == Soccer.cDebugBot)
				
				{
					
					int ip = players.get(j).regs.get("IP").intValue();
					
					if (ip >= players.get(j).mCode.code.size())
					{players.get(j).regs.get("IP").mValue=0;
					 ip=0;
					}
					
					cStep = players.get(j).mCode.code.get(ip);
					//cStep= players.get(j).getNextStep().toString();
					boolean wasStepping = BotLoader.stepping;
					if (BotLoader.stepping)
					{
						BotLoader.waitForNext = true;
						while(BotLoader.waitForNext)
						{
							try{Thread.sleep(15);}catch (Exception e){}
						}
					
						
					}
					
					
				}
				
				if (	BotLoader.codeViewMode)
					CodeViewActivity.setScroll.sendEmptyMessage(1);	
				
				players.get(j).makeMove();
				
				//players2.get(j).makeMove();
				}
			
			}
			else
			{
				//Someone scored a goal.
				BotLoader.mSoundManager.playSound(4);
				BotLoader.mSoundManager.playSound(3);
				movePlayersToStart();
				BotLoader.mSoundManager.playSound(3);
				GOAL=false;
			} 
		
			b.speed = (float) (b.speed - .08);
			if (b.speed < 0)
				b.speed=0;
			
			//View.invalidate();
			mView.updateImage.sendEmptyMessage(1);
		}
		 //BotLoader.mSoundManager.playSound(3);
	
			BotLoader.mSoundManager.playSound(4);

		
		movePlayersToStart();
	}

public float calcAngle(float sX, float sY, float tX, float tY){
		
		float dy = (tY-sY);
		float dx = (tX-sX);
		
	//	return (float) Math.atan(tan);
		
		float tan;
		if (dx==0)
			tan=999999999f;
		else
			tan=Math.abs(dy)/Math.abs(dx);
		
		float ret = (float) Math.atan(tan);
		
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
	public void draw(Canvas c) {
		// TODO Auto-generated method stub
		float ratioX = (float)c.getWidth()/Soccer.width;
		float ratioY = (float)c.getHeight()/Soccer.height;
		float angle2Ball=0;
		
		c.drawText(" "+Integer.toString(Soccer.gameTime),50, 50,b.ballPaint);
		
		c.drawText("Score: "+Integer.toString(Soccer.team1Score)+" to " + Integer.toString(Soccer.team2Score),50, 75,b.ballPaint);
		
		
		c.drawRect(1,1, c.getWidth()-2,c.getHeight()-2, strokePaint);
		c.drawLine(1, c.getHeight()/2, c.getWidth()-2, c.getHeight()/2, strokePaint);
		c.drawCircle(c.getWidth()/2, c.getHeight()/2, c.getHeight()/11 , strokePaint);
		
		c.drawRect(c.getWidth()/3,1, 2*(c.getWidth()/3),c.getHeight()/11, strokePaint);
		c.drawRect(c.getWidth()/3,c.getHeight()-(c.getHeight()/11), 2*(c.getWidth()/3),c.getHeight()-2, strokePaint);
		
		
		c.drawCircle(b.x * ratioX, b.y * ratioY, 5+ (float) (b.speed * 1.5) , b.ballPaint);
		
		for(int j=0; j < this.players.size();j++)
			{
			
			x=players.get(j).getX();
			y=players.get(j).getY();
			angle2Ball = (players.get(j).regs.get("DIR").mValue);
			if (players.get(j).isReversed)
			{
				x=CMath.revX(x);
				y=CMath.revY(y);
				angle2Ball = CMath.revA(players.get(j).regs.get("DIR").mValue);
			}
			
			x=x*ratioX;
			y=y*ratioY;
			
			
			c.drawCircle(x, y, 5, players.get(j).mPaint);
			if (j == Soccer.cDebugBot)
			{
				c.drawCircle(x, y, 10, players.get(j).mPaint);
			}
			// c.drawText("BDIS="+Float.toString( players1.get(j).getBallDistanceReg()),players1.get(j).getX() * ratioX, players1.get(j).getY() * ratioY,  players1.get(j).mPaint);
			 newX = (float) ((players.get(j).regs.get("SPD").mValue*10) * Math.sin(angle2Ball));
			 newY =  (float) ((players.get(j).regs.get("SPD").mValue*10) * Math.cos(angle2Ball));
			c.drawLine( x,y, x+newX,y+newY,  players.get(j).mPaint);
			 
			}
		
//		for(int j=0; j < players2.size();j++)
//		{	
//			
//			x=players2.get(j).getX();
//			y=players2.get(j).getY();
//			angle2Ball = (players2.get(j).regs.get("GOLR").mValue);
//			if (players2.get(j).isReversed)
//			{
//				x=CMath.revX(x);
//				y=CMath.revY(y);
//				angle2Ball = CMath.revA(players2.get(j).regs.get("GOLR").mValue);
//				
//			}
//			
//			x=x*ratioX;
//			y=y*ratioY;
//			
//			c.drawCircle(x, y, 5, players2.get(j).mPaint);
////		c.drawText("BDIS="+Float.toString( players2.get(j).getBallDistanceReg()),players2.get(j).getX() * ratioX, players2.get(j).getY() * ratioY,  players2.get(j).mPaint);
//		 newX = (float) (150 * Math.sin(angle2Ball));
//		 newY =  (float) (150 * Math.cos(angle2Ball));
//		
//		c.drawLine( x,y, x+newX,y+newY,  players2.get(0).mPaint);
//		}
		

//		float p1x = 225;
//		float p1y =300;
//		
//		float p2x = 325;
//		float p2y = 180;
//		
//	//	float angle = this.calcAngle(p2x, p2y,p1x,p1y);
//		
//		c.drawCircle(p1x * ratioX,p1y * ratioY,10, players1.get(0).mPaint);
//		
//		c.drawCircle(p2x * ratioX,p2y * ratioY,10, players2.get(0).mPaint);
//		
//		
//	//	float newX = (float) (150 * Math.sin(angle));
//		
//	//	float newY =  (float) (150 * Math.cos(angle));
//		
//		//c.drawLine(p2x, p2y, p2x+newX, p2y+newY,  players2.get(0).mPaint);
//		
//		float angle = this.calcAngle(p1x, p1y,this.b.x,this.b.y );
//		
//		float newX = (float) (150 * Math.sin(angle));
//		float newY =  (float) (150 * Math.cos(angle));
//		
//		c.drawLine(p1x * ratioX, p1y * ratioY, (p1x+newX) * ratioX, (p1y+newY) * ratioY,  players2.get(0).mPaint);
//		
//	
//			
//		for (angle=0;angle < 2 * Math.PI;angle += Math.PI/8)
//		{
//			 newX = (float) (150 * Math.sin(angle));
//			 newY =  (float) (150 * Math.cos(angle));
//			 
//			 c.drawLine(200 * ratioX,200 * ratioY, (200+newX) * ratioX, (200+newY) * ratioY,  players2.get(0).mPaint);
//			 c.drawText("ANG="+Float.toString(angle),(200+newX) * ratioX, (200+newY) * ratioY,  players2.get(0).mPaint);
//		}
//		
//	
		}


}
