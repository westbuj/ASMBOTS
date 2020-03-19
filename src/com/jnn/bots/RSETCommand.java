package com.jnn.bots;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class RSETCommand implements COMMANDS {
	public static String[] paramHelp = { "Target register", "Value"};
	public float iFloat=0;
	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean operate(Bot b,ArrayList<String> params, Hashtable<String, RegEntry> regs,	 PlayerCode code) {
		// TODO Auto-generated method stub
		
		if (regs.get(params.get(0)) == null)
			return false;
		
		if (params.get(1) == null)
			return false;
		
		if (regs.get(params.get(1)) != null)
			iFloat=regs.get(params.get(1)).mValue;
		else
			iFloat=Float.valueOf(params.get(1));
		
		regs.get(params.get(0)).mValue = iFloat;
		return true;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return "RSET";
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
		return "Sets a register to the value of param2";
	}

}
