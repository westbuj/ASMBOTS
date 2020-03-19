package com.jnn.asm.bots;

import java.util.Random;

import com.jnn.asm.soccer.Team;
import com.jnn.asm.util.ANR;
import com.jnn.asm.bots.R;


import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Spinner;

public class LoadThread extends Thread implements Runnable {

	public static boolean runRenderThread=true;
	static boolean renderingStopped=false;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		ANR.setupStorage("asmbots");
  
    
       // BotLoader.mSoundManager.initSounds(BotLoader.mContext);
        BotLoader.mSoundManager.addSound(1, R.raw.kick);
        BotLoader.mSoundManager.addSound(2, R.raw.start_game);
        BotLoader.mSoundManager.addSound(3, R.raw.goal);
        BotLoader.mSoundManager.addSound(4, R.raw.bump); 
       // BotLoader.mSoundManager.addSound(4, R.raw.applause);
        //BotLoader.mSoundManager.addSound(4, R.raw.intro);
		
     //   BotLoader.mSoundManager.playSound(4);
        Team team1=new Team();

        Team team2=new Team();
        
       // team1.addBot(new Bot(PlayerFactory.makeDefender(),game,game.getWidth()/2,0,350,400));
        
        Random r = new Random();
        
//        team1.addBot(new Bot(new PlayerCode("ball_chaser.bot"),Soccer.width/2,0,r.nextInt((int) Soccer.width),1000));
//        
//        team1.addBot(new Bot(new PlayerCode("ball_chaser_1.bot"),Soccer.width/2,0,r.nextInt((int) Soccer.width),1000));
//        team1.addBot(new Bot(new PlayerCode("defender_1a.bot"),Soccer.width/2,0,r.nextInt((int) Soccer.width),1000));
//        team1.addBot(new Bot(new PlayerCode("multi_talent.bot"),Soccer.width/2,0,r.nextInt((int) Soccer.width),1000));
//        team1.addBot(new Bot(new PlayerCode("multi_talent_a.bot"),Soccer.width/2,0,r.nextInt((int) Soccer.width),1000));
//        
//        team2.addBot(new Bot(new PlayerCode("ball_chaser.bot"),Soccer.width/2,0,r.nextInt((int) Soccer.width),1000));
//        team2.addBot(new Bot(new PlayerCode("ball_chaser_1.bot"),Soccer.width/2,0,r.nextInt((int) Soccer.width),1000));
//        team2.addBot(new Bot(new PlayerCode("defender_1a.bot"),Soccer.width/2,0,r.nextInt((int) Soccer.width),1000));
//        team2.addBot(new Bot(new PlayerCode("multi_talent.bot"),Soccer.width/2,0,r.nextInt((int) Soccer.width),1000));
//        team2.addBot(new Bot(new PlayerCode("multi_talent_a.bot"),Soccer.width/2,0,r.nextInt((int) Soccer.width),1000));
//        
        
        
//        BotLoader.game = new Soccer(BotLoader.iView,team1, team2);
       
        
       
	
    	 
    	 BotLoader.mode=BotLoader.READY;
	}
	
	

}
