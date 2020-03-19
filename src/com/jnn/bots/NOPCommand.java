package com.jnn.bots;

import java.util.ArrayList;
import java.util.Hashtable;

public class NOPCommand implements COMMANDS {
	public static String[] paramHelp = { "The target register", "The increment amount. Can be negative"};
	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean operate(Bot b, ArrayList<String> params,
			Hashtable<String, RegEntry> regs,  PlayerCode code) {
		// TODO Auto-generated method stub
		return false;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return "NOP";
	}
	public int getNumParams() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getParamHelp(int paramNum) {
		// TODO Auto-generated method stub
		return paramHelp[paramNum];
	}

	public String getCommandHelp() {
		// TODO Auto-generated method stub
		return "NOP - No operation. This command does nothing.";
	}

}
