package com.jnn.bots.command;

import java.util.ArrayList;
import java.util.Hashtable;

import com.jnn.asm.bots.Bot;
import com.jnn.asm.util.CMath;

public class DIVCommand implements COMMANDS {

	//public static String[] paramHelp= new String[1];
	public static String[] paramHelp = { "Numerator", "Denominator"};

	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean operate(Bot b ,ArrayList<String> params) {
		// TODO Auto-generated method stub

		if (b.regs.get(params.get(0)) == null)
			return false;
		
		float op1=CMath.getValueFromParam(b,params.get(0));
		float op2=CMath.getValueFromParam(b,params.get(1));
			
			
		b.regs.get(params.get(0)).mValue = op1/op2;
			
		
		
		
		return true;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "DIV";
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
		return "Divides param1 by param2";
	}

	

}
