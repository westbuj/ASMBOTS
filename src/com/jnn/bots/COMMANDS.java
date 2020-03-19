package com.jnn.bots;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public interface COMMANDS {
	
	public boolean equals(String command);
	public boolean operate(Bot b, ArrayList<String> params, Hashtable<String, RegEntry> regs, PlayerCode code);
	public String getName();
	public int getNumParams();
	public String getParamHelp(int paramNum);
	public String getCommandHelp();
	//public String toString(Bot b, ArrayList<String> params, Hashtable<String, RegEntry> regs, Soccer game, PlayerCode code);

}
