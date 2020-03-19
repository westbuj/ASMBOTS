package com.jnn.bots;

import java.util.ArrayList;
import java.util.Enumeration;

import com.jnn.asm.bots.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class EditCodeActivity extends Activity implements OnClickListener {

	Bot cBot =null;
	int mode =0;
	ArrayAdapter<String> ca = null;
	ComboBox op1=null;
	ComboBox op2=null;
	ComboBox op3=null;
	ComboBox op4=null;
	ComboBox op5=null;
	ComboBox command =null;
	TextView cmdHelp=null;
	TextView op1Prompt=null;
	TextView op2Prompt=null;
	TextView op3Prompt=null;
	TextView op4Prompt=null;
	TextView op5Prompt=null;
	
	
	public static final int MODE_READ = 1;
	public static final int MODE_EDIT = 2;
	public static final int MODE_INSERT = 3;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Integer tBot = (Integer) getIntent().getExtras().get("BOTCODE");
		cBot = Soccer.players.get(tBot.intValue());
		
		
		mode=cBot.mCode.editMode;
	        
		    //    this.dbV = new DebugView(this);
		    
		
		
	
			String[] s = new String[Soccer.commands.size()];
			
				
				Enumeration<String> e = Soccer.commands.keys();
				int j=0;
				while(e.hasMoreElements())
					s[j++] = Soccer.commands.get(e.nextElement()).getName();
				
		
		
		
		
		        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		      //  dbV = inflater.inflate(R.layout.com_edit, null);
		        
		        
		        
		        
		        
		        View v= inflater.inflate(R.layout.com_edit, null);
		        command = (ComboBox)v.findViewById(R.id.com_combo);
		        command.setAdapter(new CommandAdapter(this));
		  //   ca = new CommandAdapter(this);
		//     ca = new ArrayAdapter<String>(this, R.id.command_sel,s);
		//        command.setAdapter(ca);
		        
		        
		        
		        
		        cmdHelp =  (TextView) v.findViewById(R.id.cmd_help);
		        
		        
		        op1 = (ComboBox)v.findViewById(R.id.op1_combo);
		        RegisterAdapter ta = new RegisterAdapter(this);
		        op1.setAdapter( new RegisterAdapter(this));
		        
		        
		        op1Prompt = (TextView) v.findViewById(R.id.op1_help);
		        
		        
		        op2 = (ComboBox)v.findViewById(R.id.op2_combo);
		        op2.setAdapter( ta);
		        
		        op2Prompt = (TextView) v.findViewById(R.id.op2_help);
		        
		        op3 = (ComboBox)v.findViewById(R.id.op3_combo);
		        op3.setAdapter( ta);
		        op3Prompt = (TextView) v.findViewById(R.id.op3_help);
		        
		        op4 = (ComboBox)v.findViewById(R.id.op4_combo);
		        op4.setAdapter( ta);
		        op4Prompt = (TextView) v.findViewById(R.id.op4_help);
		        
		        op5 = (ComboBox)v.findViewById(R.id.op5_combo);
		        op5.setAdapter( ta);
		        op5Prompt = (TextView) v.findViewById(R.id.op5_help);
		        
		       
		        //if edit mode, set the valuse
		        if (mode == MODE_EDIT)
		        {
		        	String code= cBot.mCode.code.get(cBot.mCode.editPoint);
		        	ArrayList<String> params = PlayerCode.tokenizeString(code," ");
		        	
		        	String cCommand= params.get(0);
		        	
		        	command.setText(cCommand);
		        	params.remove(0);
		        	
		        	if (params.size() >= 1)
	            		op1.setText(params.get(0));
	            	else
	            		op1.setText("");
		        	
		        	
		        	if (params.size() >= 2)
	            		op2.setText(params.get(1));
	            	else
	            		op2.setText("");
		        	
		        	
		        	
		        	if (params.size() >= 3)
	            		op3.setText(params.get(2));
	            	else
	            		op3.setText("");
		        	
		        	
		        	
		        	if (params.size() >= 4)
	            		op4.setText(params.get(3));
	            	else
	            		op4.setText("");
		        	
		        	
		        	
		        	
		        	if (params.size() >= 5)
	            		op5.setText(params.get(4));
	            	else
	            		op5.setText("");
		      	
		        	
		        }
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        //get the save button
		        
		        Button saveButton = (Button) v.findViewById(R.id.sav_button);
		        saveButton.setTag("SAVE");
		        saveButton.setOnClickListener(this);
		        
		
		        
		        
		        
		        
		        
		        command.attachListener(new TextWatcher(){
			        public void afterTextChanged(Editable s) {

			        	COMMANDS c = Soccer.commands.get(s.toString());
			        	if (c != null)
			        	{
			        		cmdHelp.setText(c.getCommandHelp());
			        		if (c.getNumParams() >= 1)
			            		op1Prompt.setText(c.getParamHelp(0));
			            	else
			            		op1Prompt.setText("N/A");
			        		
			        		if (c.getNumParams() >= 2)
			            		op2Prompt.setText(c.getParamHelp(1));
			            	else
			            		op2Prompt.setText("N/A");
			        		
			        		if (c.getNumParams() >= 3)
			            		op3Prompt.setText(c.getParamHelp(2));
			            	else
			            		op3Prompt.setText("N/A");
			        		
			        		if (c.getNumParams() >= 4)
			            		op4Prompt.setText(c.getParamHelp(3));
			            	else
			            		op4Prompt.setText("N/A");
			        		
			        		if (c.getNumParams() >= 5)
			            		op5Prompt.setText(c.getParamHelp(4));
			            	else
			            		op5Prompt.setText("N/A");
			        		
			        		
			        	}
			        	
			        	
			        }
			        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
			        public void onTextChanged(CharSequence s, int start, int before, int count){
			        	
			        	
			        	
			        }
			    }); 
		        
		        
		        
		        
		        
		        setContentView(v);
	}




	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		if (arg0.getTag().equals("SAVE"))
		{
			int params = 0; 
			COMMANDS c = Soccer.commands.get(command.getText());
        	if (c != null)
        	{
        		params = c.getNumParams();
        		
            	
        	}
			
		//	COMMANDS c = (COMMANDS)command.getText();
			String op ="";
			if (params > 0)
				op += (String)op1.getText();//.getSelectedItem();	
			
			if (params > 1)
				op += " " +(String)op2.getText();//(String)op2.getSelectedItem();	
			
			if (params > 2)
				op += " " +(String)op3.getText();//(String)op3.getSelectedItem();	
			
			if (params > 3)
				op += " " +(String)op4.getText();//(String)op4.getSelectedItem();
			
			if (params > 4)
				op += " " +(String)op5.getText();//(String)op5.getSelectedItem();
			
			
			if (this.mode == EditCodeActivity.MODE_INSERT)
			{
			String codeLine = command.getText()+" "+op;
			this.cBot.mCode.code.add(cBot.mCode.editPoint+1, codeLine);
			CodeViewActivity.setScroll.sendEmptyMessage(1);
			}
			
			if (this.mode == EditCodeActivity.MODE_EDIT)
			{

				String codeLine = command.getText()+" "+op;
				this.cBot.mCode.code.remove(cBot.mCode.editPoint);
				this.cBot.mCode.code.add(cBot.mCode.editPoint, codeLine);
				CodeViewActivity.setScroll.sendEmptyMessage(1);
			}
			this.finish();
		}
		
		
		
	}
	
	
	

}
