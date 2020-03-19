package com.jnn.bots;

import java.util.Enumeration;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RegisterAdapter extends BaseAdapter {
	
	String[] commands=null;
	private Context mContext = null;
	public RegisterAdapter(Context context) {
		mContext = context;
	
	
	}
	
	public String[] getCommands(){
		String[] s = new String[Soccer.commands.size()+1];
		s[0]="REGS";
			
			Enumeration<String> e = Soccer.commands.keys();
			int j=0;
			while(e.hasMoreElements())
				s[j++] = Soccer.commands.get(e.nextElement()).getName();
			
			return s;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return Soccer.registers.size()+1;
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		if (arg0 == 0)
			return "REGS";
		
		return Soccer.registers.get(arg0 -1);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
	
		CommandView bV=null;
		String cLine=(String)getItem(arg0);
		
		if (convertView != null)
			{bV = (CommandView)convertView;			
			}
		else
			bV=new CommandView(this.mContext,cLine);
		
		
		bV.setText(cLine);
		return bV;
	}

}
