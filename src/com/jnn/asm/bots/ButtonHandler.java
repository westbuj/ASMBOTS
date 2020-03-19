package com.jnn.asm.bots;

import com.jnn.asm.soccer.GameFactory;
import com.jnn.asm.soccer.Soccer;
import com.jnn.asm.util.CMath;
import com.jnn.asm.bots.R;

import com.jnn.ebook.TutorViewer;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ButtonHandler implements OnClickListener {

	public void onClick(View arg0) {
		
		if (arg0.getTag().equals("TOGGLE_MUTE"))
		{
			BotLoader.isMute = !BotLoader.isMute;
			Button but = (Button) BotLoader.launch.findViewById(R.id.mute_button);
			if (BotLoader.isMute)
				but.setText("Turn Sound On"); 
			else
				but.setText("Mute Sounds");
			return;
		}
		
		if (arg0.getTag().equals("HIDE_MF_CP"))
		{
			BotLoader.dbV.setVisibility(View.INVISIBLE);  
			return;
		}
		if (arg0.getTag().equals("TEAM_SELECT_SHOW"))
		{
			BotLoader.launch.setVisibility(View.INVISIBLE);
			BotLoader.setMatchReady();
	    	BotLoader.teamSelectView.setVisibility(View.VISIBLE);  
			return;
		}
		if (arg0.getTag().equals("LAUNCH_TUTORIAL"))
		{

	    	
	    	Intent myIntent = new Intent(BotLoader.mContext,TutorViewer.class);
	    	myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			BotLoader.mContext.startActivity(myIntent);
			
			return;
		}
		
		if (arg0.getTag().equals("LAUNCH_NEWS"))
		{
	    	Intent i = new Intent(Intent.ACTION_VIEW);
	    	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.setData(Uri.parse("http://www.asmbots.com"));
			BotLoader.mContext.startActivity(i);
			
			return;
		}
		
		if (arg0.getTag().equals("SELECT_TEAM_MEMBER"))
		{
			Intent myIntent = new Intent(BotLoader.mContext, SelectBotActivity.class);
	    	//myIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
		//s	BotLoader.startActivityForResult(myIntent,1);
		
			return;
		}
		
		if (arg0.getTag().equals("CANCEL_TEAM_SELECT"))
		{
			BotLoader.launch.setVisibility(View.VISIBLE);
	    	BotLoader.teamSelectView.setVisibility(View.INVISIBLE);
//	    
			
			return;
		}
		
		if (arg0.getTag().equals("STEP_BUTTON"))
		{
		    BotLoader.stepping= true;
            BotLoader.waitForNext=false;
            MyView.extendTimeOut();
			return;
		}
		
		if (arg0.getTag().equals("RUN_BUTTON"))
		{
			BotLoader.stepping= !BotLoader.stepping;
            BotLoader.waitForNext=false;
            MyView.extendTimeOut();
			return;
		}
		
		if (arg0.getTag().equals("LEAVE_GAME"))
		{
			BotLoader.confirmAndAbort.show();           
			return;
		} 
		
		if (arg0.getTag().equals("START_GAME_MIRROR"))
		{
			
			GameFactory.produceMirrorGame();
			
			return;
		}
		

		if (arg0.getTag().equals("START_ADV_GAME"))
		{
			
			GameFactory.produceHardGame(BotLoader.mContext.getResources());
			
			return;
		}
		

	}

}