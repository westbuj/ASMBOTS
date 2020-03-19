package com.jnn.bots.command;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import com.jnn.asm.bots.Bot;

public interface COMMANDS {
	
	public boolean equals(String command);
	public boolean operate(Bot b, ArrayList<String> params);
	public String getName();
	public int getNumParams();
	public String getParamHelp(int paramNum);
	public String getCommandHelp();
	//public String toString(Bot b, ArrayList<String> params, Hashtable<String, RegEntry> regs, Soccer game, PlayerCode code);

}
