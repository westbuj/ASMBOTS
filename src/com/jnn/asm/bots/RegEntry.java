package com.jnn.asm.bots;

public class RegEntry  {
	
	public float mValue=0;
	
	public final static int SHOW_FLOAT=1;
	public final static int SHOW_INT=2;
	
	
	int show_type = SHOW_FLOAT;
	
	public String show(){
		return Float.toString(mValue);
	}
	
	public RegEntry(float iValue){
		mValue=iValue;
	}
	public int intValue()
	{
		return (int)mValue;
	}

}
