package com.jnn.bots.command;

import java.util.ArrayList;
import java.util.Hashtable;

import com.jnn.asm.bots.Bot;
import com.jnn.asm.util.CMath;

public class PUSHCommand implements COMMANDS {

	//public static String[] paramHelp= new String[1];
	public static String[] paramHelp = { "Pointer", "Value"};

	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean operate(Bot b ,ArrayList<String> params) {
		// TODO Auto-generated method stub

		boolean success = false;
		
		try{
		float stackPTR=CMath.getValueFromParam(b,params.get(0));
			
		b.stack[(int) stackPTR] = CMath.getValueFromParam(b,params.get(1));
		
		success = true;
	}catch (Exception e){
		success = false;
	}	
		
		
		
		return success;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "PUSH";
	}

	public int getNumParams() {
		// TODO Auto-generated method stub
		return 2;
	}

	public String getParamHelp(int paramNum) {
		// TODO Auto-generated method stub
		return paramHelp[paramNum];
	}

	public String getCommandHelp() {
		// TODO Auto-generated method stub
		return "Puts value on the stack at the specified location (the pointer)";
	}

	

}
