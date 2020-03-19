package com.jnn.bots;

/**
 * 
 */




import java.io.File;import com.jnn.asm.bots.R;




import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Dad
 * 
 */
public class CodeViewActivity extends ListActivity {
	
	 @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		 BotLoader.codeViewMode=true;
		super.onResume();
	}
	public static Handler setScroll = new Handler() {

 		public void handleMessage(Message msg) {
 			
 			CodeViewActivity.bAdapter.notifyDataSetChanged();
 			
 			CodeViewActivity.thisAct.setSelection(
 			(int)bAdapter.mBot.regs.get("IP").mValue
 			);
 			
 		}
	 };
	
	public static CodeViewActivity thisAct = null;
	public static PlayerCodeAdapter bAdapter = null;
	@Override public void onConfigurationChanged(Configuration newConfig) { super.onConfigurationChanged(newConfig); }
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		 
	    ANR.setupStorage("asmbots");
		 
		BotLoader.codeViewMode=true; 
		thisAct=this;
		

		if (bAdapter == null)
			CodeViewActivity.bAdapter = new PlayerCodeAdapter(this,BotLoader.game.players.get(Soccer.cDebugBot));
		
		bAdapter.mBot=BotLoader.game.players.get(Soccer.cDebugBot);
		
		// Display it
		setListAdapter(CodeViewActivity.bAdapter);

		//setContentView(l);
		View dbV=null;
		 LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        dbV = inflater.inflate(R.layout.code_view_control, null);
	        
	        Button nxtStep = (Button) dbV.findViewById(R.id.nxt_button);
	        nxtStep.setOnClickListener(new View.OnClickListener() {
	        	    public void onClick(View view) {
	        		                BotLoader.stepping= true;
	        		                BotLoader.waitForNext=false;
	        		               // CodeViewActivity.setScroll.sendEmptyMessage(0);
	        	            }
	        	        });
	        
	        Button runStep = (Button) dbV.findViewById(R.id.run_button);
	        runStep.setOnClickListener(new View.OnClickListener() {
	        	    public void onClick(View view) {
	        		                BotLoader.stepping= !BotLoader.stepping;
	        		                BotLoader.waitForNext=false;
	        	            }
	        	        });
	        
	        
	        
	        addContentView(dbV, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
	      
	        registerForContextMenu(getListView());

	        

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		BotLoader.codeViewMode=false;
		super.onPause();
	}
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		
		//CachedViewed thisModelTest=(CachedViewed)MainActivity.modelBrowse.get(position);	
		
//		Brush b= (Brush) CodeViewActivity.bAdapter.getItem(position);
//		
//		MUStudio.cBrush=b;
//				
//		this.finish();	
		
		
		v.performLongClick();
		
		

	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        boolean result = super.onCreateOptionsMenu(menu);
	      
	        menu.add(0, 1, 0, "Save").setShortcut('3', 'c');
	        menu.add(0, 2, 0, "Save All").setShortcut('4', 's');
	       
	        return result;//super.onCreateOptionsMenu(menu);
	    }
	 
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	       

	        switch (item.getItemId()) {
	            case 1:
	            	String fName = Soccer.players.get(Soccer.cDebugBot).mCode.fileName;
	            	Soccer.players.get(Soccer.cDebugBot).mCode.save(fName);
	            	return true;
	            case 2: 
	            	for (int j=0; j< Soccer.team1.players.size();j++)
	            	{
	            		Soccer.team1.players.get(j).mCode.save( Soccer.players.get(j).mCode.fileName);
	            	}
	                return true;
	                  }
	        return super.onOptionsItemSelected(item);
	    }
	 @Override  
     public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
     super.onCreateContextMenu(menu, v, menuInfo);  
         menu.setHeaderTitle("Code Options");  
         menu.add(0, 1, 0, "Edit Command");  
         menu.add(0, 2, 0, "Insert above");
         menu.add(0, 3, 0, "Delete Command");
       
         
     }  
 @Override 
   public boolean onContextItemSelected(MenuItem item) {  
	
	 
	 AdapterView.AdapterContextMenuInfo cmi =
	        (AdapterView.AdapterContextMenuInfo) item.getMenuInfo ();

	

	 if(item.getItemId() == 1)
	 {
	 Intent myIntent = new Intent(this, EditCodeActivity.class);
	 Soccer.players.get(Soccer.cDebugBot).mCode.editPoint=cmi.position;
	 Soccer.players.get(Soccer.cDebugBot).mCode.editMode=EditCodeActivity.MODE_EDIT;
	 myIntent.putExtra("BOTCODE",new Integer(Soccer.cDebugBot));
	 
	this.startActivity(myIntent);
	 
	 }
	 
	 if(item.getItemId() == 2)
	 {
	 Intent myIntent = new Intent(this, EditCodeActivity.class);
	 Soccer.players.get(Soccer.cDebugBot).mCode.editPoint=cmi.position;
	 Soccer.players.get(Soccer.cDebugBot).mCode.editMode=EditCodeActivity.MODE_INSERT;
	 myIntent.putExtra("BOTCODE",new Integer(Soccer.cDebugBot));
	 
	this.startActivity(myIntent);
	 
	 }
	 
	 if(item.getItemId() == 3)
	 {
	 
	 //Soccer.players.get(Soccer.cDebugBot).mCode.editPoint=cmi.position;
	 Soccer.players.get(Soccer.cDebugBot).mCode.code.remove(cmi.position);
	 //Soccer.players.get(Soccer.cDebugBot).mCode.editMode=EditCodeActivity.MODE_INSERT;
	 CodeViewActivity.setScroll.sendEmptyMessage(1);
	 
	
	 
	 }
	 
	
	 
      
     return true;  
     }  
	
}
