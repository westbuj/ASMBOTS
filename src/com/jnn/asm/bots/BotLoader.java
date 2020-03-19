package com.jnn.asm.bots;





import java.util.ArrayList;


import com.jnn.asm.soccer.Soccer;
import com.jnn.asm.util.ANR;
import com.jnn.asm.util.SoundManager;
import com.jnn.asm.bots.R;

import com.jnn.ebook.TutorViewer;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class BotLoader extends Activity {
	
	public static Builder confirmAndAbort=null;
	public static boolean isMute = false;
	public static Bitmap gameOverSplash = null;
	
	 @Override 
	    public void onConfigurationChanged(Configuration newConfig) { 
	        super.onConfigurationChanged(newConfig); 
	        //---code to redraw your activity here---
	        //...
	    }

	 
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
    	if (resultCode>0)
    	{TextView botName=null;
    	switch (requestCode)
    	{
    	case 1:
    		botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_1_name);
    		teamReadiness[1]=true;
    		botName.setText(data.getExtras().getString("BOT_PATH"));
    		break;
    	case 2:
    		botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_2_name);
    		teamReadiness[2]=true;
    		botName.setText(data.getExtras().getString("BOT_PATH"));
    		break;
    	case 3:
    		botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_3_name);
    		teamReadiness[3]=true;
    		botName.setText(data.getExtras().getString("BOT_PATH"));
    		break;
    	case 4:
    		botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_4_name);
    		teamReadiness[4]=true;
    		botName.setText(data.getExtras().getString("BOT_PATH"));
    		break;
    	case 5:
    		botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_5_name);
    		teamReadiness[5]=true;
    		botName.setText(data.getExtras().getString("BOT_PATH"));
    		break;
    	}
    	
    	//Can we start a match?
    	setMatchReady();
    		
    	}
    	
    	
    	
		super.onActivityResult(requestCode, resultCode, data);
	}
    public static void setMatchReady()
    {
    	
    		TextView botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_1_name);
    		if (ANR.isFile(botName.getText().toString()))
    			teamReadiness[1]=true;
    		else
    			teamReadiness[1]=false;
    		
    		botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_2_name);
    		if (ANR.isFile(botName.getText().toString()))
    			teamReadiness[2]=true;
    		else
    			teamReadiness[2]=false;
    		
    		botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_3_name);
    		if (ANR.isFile(botName.getText().toString()))
    			teamReadiness[3]=true;
    		else
    			teamReadiness[3]=false;
    		
    		botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_4_name);
    		if (ANR.isFile(botName.getText().toString()))
    			teamReadiness[4]=true;
    		else
    			teamReadiness[4]=false;
    		
    		botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_5_name);
    		if (ANR.isFile(botName.getText().toString()))
    			teamReadiness[5]=true;
    		else
    			teamReadiness[5]=false;
    			
    		
   	boolean start=true;
   	for (int j=1;j<=5;j++)
       	if (!teamReadiness[j])
   				start=false;
    	  	
   	
    	if (start)
    		{Button start_button_mirror = (Button) teamSelectView.findViewById(R.id.start_game_mirror);
    		start_button_mirror.setVisibility(View.VISIBLE);
    		
    		start_button_mirror = (Button) teamSelectView.findViewById(R.id.start_adv_game);
    		start_button_mirror.setVisibility(View.VISIBLE);
    		
    	//	start_button_mirror = (Button) teamSelectView.findViewById(R.id.start_easy_game);
    	//	start_button_mirror.setVisibility(View.VISIBLE);
    		
    		}
    	else
    	{Button start_button_mirror = (Button) teamSelectView.findViewById(R.id.start_game_mirror);
		start_button_mirror.setVisibility(View.INVISIBLE);
		
		start_button_mirror = (Button) teamSelectView.findViewById(R.id.start_adv_game);
		start_button_mirror.setVisibility(View.INVISIBLE);
		
		//start_button_mirror = (Button) teamSelectView.findViewById(R.id.start_easy_game);
		//start_button_mirror.setVisibility(View.INVISIBLE);
		}
    		
    	
    	}
    	
 
	/** Called when the activity is first created. */
    public static boolean[] teamReadiness = new boolean[]{false,false,false,false,false,false};
	public static Soccer game;
	public static MyView iView=null;
	 public static SoundManager mSoundManager;
	 public static boolean isDebug=true;
	 public static View dbV=null;
	 public static View launch=null;
	 public static View teamSelectView=null;
	 public static boolean stepping=false;
	 public static boolean waitForNext=true;
	 public static Context mContext;
	 public static boolean codeViewMode=false;
	 public static int speed=15;
	 public static final int LOADING=1;
	 public static final int READY=2;
	 public static final int PLAYING=3;
	 public static final int GAMEOVER=4;
	 public static Spinner botSelect=null;
	// public static final int QUITTING=4;
	 
	 public static boolean isInitialized = false;
	 
	public static int mode = LOADING;
	public static ButtonHandler butHand = new ButtonHandler();
	
	public void initialize(){
		mContext = this.getBaseContext();
        
        BotLoader.mSoundManager = new SoundManager();
        BotLoader.mSoundManager.initSounds(BotLoader.mContext); 
        
        
        ANR.setupStorage("asmbots");
        
        
        String contents = ANR.getRawResourceAsStringByName(getResources(),"easy_1");
        if (!ANR.isFile("easy_1.bot"))
          	ANR.saveXMLFile(contents,"easy_1.bot");
        
        contents = ANR.getRawResourceAsStringByName(getResources(),"easy_2");
        if (!ANR.isFile("easy_2.bot"))
          	ANR.saveXMLFile(contents,"easy_2.bot");
        
        contents = ANR.getRawResourceAsStringByName(getResources(),"easy_3");
        if (!ANR.isFile("easy_3.bot"))
          	ANR.saveXMLFile(contents,"easy_3.bot");
        
        contents = ANR.getRawResourceAsStringByName(getResources(),"easy_4");
        if (!ANR.isFile("easy_4.bot"))
          	ANR.saveXMLFile(contents,"easy_4.bot");
        
        contents = ANR.getRawResourceAsStringByName(getResources(),"easy_5");
        if (!ANR.isFile("easy_5.bot"))
          	ANR.saveXMLFile(contents,"easy_5.bot");
        
        contents = ANR.getRawResourceAsStringByName(getResources(),"ace_attack");
        if (!ANR.isFile("ace.bot"))
          	ANR.saveXMLFile(contents,"ace.bot");
      
        contents = ANR.getRawResourceAsStringByName(getResources(),"ace_l_wing");
        if (!ANR.isFile("ace_l_wing.bot"))
          	ANR.saveXMLFile(contents,"ace_l_wing.bot");
        
        contents = ANR.getRawResourceAsStringByName(getResources(),"ace_r_wing");
        if (!ANR.isFile("ace_r_wing.bot"))
          	ANR.saveXMLFile(contents,"ace_r_wing.bot");
        
contents = ANR.getRawResourceAsStringByName(getResources(),"test_bot");
        
        if (!ANR.isFile("test_bot.bot"))
              	ANR.saveXMLFile(contents,"test_bot.bot");
        
        
       
        
      
        
              
 
        iView=new MyView(this);
       
        iView.setBackgroundResource(R.drawable.field);
        setContentView(iView);
        
        
        
        
        
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        launch = inflater.inflate(R.layout.launch, null);
        
        addContentView(launch, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
        
        Button but = (Button) launch.findViewById(R.id.start_button);
        but.setTag("TEAM_SELECT_SHOW");
        but.setOnClickListener(BotLoader.butHand);
        
        
        
        
        but = (Button) launch.findViewById(R.id.tutorial_button);
        but.setTag("LAUNCH_TUTORIAL");
        but.setOnClickListener(BotLoader.butHand);
        
        
        but = (Button) launch.findViewById(R.id.news_button);
        but.setTag("LAUNCH_NEWS");
        but.setOnClickListener(BotLoader.butHand);
       
        
        but = (Button) launch.findViewById(R.id.mute_button);
        but.setTag("TOGGLE_MUTE");
        but.setOnClickListener(BotLoader.butHand);
        
        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        teamSelectView = inflater.inflate(R.layout.teamconfigure, null);
        
        teamSelectView.setVisibility(View.INVISIBLE);
        addContentView(teamSelectView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
        
        but = (Button) teamSelectView.findViewById(R.id.cancel_sel_bot);
        but.setTag("CANCEL_TEAM_SELECT");
        but.setOnClickListener(BotLoader.butHand);
       
        
        
        
        but = (Button) teamSelectView.findViewById(R.id.start_game_mirror);
        but.setTag("START_GAME_MIRROR");
        but.setOnClickListener(BotLoader.butHand);
       
        but = (Button) teamSelectView.findViewById(R.id.start_adv_game);
        but.setTag("START_ADV_GAME");
        but.setOnClickListener(BotLoader.butHand);
        
      //  but = (Button) teamSelectView.findViewById(R.id.start_easy_game);
        //but.setTag("START_EASY_GAME");
        //but.setOnClickListener(BotLoader.butHand);
        
        
        View.OnClickListener VOC = new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Intent myIntent = new Intent(BotLoader.mContext, SelectBotActivity.class);
    	    	startActivityForResult(myIntent,((Integer)view.getTag()).intValue());
    		    }
    	    };
    	        
        
        
        Button selBot = (Button) teamSelectView.findViewById(R.id.sel_bot_1);
        selBot.setTag(new Integer(1));
        selBot.setOnClickListener(VOC);
        
          
       

        
        selBot = (Button) teamSelectView.findViewById(R.id.sel_bot_2);
        selBot.setTag(new Integer(2));
        selBot.setOnClickListener(VOC);
        
        
        selBot = (Button) teamSelectView.findViewById(R.id.sel_bot_3);
        selBot.setTag(new Integer(3));
        selBot.setOnClickListener(VOC);
        
        
        
        selBot = (Button) teamSelectView.findViewById(R.id.sel_bot_4);
        selBot.setTag(new Integer(4));
        selBot.setOnClickListener(VOC);
        
        
        
        selBot = (Button) teamSelectView.findViewById(R.id.sel_bot_5);
        selBot.setTag(new Integer(5));
        selBot.setOnClickListener(VOC);
        
        
        
        
        
        
        
    //    this.dbV = new DebugView(this);
    
        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dbV = inflater.inflate(R.layout.debug, null);
        
        but = (Button) dbV.findViewById(R.id.leave_button);
        but.setTag("LEAVE_GAME");
        but.setOnClickListener(BotLoader.butHand);
       
        
       but = (Button) dbV.findViewById(R.id.nxt_button);
       but.setTag("STEP_BUTTON");
       but.setOnClickListener(BotLoader.butHand);
      
       but = (Button) dbV.findViewById(R.id.run_button);
        but.setTag("RUN_BUTTON");
        but.setOnClickListener(BotLoader.butHand);
      
        
        but = (Button) dbV.findViewById(R.id.debug_MUTE_button);
        but.setTag("TOGGLE_MUTE");
        but.setOnClickListener(BotLoader.butHand);
        
        Button codeStep = (Button) dbV.findViewById(R.id.cod_button);
        codeStep.setOnClickListener(new View.OnClickListener() {
        	    public void onClick(View view) {
        	    	Intent myIntent = new Intent(BotLoader.mContext, CodeViewActivity.class);
        	    	myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        			mContext.startActivity(myIntent);
        			BotLoader.dbV.setVisibility(View.INVISIBLE);                           	    	
        	            }
        	        });
        
        SeekBar gameSpeed = (SeekBar) dbV.findViewById(R.id.gameSpeed);
        gameSpeed.setMax(1000);
      
        
        gameSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

        	   public void onProgressChanged(SeekBar seekBar, int progress,
        	     boolean fromUser) {
        		   
        		   speed= 1000 - progress;
        		   MyView.extendTimeOut();

        	   }

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
        	  });

        	
        
        
        Button hideView = (Button) dbV.findViewById(R.id.hide_button);
        hideView.setTag("HIDE_MF_CP");
        
        hideView.setOnClickListener(butHand);
        
        addContentView(dbV, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
      
        
     //   game = new Soccer(iView);
        
       
				
 botSelect = (Spinner)BotLoader.dbV.findViewById(R.id.debug_bot_sel);
        


    	 
    	 
    	 botSelect.setOnItemSelectedListener(new OnItemSelectedListener() {
	            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
	                // your code here
	            	
	              Soccer.cDebugBot=(int)(parentView.getItemIdAtPosition(position));
	              MyView.extendTimeOut();
	              BotLoader.game.mView.updateImage.sendEmptyMessage(1);	
	            	
	            }

	            public void onNothingSelected(AdapterView<?> parentView) {
	                // your code here
	            }

				
	        });

    	 
    	 
    	 
    	 
		dbV.setVisibility(View.INVISIBLE);
		
		BotLoader.game = new Soccer(BotLoader.iView);
		
		LoadThread l = new LoadThread();
        Thread gameThread = new Thread(l);
        //gameThread.start();
        gameThread.run();
        
        
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        

	  initialize();
	   
      BotLoader.gameOverSplash = ANR.getRawResourceAsBitmapByName(getResources(),"match_over");
      
   
	  isInitialized=true;
	  
	  try{
	  
	  if (ANR.isFile("socteam.set"))
	  {
		  String teamSet =  ANR.getFileContentsAsString("socteam.set");
		  ArrayList<String> s = ANR.textToLines(teamSet);
		  if (s.get(0).toUpperCase().startsWith("T"))
			  BotLoader.isMute=true;
		  else
			  BotLoader.isMute=false;
		  
		  TextView tv = null;
		  if (s.get(1).length()>0)
			  {tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_1_name);
			   tv.setText(s.get(1)); 
			  }
		  
		  
		  if (s.get(2).length()>0)
		  {tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_2_name);
		   tv.setText(s.get(2)); 
		  }
		  
		  
		  if (s.get(3).length()>0)
		  {tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_3_name);
		   tv.setText(s.get(3)); 
		  }
		  
		  
		  if (s.get(4).length()>0)
		  {tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_4_name);
		   tv.setText(s.get(4)); 
		  }
		  
		  
		  
		  if (s.get(5).length()>0)
		  {tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_5_name);
		   tv.setText(s.get(5)); 
		  }
		  
		  
	  }
	  }catch (Exception e){};

  mContext = this.getBaseContext();
  
	
	confirmAndAbort = new AlertDialog.Builder(this);
	
	confirmAndAbort.setIcon(android.R.drawable.ic_dialog_alert);
     confirmAndAbort.setTitle("Mainframe Interrupt");
     confirmAndAbort.setMessage("If you leave, the Arena Mainframe will disconnect.\n\nUnsaved code changes will be lost.\n\nDo you want to abort this match?\n");
     
     confirmAndAbort.setNegativeButton("No",  new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int which){}});
     confirmAndAbort.setPositiveButton("Yes", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int which){
    	 																							// Exit the activity
    	 																							BotLoader.stepping= false;
    	 																							BotLoader.waitForNext=false;
    	 																							BotLoader.dbV.setVisibility(View.INVISIBLE);
    	 																							BotLoader.mode=BotLoader.READY;
    	 																							BotLoader.launch.setVisibility(View.VISIBLE);
    	 																							BotLoader.iView.updateImage.sendEmptyMessage(1);
     																								}
     																					});
	
        
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
          
        	if (BotLoader.mode != BotLoader.READY)
        	{
        		confirmAndAbort.show();
        		
                return true;
        	}
        	
        }
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
      
        menu.add(0, 1, 0, "Documentation").setShortcut('3', 'c');
      
        
        return result;//super.onCreateOptionsMenu(menu);
    }
 
 @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       

        switch (item.getItemId()) {
            case 1:
            	Intent myIntent = new Intent(BotLoader.mContext,TutorViewer.class);
    	    	myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    			BotLoader.mContext.startActivity(myIntent);
            	return true;
            
                  }
        return super.onOptionsItemSelected(item);
    }


