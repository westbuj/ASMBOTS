package com.jnn.bots;

import java.util.Enumeration;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

public class ListAdapter extends BaseAdapter {
	
	String[] items=null;
	private Context mContext = null;
	public ListAdapter(Context context) {
		mContext = context;
	
	
	}
	
	public void setArray(String[] s){
		items=s;
		
	}
	public String[] getItems(){
			
			return items;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return items.length;
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return items[arg0];
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
	
		CommandView bV=null;
		String cLine=items[arg0];
		
		if (convertView != null)
			{bV = (CommandView)convertView;			
			}
		else
			bV=new CommandView(this.mContext,cLine);
		
		
		bV.setText(cLine);
		return bV;
	}

}
