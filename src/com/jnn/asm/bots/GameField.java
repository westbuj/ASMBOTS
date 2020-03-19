package com.jnn.asm.bots;


import android.graphics.Canvas;
import android.view.View;

public interface GameField extends Runnable{
	
	
	public int addPlayer(Bot g, int team);
	public void draw(Canvas c, View v);

	public static float width=500;
	public static float height=1000;
}
