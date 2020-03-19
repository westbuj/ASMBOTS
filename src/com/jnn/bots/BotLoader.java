package com.jnn.bots;





import java.util.Random;

import com.jnn.asm.bots.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.view.LayoutInflater;
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
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
    	TextView botName=null;
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
    	boolean start=true;
    	for (int j=1;j<=5;j++)
    		if (!teamReadiness[j])
    			start=false;
    	
    	if (start)
    		{Button start_button_mirror = (Button) teamSelectView.findViewById(R.id.start_game_mirror);
    		start_button_mirror.setVisibility(View.VISIBLE);
    		}
    	else
    	{Button start_button_mirror = (Button) teamSelectView.findViewById(R.id.start_game_mirror);
		start_button_mirror.setVisibility(View.INVISIBLE);
		}
    		
    	
    	
    	
    	
		super.onActivityResult(requestCode, resultCode, data);
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
	 public static Spinner botSelect=null;
	// public static final int QUITTING=4;
	 
	 
	public static int mode = LOADING;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        
        
        
        
        
        mContext = this.getBaseContext();
        
        BotLoader.mSoundManager = new SoundManager();
        BotLoader.mSoundManager.initSounds(BotLoader.mContext); 
        
        
        ANR.setupStorage("asmbots");
        
        String contents = ANR.getRawResourceAsStringByName(getResources(),"multi_talent");
        
        if (!ANR.isFile("mt_a_bendor.bot"))
              	ANR.saveXMLFile(contents,"mt_a_bendor.bot");
       
        if (!ANR.isFile("mt_a_calculawn.bot"))
          	ANR.saveXMLFile(contents,"mt_a_calculawn.bot");
        
        if (!ANR.isFile("mt_a_r2.bot"))
          	ANR.saveXMLFile(contents,"mt_a_r2.bot");
        
        contents = ANR.getRawResourceAsStringByName(getResources(),"ball_chaser");
        
        if (!ANR.isFile("chase_baller.bot"))
          	ANR.saveXMLFile(contents,"chase_baller.bot");
   
        if (!ANR.isFile("ball_affine.bot"))
        	ANR.saveXMLFile(contents,"ball_affine.bot");
    
        if (!ANR.isFile("catchup.bot"))
        	ANR.saveXMLFile(contents,"catchup.bot");
        
        contents = ANR.getRawResourceAsStringByName(getResources(),"defender");
        
        if (!ANR.isFile("ace_defend_1.bot"))
          	ANR.saveXMLFile(contents,"ace_defend_1.bot");
   
        if (!ANR.isFile("homeboy.bot"))
        	ANR.saveXMLFile(contents,"homeboy.bot");
    
        if (!ANR.isFile("goal_denier.bot"))
        	ANR.saveXMLFile(contents,"goal_denier.bot");
        
              
 
        iView=new MyView(this);
       
        iView.setBackgroundResource(R.drawable.field);
        setContentView(iView);
        
        
        
        
        
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        launch = inflater.inflate(R.layout.launch, null);
        
        addContentView(launch, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
        
        Button start = (Button) launch.findViewById(R.id.start_button);
        start.setOnClickListener(new View.OnClickListener() {
        	    public void onClick(View view) {
        	    //	BotLoader.launch.setVisibility(View.INVISIBLE);
        	    	
        	    	launch.setVisibility(View.INVISIBLE);
        	    	teamSelectView.setVisibility(View.VISIBLE);
//        	    	
//        	    	Intent myIntent = new Intent(BotLoader.mContext, SelectBotActivity.class);
//        	    	myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        			mContext.startActivity(myIntent);
//        			
//        			
        			
        	            }
        	        });
        
        
        
        
        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        teamSelectView = inflater.inflate(R.layout.teamconfigure, null);
        
        teamSelectView.setVisibility(View.INVISIBLE);
        addContentView(teamSelectView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
        
        
        
        
        Button start_button_mirror = (Button) teamSelectView.findViewById(R.id.start_game_mirror);
        
        start_button_mirror.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	
    	    	teamSelectView.setVisibility(View.INVISIBLE);
    	    	TextView botName;
    	    	botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_1_name);
        		
    	    	
   			PlayerCode pc=new PlayerCode(botName.getText().toString());
    		    
    		Bot nBot=new Bot(pc,Soccer.width/2,0,CMath.r.nextInt((int) Soccer.width),900);
    		Bot nBot2=new Bot(pc,Soccer.width/2,0,CMath.r.nextInt((int) Soccer.width),900);
    		
    		Soccer.team1.addBot(nBot);
    		Soccer.team2.addBot(nBot2);
    		nBot2.isReversed=true;
    		
    		
    		
    		
    		
    		botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_2_name);
    		
	    	
   			pc=new PlayerCode(botName.getText().toString());
    		    
    		nBot=new Bot(pc,Soccer.width/2,0,CMath.r.nextInt((int) Soccer.width),900);
    		nBot2=new Bot(pc,Soccer.width/2,0,CMath.r.nextInt((int) Soccer.width),900);
    		
    		Soccer.team1.addBot(nBot);
    		Soccer.team2.addBot(nBot2);
    		nBot2.isReversed=true;
    		
    		
    		
    		
    		
    		botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_3_name);
    		
	    	
   			pc=new PlayerCode(botName.getText().toString());
    		    
    		nBot=new Bot(pc,Soccer.width/2,0,CMath.r.nextInt((int) Soccer.width),900);
    		nBot2=new Bot(pc,Soccer.width/2,0,CMath.r.nextInt((int) Soccer.width),900);
    		
    		Soccer.team1.addBot(nBot);
    		Soccer.team2.addBot(nBot2);
    		nBot2.isReversed=true;
    		
    		
    		
    		
    		botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_4_name);
    		
	    	
   			pc=new PlayerCode(botName.getText().toString());
    		    
    		nBot=new Bot(pc,Soccer.width/2,0,CMath.r.nextInt((int) Soccer.width),900);
    		nBot2=new Bot(pc,Soccer.width/2,0,CMath.r.nextInt((int) Soccer.width),900);
    		
    		Soccer.team1.addBot(nBot);
    		Soccer.team2.addBot(nBot2);
    		nBot2.isReversed=true;
    		
    		
    		
    		
    		botName = (TextView) teamSelectView.findViewById(R.id.sel_bot_5_name);
    		
	    	
   			pc=new PlayerCode(botName.getText().toString());
    		    
    		nBot=new Bot(pc,Soccer.width/2,0,CMath.r.nextInt((int) Soccer.width),900);
    		nBot2=new Bot(pc,Soccer.width/2,0,CMath.r.nextInt((int) Soccer.width),900);
    		
    		Soccer.team1.addBot(nBot);
    		Soccer.team2.addBot(nBot2);
    		nBot2.isReversed=true;
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    			BotLoader.game.initTeams();
    			BotLoader.mode=BotLoader.PLAYING;
    			
    			Thread gameThread = new Thread(BotLoader.game);
    			gameThread.start();
    		
    		 }
        });
        
        
        
        
        
        Button selBot = (Button) teamSelectView.findViewById(R.id.sel_bot_1);
        selBot.setOnClickListener(new View.OnClickListener() {
        	    public void onClick(View view) {
        	    	Intent myIntent = new Intent(BotLoader.mContext, SelectBotActivity.class);
        	    	//myIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        			startActivityForResult(myIntent,1);
        			
        	            }
        	        });
        
       

        
        selBot = (Button) teamSelectView.findViewById(R.id.sel_bot_2);
        selBot.setOnClickListener(new View.OnClickListener() {
        	    public void onClick(View view) {
        	    	Intent myIntent = new Intent(BotLoader.mContext, SelectBotActivity.class);
        	    	//myIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        			startActivityForResult(myIntent,2);
        			
        	            }
        	        });
        
        
        selBot = (Button) teamSelectView.findViewById(R.id.sel_bot_3);
        selBot.setOnClickListener(new View.OnClickListener() {
        	    public void onClick(View view) {
        	    	Intent myIntent = new Intent(BotLoader.mContext, SelectBotActivity.class);
        	    	//myIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        			startActivityForResult(myIntent,3);
        			
        	            }
        	        });
        
        
        
        selBot = (Button) teamSelectView.findViewById(R.id.sel_bot_4);
        selBot.setOnClickListener(new View.OnClickListener() {
        	    public void onClick(View view) {
        	    	Intent myIntent = new Intent(BotLoader.mContext, SelectBotActivity.class);
        	    	//myIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        			startActivityForResult(myIntent,4);
        			
        	            }
        	        });
        
        
        
        selBot = (Button) teamSelectView.findViewById(R.id.sel_bot_5);
        selBot.setOnClickListener(new View.OnClickListener() {
        	    public void onClick(View view) {
        	    	Intent myIntent = new Intent(BotLoader.mContext, SelectBotActivity.class);
        	    	//myIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        			startActivityForResult(myIntent,5);
        			
        	            }
        	        });
        
        
        
        
        
        
        
        
        
        
    //    this.dbV = new DebugView(this);
    
        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dbV = inflater.inflate(R.layout.debug, null);
        
        Button nxtStep = (Button) dbV.findViewById(R.id.nxt_button);
        nxtStep.setOnClickListener(new View.OnClickListener() {
        	    public void onClick(View view) {
        		                BotLoader.stepping= true;
        		                BotLoader.waitForNext=false;
        	            }
        	        });
        
        Button runStep = (Button) dbV.findViewById(R.id.run_button);
        runStep.setOnClickListener(new View.OnClickListener() {
        	    public void onClick(View view) {
        		                BotLoader.stepping= !BotLoader.stepping;
        		                BotLoader.waitForNext=false;
        	            }
        	        });
        
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

        	   }

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
        	  });

        	
        
        
        Button hideView = (Button) dbV.findViewById(R.id.hide_button);
        hideView.setOnClickListener(new View.OnClickListener() {
        	    public void onClick(View view) {
        	    	BotLoader.dbV.setVisibility(View.INVISIBLE);  
                           	    	
        	            }
        	        });
        addContentView(dbV, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
      
        
     //   game = new Soccer(iView);
        
       
				
 botSelect = (Spinner)BotLoader.dbV.findViewById(R.id.debug_bot_sel);
        


    	 
    	 
    	 botSelect.setOnItemSelectedListener(new OnItemSelectedListener() {
	            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
	                // your code here
	            	
	              Soccer.cDebugBot=Integer.parseInt(parentView.getItemAtPosition(position).toString());
	              
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
        
        
   
        
        
		
//		
//		
//       
//        Thread gameThread = new Thread(game);
//        gameThread.start();
//       
//        
        
    }
}