@Override
protected void onPause() {
	// TODO Auto-generated method stub
	try{
	if (this.isFinishing())
	{  
		String oString=Boolean.toString(BotLoader.isMute) + "\n";
	
		TextView tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_1_name);
		oString += tv.getText() + "\n";
	
		tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_2_name);
		oString += tv.getText() + "\n";
	
		tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_3_name);
		oString += tv.getText() + "\n";
	
		tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_4_name);
		oString += tv.getText() + "\n";
	
		tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_5_name);
		oString += tv.getText() + "\n";
	
		ANR.saveXMLFile(oString,"socteam.set");
	}
	}
	catch (Exception e){};
	
	
	super.onPause();
}
 
// @Override
// public void onSaveInstanceState(Bundle savedInstanceState) {
//   // Save UI state changes to the savedInstanceState.
//   // This bundle will be passed to onCreate if the process is
//   // killed and restarted.
//   savedInstanceState.putBoolean("mute", BotLoader.isMute);
//   
//   
//   TextView tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_1_name);
//   savedInstanceState.putString("bot1", (String) tv.getText());
//   
//   tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_2_name);
//   savedInstanceState.putString("bot2", (String) tv.getText());
//   
//   tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_3_name);
//   savedInstanceState.putString("bot3", (String) tv.getText());
//   
//   tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_4_name);
//   savedInstanceState.putString("bot4", (String) tv.getText());
//   
//   tv = (TextView)BotLoader.teamSelectView.findViewById(R.id.sel_bot_5_name);
//   savedInstanceState.putString("bot5", (String) tv.getText());
//   
//   // etc.
//   super.onSaveInstanceState(savedInstanceState);
// }


}