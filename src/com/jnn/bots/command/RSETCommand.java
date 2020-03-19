package com.jnn.bots.command;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import com.jnn.asm.bots.Bot;

public class RSETCommand implements COMMANDS {
	public static String[] paramHelp = { "Target register", "Value"};
	public float iFloat=0;
	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean operate(Bot b,ArrayList<String> params) {
		// TODO Auto-generated method stub
		

		
		if (b.regs.get(params.get(0)) == null)
			return false;
		
		if (params.get(1) == null)
			return false;
		
		if (b.regs.get(params.get(1)) != null)
			iFloat=b.regs.get(params.get(1)).mValue;
		else
			iFloat=Float.valueOf(params.get(1));
		
		b.regs.get(params.get(0)).mValue = iFloat;
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
