package com.jnn.asm.bots;

import java.util.ArrayList;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PlayerCodeAdapter extends BaseAdapter {

	public Bot mBot = null;
	private Context mContext = null;
	public PlayerCodeAdapter(Context context,Bot pc) {
		mContext = context;
		mBot=pc;
		
	
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return mBot.mCode.code.size();
	}
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mBot.mCode.code.get(arg0);
	}
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	public View getView(int position, View convertView, ViewGroup parent)  {
		// TODO Auto-generated method stub
		CodeView bV=null;
		String cLine=mBot.mCode.code.get(position);
		
		if (convertView != null)
			{bV = (CodeView)convertView;			
			}
		else
			bV=new CodeView(this.mContext,cLine);
		
		if (!mBot.codeStat[position])   //was the last run an eroor
			bV.setBackgroundColor(Color.RED);
		else
			bV.setBackgroundColor(Color.TRANSPARENT);
		
		if (cLine.startsWith(":"))
			bV.setBackgroundColor(Color.TRANSPARENT);
		
		bV.setText(cLine);
		if (position == mBot.regs.get("IP").mValue)
			bV.hilight=true;//.tPaint.setColor(Color.LTGRAY);
		else
			bV.hilight=false;//bV.tPaint.setColor(Color.YELLOW);
		
		 bV.invalidate();
		return bV;
	}
}
