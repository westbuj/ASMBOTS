package com.jnn.asm.bots;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class ComboBox extends LinearLayout {

	EditText et=null;
	Spinner setReg=null;
	BaseAdapter ca =null;
	public ComboBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		et=new EditText(context);
		et.setTextSize(10);
		setReg= new Spinner(context);
	//	ca = new CommandAdapter(context);
		
	//	setReg.setAdapter(ca);
		
		this.addView(et);
		
		et.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {}
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
		
		
		
		
		this.addView(setReg);
		
		 setReg.setOnItemSelectedListener(new OnItemSelectedListener() {
	            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
	                // your code here
	            	
	            	if (position>0)
	            		{et.setText((String)ca.getItem(position));
	            		setReg.setSelection(0);
	            		}
	            	 
	            	
	            	
	            }

	            public void onNothingSelected(AdapterView<?> parentView) {
	                // your code here
	            }

				
	        });

		
		
		
	}
	public void setText(String text){
		this.et.setText(text);
	}
	
	public String getText(){
		return this.et.getText().toString();
	}
	public void attachListener(TextWatcher tw){
		
		et.addTextChangedListener(tw);
		
		
	}
	
	
	public void setAdapter(BaseAdapter b){
		
		ca=b;
		setReg.setAdapter(b);
	}

}
