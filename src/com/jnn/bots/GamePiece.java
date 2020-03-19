package com.jnn.bots;

public interface GamePiece{// extends Runnable{
	
	public int getX();
	public int getY();
	public int getSpeed();
	public int getDirection();
	
	public int setX(int iInt);
	public int setY(int iInt);
	public int setSpeed(int iInt);
	public int setDirection(int iInt);
	public PlayerCode getCode();
	public void makeMove();
	

}
