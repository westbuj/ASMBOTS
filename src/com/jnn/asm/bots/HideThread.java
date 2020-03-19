package com.jnn.asm.bots;


import android.view.View;


public class HideThread extends Thread implements Runnable {

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (BotLoader.mode==BotLoader.PLAYING)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {			}
			
			MyView.hideMFCP -= 1;
			if (MyView.hideMFCP <0 && BotLoader.dbV.getVisibility() == View.VISIBLE)
			{
				BotLoader.iView.hideMFCPHandler.sendEmptyMessage(1);
				
				//hide
			}
			
		}
		
	}
	
	

}
