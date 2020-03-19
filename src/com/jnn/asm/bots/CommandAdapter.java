package com.jnn.asm.bots;

import java.util.Enumeration;

import com.jnn.asm.soccer.Soccer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

public class CommandAdapter extends BaseAdapter {
	
	String[] commands=null;
	private Context mContext = null;
	public CommandAdapter(Context context) {
		mContext = context;
	
	
	}
	public int indexOf(String command)
	{   int ret=-1;
		for (int j=0; j< commands.length;j++)
		{
			if (commands[j].equals(command))
				return j;
		}
		
		return ret;
	}
	
	public String[] getCommands(){
		String[] s = new String[Soccer.commands.size() + 1];
		s[0]="COMS";
		
			
			Enumeration<String> e = Soccer.commands.keys();
			int j=1;
			while(e.hasMoreElements())
				s[j++] = Soccer.commands.get(e.nextElement()).getName();
			
			return s;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		if (commands == null)
			commands = getCommands();
		
		return commands.length;
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return Soccer.commands.get(commands[arg0]).getName();
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
	
		CommandView bV=null;
		String cLine=commands[arg0];
		
		if (convertView != null)
			{bV = (CommandView)convertView;			
			}
		else
			bV=new CommandView(this.mContext,cLine);
		
		
		bV.setText(cLine);
		return bV;
	}

}
