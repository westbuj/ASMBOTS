package com.jnn.bots;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class BotFileAdapter extends BaseAdapter {
	
	public ArrayList<String> botList=new ArrayList<String>();
	public ArrayList<String> selBots = new ArrayList<String>();
	
	/** Remember our context so we can use it when constructing views. */
	private Context mContext;

	
	public  void reloadbotList()
    {
    	  File appRoot = new File(ANR.appStoragePath);
    	  
    	 
          File[] files= appRoot.listFiles();  
      	
        	botList.clear();
        	        	
            for (int j = 0; j < files.length ;j++) 
            {
            	
            	
            	if (files[j].isFile() && files[j].getName().endsWith(".bot"))
            	{
            	
            		botList.add(files[j].getName());
            	}
            		
  
            }

           
            
            
        	notifyDataSetChanged();
    }

	
	
	
	
	
	
	
	
	public BotFileAdapter(Context context) {
		mContext = context;
		
		 reloadbotList();
		
	}

	public void addItem(String it) {
		botList.add(0,it);
	}

	public void setListItems(ArrayList<String> lit) {
		botList = lit;
	}

	/** @return The number of items in the */
	public int getCount() {
		return botList.size();
	}

	public boolean haveItem(String p)
	{
		return (botList.contains(p));
	}
	public Object getItem(int position) {

		return botList.get(position);
	}
	
	public void empty(){
		
		this.botList.clear();
		
	}

	public boolean areAllItemsSelectable() {
		return true;
	}

	public boolean isSelectable(int position) {
		return true;
	}

	/** Use the array index as a unique id. */
	public long getItemId(int position) {
		return position;
	}

	/**
	 * @param convertView
	 *            The old view to overwrite, if one is passed
	 * @returns a IconifiedTextView that holds wraps around an IconifiedText
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		
		String mItem=botList.get(position);
	//	if (thisModelTest.getView() != null)
		//	return thisModelTest.getView();
		
		
		TextView l;
		if (convertView != null)
			l=(TextView)convertView;
		else
			{
			
			l = new TextView(this.mContext);		
	
			
		
			
			}
		
		
		
		l.setText(mItem);
	
		return l;
	}
}