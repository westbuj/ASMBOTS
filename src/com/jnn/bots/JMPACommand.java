package com.jnn.bots;

import java.util.ArrayList;
import java.util.Hashtable;

public class JMPACommand implements COMMANDS {
	public static String[] paramHelp = { "LABEL"};
	
	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean operate(Bot b,ArrayList<String> params,
			Hashtable<String, RegEntry> regs,  PlayerCode code) {
		// TODO Auto-generated method stub

			//if (regs.get(params.get(0)) == null)
				//return false;
			
			if (params.get(0) == null)
				return false;
			
			//Seach the code for this label
			for (int j=0;j<code.code.size();j++)
			{
				if (code.code.get(j).startsWith(params.get(0)))
					{regs.get("IP").mValue=j;
					 return true;
					}
				
			}
			
			return false;
		
		
	}
	public String getName() {
		// TODO Auto-generated method stub
		return "JMPA";
	}
	public int getNumParams() {
		// TODO Auto-generated method stub
		return 1;
	}

	public String getParamHelp(int paramNum) {
		// TODO Auto-generated method stub
		return paramHelp[paramNum];
	}

	public String getCommandHelp() {
		// TODO Auto-generated method stub
		return "JMPA :LABEL  -  Set the Instruction Pointer to the line with :LABEL";
	}

}
