package com.jnn.bots;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;



public class SelectBotActivity extends ListActivity {
	
	
    static BotFileAdapter bfa = null;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	

		if (bfa == null)
			bfa = new BotFileAdapter(this);
		
		
		
		// Display it
		setListAdapter(bfa);

		//setContentView(l);
		

	        

	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent i = new Intent();
		i.putExtra("BOT_PATH",bfa.getItem(position).toString());
		setResult(1,i);
		finish();
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		PlayerCode pc=new PlayerCode(bfa.getItem(position).toString());
//		    
//		Bot nBot=new Bot(pc,Soccer.width/2,0,CMath.r.nextInt((int) Soccer.width),1000);
//		Bot nBot2=new Bot(pc,Soccer.width/2,0,CMath.r.nextInt((int) Soccer.width),1000);
//		
//		Soccer.team1.addBot(nBot);
//		Soccer.team2.addBot(nBot2);
//		nBot2.isReversed=true;
//		
//		if (Soccer.team1.players.size() >= 5)
//		{
//
//			BotLoader.game.initTeams();
//			BotLoader.mode=BotLoader.PLAYING;
//			
//			Thread gameThread = new Thread(BotLoader.game);
//			gameThread.start();
//			this.finish();
//		}

		
		
		
		
		
		
		
		
		
		
	}
	
	
}
