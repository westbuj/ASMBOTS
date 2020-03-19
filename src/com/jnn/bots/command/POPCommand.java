package com.jnn.bots.command;

import java.util.ArrayList;
import java.util.Hashtable;

import com.jnn.asm.bots.Bot;
import com.jnn.asm.util.CMath;

public class POPCommand implements COMMANDS {

	//public static String[] paramHelp= new String[1];
	public static String[] paramHelp = { "Pointer", "Target Reg"};

	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean operate(Bot b ,ArrayList<String> params) {
		// TODO Auto-generated method stub

		try{
		if (!b.isReg(params.get(1)))
			return false;
		
		float stackPTR=CMath.getValueFromParam(b,params.get(0));
			
		b.regs.get(params.get(1)).mValue = 	b.stack[(int) stackPTR];
			
		}catch (Exception e){
			return false;
		}
		
		
		return true;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "POP";
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
		return "Puts value from the stack to the specified register";
	}

	

}
