package com.jnn.bots;

import android.graphics.Canvas;

public interface GameField extends Runnable{
	
	
	public int addPlayer(Bot g, int team);
	public void draw(Canvas c);

	public static float width=500;
	public static float height=1000;
}
