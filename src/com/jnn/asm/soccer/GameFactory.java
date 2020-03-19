package com.jnn.asm.soccer;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.jnn.asm.bots.Bot;
import com.jnn.asm.bots.BotLoader;
import com.jnn.asm.bots.HideThread;
import com.jnn.asm.bots.PlayerCode;
import com.jnn.asm.bots.R;
import com.jnn.asm.util.ANR;
import com.jnn.asm.util.CMath;

public class GameFactory {
	
	public static void produceHardGame(Resources res){
		
		Soccer.team1.empty();
        Soccer.team2.empty();
        Soccer.team1.points=0;
        Soccer.team2.points=0;
        Soccer.players.clear();
			

      Soccer.team1.goaly = 0;
      Soccer.team2.goaly = Soccer.height;
      
 	BotLoader.teamSelectView.setVisibility(View.INVISIBLE);
 	TextView botName;
 	botName = (TextView) BotLoader.teamSelectView.findViewById(R.id.sel_bot_1_name);
		
 	
		PlayerCode pc1=new PlayerCode(botName.getText().toString());
		PlayerCode pc2=new PlayerCode("ATACE.bot",ANR.getRawResourceAsStringByName(res,("ace_attack")));
	    
	Bot nBot=new Bot(pc1,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
	Bot nBot2=new Bot(pc2,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
	
	Soccer.team1.addBot(nBot);
	Soccer.team2.addBot(nBot2);
	nBot2.isReversed=true;
	
	 
	 
	
	
	botName = (TextView) BotLoader.teamSelectView.findViewById(R.id.sel_bot_2_name);
	
	
	 pc1=new PlayerCode(botName.getText().toString());
	 pc2=new PlayerCode("LWINGACE.bot",ANR.getRawResourceAsStringByName(res,"ace_l_wing"));
 
nBot=new Bot(pc1,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
nBot2=new Bot(pc2,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
	
	Soccer.team1.addBot(nBot);
	Soccer.team2.addBot(nBot2);
	nBot2.isReversed=true;
	
	
	
	
	
	botName = (TextView) BotLoader.teamSelectView.findViewById(R.id.sel_bot_3_name);
	
	
	
	
	 pc1=new PlayerCode(botName.getText().toString());
	 pc2=new PlayerCode("RWINGACE.bot",ANR.getRawResourceAsStringByName(res,("ace_r_wing")));
 
nBot=new Bot(pc1,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
nBot2=new Bot(pc2,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
	

	Soccer.team1.addBot(nBot);
	Soccer.team2.addBot(nBot2);
	nBot2.isReversed=true;
	
	
	
	
	botName = (TextView) BotLoader.teamSelectView.findViewById(R.id.sel_bot_4_name);
	
	
	
	 pc1=new PlayerCode(botName.getText().toString());
	 pc2=new PlayerCode("DEFACE.bot",ANR.getRawResourceAsStringByName(res,"ace_defend"));
 
nBot=new Bot(pc1,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
nBot2=new Bot(pc2,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
	
		
	Soccer.team1.addBot(nBot);
	Soccer.team2.addBot(nBot2);
	nBot2.isReversed=true;
	
	
	
	
	botName = (TextView) BotLoader.teamSelectView.findViewById(R.id.sel_bot_5_name);
	
	
	
	
	 pc1=new PlayerCode(botName.getText().toString());
	 pc2=new PlayerCode("GOALACE.bot",ANR.getRawResourceAsStringByName(res,"ace_goalie"));
 
nBot=new Bot(pc1,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
nBot2=new Bot(pc2,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
	
	
	Soccer.team1.addBot(nBot);
	Soccer.team2.addBot(nBot2);
	nBot2.isReversed=true;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		BotLoader.game.initTeams();
		BotLoader.mode=BotLoader.PLAYING;
		
		Thread gameThread = new Thread(BotLoader.game);
		gameThread.start();
	
		HideThread ht = new HideThread();
		Thread hThread = new Thread(ht);
		hThread.start();
		
		
	}
	public static void produceMirrorGame(){
		
		Soccer.team1.empty();
        Soccer.team2.empty();
        Soccer.team1.points=0;
        Soccer.team2.points=0;
        Soccer.players.clear();
			

      Soccer.team1.goaly = 0;
      Soccer.team2.goaly = Soccer.height;
      
 	BotLoader.teamSelectView.setVisibility(View.INVISIBLE);
 	TextView botName;
 	botName = (TextView) BotLoader.teamSelectView.findViewById(R.id.sel_bot_1_name);
		
 	
		PlayerCode pc1=new PlayerCode(botName.getText().toString());
		PlayerCode pc2=new PlayerCode(botName.getText().toString());
	    
	Bot nBot=new Bot(pc1,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
	Bot nBot2=new Bot(pc2,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
	
	Soccer.team1.addBot(nBot);
	Soccer.team2.addBot(nBot2);
	nBot2.isReversed=true;
	
	 
	 
	
	
	botName = (TextView) BotLoader.teamSelectView.findViewById(R.id.sel_bot_2_name);
	
	
	 pc1=new PlayerCode(botName.getText().toString());
	 pc2=new PlayerCode(botName.getText().toString());
 
nBot=new Bot(pc1,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
nBot2=new Bot(pc2,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
	
	Soccer.team1.addBot(nBot);
	Soccer.team2.addBot(nBot2);
	nBot2.isReversed=true;
	
	
	
	
	
	botName = (TextView) BotLoader.teamSelectView.findViewById(R.id.sel_bot_3_name);
	
	
	
	
	 pc1=new PlayerCode(botName.getText().toString());
	 pc2=new PlayerCode(botName.getText().toString());
 
nBot=new Bot(pc1,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
nBot2=new Bot(pc2,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
	

	Soccer.team1.addBot(nBot);
	Soccer.team2.addBot(nBot2);
	nBot2.isReversed=true;
	
	
	
	
	botName = (TextView) BotLoader.teamSelectView.findViewById(R.id.sel_bot_4_name);
	
	
	
	 pc1=new PlayerCode(botName.getText().toString());
	 pc2=new PlayerCode(botName.getText().toString());
 
nBot=new Bot(pc1,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
nBot2=new Bot(pc2,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
	
		
	Soccer.team1.addBot(nBot);
	Soccer.team2.addBot(nBot2);
	nBot2.isReversed=true;
	
	
	
	
	botName = (TextView) BotLoader.teamSelectView.findViewById(R.id.sel_bot_5_name);
	
	
	
	
	 pc1=new PlayerCode(botName.getText().toString());
	 pc2=new PlayerCode(botName.getText().toString());
 
nBot=new Bot(pc1,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
nBot2=new Bot(pc2,Soccer.width/2,0,CMath.rnd.nextInt((int) Soccer.width),0);
	
	
	Soccer.team1.addBot(nBot);
	Soccer.team2.addBot(nBot2);
	nBot2.isReversed=true;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		BotLoader.game.initTeams();
		BotLoader.mode=BotLoader.PLAYING;
		
		Thread gameThread = new Thread(BotLoader.game);
		gameThread.start();
	
		HideThread ht = new HideThread();
		Thread hThread = new Thread(ht);
		hThread.start();
		
	}

}
