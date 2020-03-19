package com.jnn.bots;

import java.util.ArrayList;
import java.util.Hashtable;

public class INCCommand implements COMMANDS {

	//public static String[] paramHelp= new String[1];
	public static String[] paramHelp = { "Target Register", "Increment amount"};

	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean operate(Bot b ,ArrayList<String> params,
			Hashtable<String, RegEntry> regs,  PlayerCode code) {
		// TODO Auto-generated method stub

			if (regs.get(params.get(0)) == null)
				return false;
			
			if (params.get(1) == null)
				return false;
			
			
			regs.get(params.get(0)).mValue += Integer.valueOf(params.get(1));
			
		
		
		
		return true;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "INC";
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
		return "Increments param1 by param2";
	}

	

}
