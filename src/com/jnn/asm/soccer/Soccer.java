package com.jnn.asm.soccer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

import com.jnn.asm.bots.Bot;
import com.jnn.asm.bots.BotLoader;
import com.jnn.asm.bots.CodeViewActivity;
import com.jnn.asm.bots.GameField;
import com.jnn.asm.bots.ListAdapter;
import com.jnn.asm.bots.MyView;
import com.jnn.asm.util.CMath;
import com.jnn.bots.command.ANGLCommand;
import com.jnn.bots.command.COMMANDS;
import com.jnn.bots.command.DECCommand;
import com.jnn.bots.command.DISTCommand;
import com.jnn.bots.command.DIVCommand;
import com.jnn.bots.command.DSETCommand;
import com.jnn.bots.command.GETOCommand;
import com.jnn.bots.command.GETTCommand;
import com.jnn.bots.command.INCCommand;
import com.jnn.bots.command.JMPACommand;
import com.jnn.bots.command.JUBTCommand;
import com.jnn.bots.command.JUGTCommand;
import com.jnn.bots.command.JULTCommand;
import com.jnn.bots.command.JUXTCommand;
import com.jnn.bots.command.KICKCommand;
import com.jnn.bots.command.LNPTCommand;
import com.jnn.bots.command.MULTCommand;
import com.jnn.bots.command.NOPCommand;
import com.jnn.bots.command.POPCommand;
import com.jnn.bots.command.PUSHCommand;
import com.jnn.bots.command.RSETCommand;
import com.jnn.bots.command.YELDCommand;



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
	public MyView mView = null;
	public static Hashtable<String, COMMANDS> commands = new Hashtable<String, COMMANDS>();
	public static ArrayList<String> registers = new ArrayList<String>();
	//public static int intensity =0;
	Paint strokePaint=new Paint();
	Paint debugBotPaint=new Paint();
	//public static int team1Score;
	//public static int team2Score;
	 
	public static int ip;
	//temps
	float newX;
	float newY;
	float x1;
	float y1;
	float x;
	float y;
	float speed;
	float direction;
	public static float goalMinX=0;
	public static float goalMaxX=0;
	public Soccer(MyView iView){
		mView=iView;
		
		
		
		goalMinX=width/3;
		goalMaxX=2*(width/3);
		
		
		commands.put("RSET",new RSETCommand());
		commands.put("KICK", new KICKCommand());
		commands.put("INC", new INCCommand());
		commands.put("JMPA", new JMPACommand());
		commands.put("JULT", new JULTCommand());
		commands.put("NOP", new NOPCommand());
		commands.put("LNPT", new LNPTCommand());
		commands.put("ANGL", new ANGLCommand());
		commands.put("DSET", new DSETCommand());
		commands.put("YELD", new YELDCommand());
		commands.put("JUBT", new JUBTCommand());
		commands.put("GETO", new GETOCommand());
		commands.put("GETT", new GETTCommand());
		commands.put("JUGT", new JUGTCommand());
		commands.put("DEC", new DECCommand());
		commands.put("MULT", new MULTCommand());
		commands.put("DIV", new DIVCommand());
		commands.put("DIST", new DISTCommand());
		commands.put("JUXT", new JUXTCommand());
		commands.put("PUSH", new PUSHCommand());
		commands.put("POP", new POPCommand());
		
		
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
		registers.add("NMOD");
		registers.add("GOLR");
		registers.add("GOLX");
		registers.add("GOLY");
	
		registers.add("RET1");
		registers.add("RET2");
		registers.add("RET3");
		registers.add("RET4");
		registers.add("RET5");
		
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
		
		
		strokePaint.setStyle(Paint.Style.STROKE);
		strokePaint.setColor(Color.WHITE);
		
		debugBotPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		debugBotPaint.setColor(Color.LTGRAY);
		
		team1=new Team();
		team2=new Team();
	
		
	}
	
	
	public void initTeams()
	{
		
		String[][] botSel = new String[5][2];
		
		for(int j=0;j<5;j++)
		{
			team1.getBot(j).team=1;
			team2.getBot(j).team=2;
			team1.getBot(j).id=addPlayer(team1.getBot(j),1);
			team2.getBot(j).id=addPlayer(team2.getBot(j),2);
			team2.getBot(j).isReversed=true;
			
			team1.getBot(j).mPaint.setColor(Color.BLUE);
			team2.getBot(j).mPaint.setColor(Color.RED);
			
			botSel[j][0]=Integer.toString(team1.getBot(j).id);
			botSel[j][1]=team1.getBot(j).mCode.fileName;
			
		}
		cDebugBot = team1.getBot(0).id;
//		team1.goaly=1000;
//		team2.goaly=0;
		
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
		
		x1=0;
		y1=0;
		b.x=CMath.MAX_X/2;
		b.y=CMath.MAX_Y/2;
		b.speed=0;
	//	Random randomGenerator = new Random();
		
		if (Soccer.team1.goaly == Soccer.height)
			Soccer.team1.goaly = 0;
		else
			Soccer.team1.goaly = Soccer.height;
		
		if (Soccer.team2.goaly == Soccer.height)
			Soccer.team2.goaly = 0;
		else
			Soccer.team2.goaly = Soccer.height;
			
			
			
		for(int j=0; j < players.size();j++)
		{
		  //REverseSIdes
		//  players.get(j).isReversed= !players.get(j).isReversed;
			
		 players.get(j).regs.get("POSX").mValue=CMath.revX(players.get(j).regs.get("POSX").mValue);
		 players.get(j).regs.get("POSY").mValue=CMath.revY(players.get(j).regs.get("POSY").mValue);
		  players.get(j).isReversed= !players.get(j).isReversed;
		  
		  if (j == 0 || j == 1)
		  {
			  players.get(j).regs.get("TRGX").mValue = 250;
			  players.get(j).regs.get("TRGY").mValue= 650;
		  }
		  if (j == 2 || j == 3)
		  {
			  players.get(j).regs.get("TRGX").mValue = 100;
			  players.get(j).regs.get("TRGY").mValue= 750;
		  }
		  if (j == 4 || j == 5)
		  {
			  players.get(j).regs.get("TRGX").mValue = 400;
			  players.get(j).regs.get("TRGY").mValue= 750;
		  }
		  if (j == 6 || j == 7)
		  {
			  players.get(j).regs.get("TRGX").mValue = 150;
			  players.get(j).regs.get("TRGY").mValue= 850;
		  }
		  if (j == 8 || j == 9)
		  {
			  players.get(j).regs.get("TRGX").mValue = 350;
			  players.get(j).regs.get("TRGY").mValue= 850;
		  }
		}
		
		boolean moving = true;
		while (moving && BotLoader.mode==BotLoader.PLAYING)
			
		{	moving=false;	
		for(int j=0; j < players.size();j++)
		{
			if (CMath.distance(players.get(j).regs.get("POSX").mValue,players.get(j).regs.get("POSY").mValue,players.get(j).regs.get("TRGX").mValue,players.get(j).regs.get("TRGY").mValue) > 5)
				moving=true;
		
			 
			
		
			
//		 direction=0;
//		if (players.get(j).isReversed)
//			direction = CMath.revA(CMath.calcAngle( players.get(j).regs.get("POSX").mValue,players.get(j).regs.get("POSY").mValue,players.get(j).regs.get("TRGX").mValue, players.get(j).regs.get("TRGY").mValue));
//		else
			direction = CMath.calcAngle( players.get(j).regs.get("POSX").mValue,players.get(j).regs.get("POSY").mValue,players.get(j).regs.get("TRGX").mValue, players.get(j).regs.get("TRGY").mValue);
		
//			if (players.get(j).isReversed)
//				direction = CMath.revA(direction);
		
		speed=2.12f;
//		if (players.get(j).isReversed)
//			{x1 = CMath.revX(players.get(j).regs.get("POSX").mValue);
//			y1 = CMath.revY(players.get(j).regs.get("POSY").mValue);
//			}
//		else
//		{
			x1 = (players.get(j).regs.get("POSX").mValue);
			y1 = (players.get(j).regs.get("POSY").mValue);
		//}
		
		
		y1 += speed * Math.cos(direction);
			
		x1 += speed * Math.sin(direction);
		
//			
//			if (players.get(j).isReversed)
//			{
//				players.get(j).regs.get("POSX").mValue=CMath.revX(x1);
//				players.get(j).regs.get("POSY").mValue=CMath.revY(y1);
//				players.get(j).regs.get("DIR").mValue=CMath.revA(direction);
//			}
//			else
//			{
				players.get(j).regs.get("POSX").mValue = x1;
				players.get(j).regs.get("POSY").mValue = y1;
				players.get(j).regs.get("DIR").mValue=direction;
		//	}
		
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
		Soccer.gameTime=0;
		movePlayersToStart();
		 BotLoader.mSoundManager.playSound(2);
		
		
		//boolean playing =true;
		while (Soccer.gameTime <= 9999 && BotLoader.mode == BotLoader.PLAYING)
		{
			
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
					
					ip = players.get(j).regs.get("IP").intValue();
					
					if (ip >= players.get(j).mCode.code.size())
					{players.get(j).regs.get("IP").mValue=0;
					 ip=0;
					}
					
					cStep = players.get(j).mCode.code.get(ip);
					//cStep= players.get(j).getNextStep().toString();
					//boolean wasStepping = BotLoader.stepping;
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
				 BotLoader.mSoundManager.playSound(3);
				movePlayersToStart();
			    GOAL=false;
			    BotLoader.mSoundManager.playSound(2);
			} 
		
			b.speed = (float) (b.speed - .08);
			if (b.speed < 0)
				b.speed=0;
			
			//View.invalidate();
			mView.updateImage.sendEmptyMessage(1);
		}
		 BotLoader.mSoundManager.playSound(2);
	
		if (BotLoader.mode==BotLoader.PLAYING)
			{BotLoader.mode=BotLoader.GAMEOVER;
				//BotLoader.mSoundManager.playSound(4);
				movePlayersToStart();
			}
	}


	public void draw(Canvas c, View v) {
		// TODO Auto-generated method stub
		int nWid = v.getWidth();
		int nHid = v.getHeight();
		 
		
//		c.drawText("w="+Integer.toString(nWid),20, 50,b.ballPaint);
	//	c.drawText("h="+Integer.toString(nHid),20, 70,b.ballPaint);
		
		float ratioX = (float)v.getWidth()/Soccer.width;
		float ratioY = (float)v.getHeight()/Soccer.height;
		float angle2Ball=0;
		
		c.drawText(" "+Integer.toString(Soccer.gameTime),v.getWidth()-50, 50,b.ballPaint);
		
		c.drawText(Integer.toString(Soccer.team1.points),50,75,Soccer.team1.players.get(0).mPaint);
	//	c.drawText(" to ",57,75,b.ballPaint);
		c.drawText(Integer.toString(Soccer.team2.points),v.getWidth()-50,v.getHeight()-50,Soccer.team2.players.get(0).mPaint);
		
		
		
		c.drawRect(1,1, v.getWidth()-2,v.getHeight()-2, strokePaint);
		c.drawLine(1, v.getHeight()/2, v.getWidth()-2, v.getHeight()/2, strokePaint);
		c.drawCircle(v.getWidth()/2, v.getHeight()/2, v.getHeight()/11 , strokePaint);
		
		c.drawRect(v.getWidth()/3,1, 2*(v.getWidth()/3),v.getHeight()/11, strokePaint);
		c.drawRect(v.getWidth()/3,v.getHeight()-(v.getHeight()/11), 2*(v.getWidth()/3),v.getHeight()-2, strokePaint);
		
		
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
				c.drawCircle(x, y, 10, debugBotPaint);
				//c.drawText(players.get(Soccer.cDebugBot).mCode.fileName, x, y,  players.get(j).mPaint);
				
				 newX = (float) ((players.get(j).regs.get("SPD").mValue*10) * Math.sin(angle2Ball));
				 newY =  (float) ((players.get(j).regs.get("SPD").mValue*10) * Math.cos(angle2Ball));
				c.drawLine( x,y, x+newX,y+newY,  players.get(j).mPaint);
			}
			c.drawCircle(x, y, 5, players.get(j).mPaint);
			// c.drawText("BDIS="+Float.toString( players1.get(j).getBallDistanceReg()),players1.get(j).getX() * ratioX, players1.get(j).getY() * ratioY,  players1.get(j).mPaint);
			
			 
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
