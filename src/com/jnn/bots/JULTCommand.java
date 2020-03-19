package com.jnn.bots;

import java.util.ArrayList;
import java.util.Hashtable;

public class JULTCommand implements COMMANDS {

	public static String[] paramHelp = { "Number 1", "Number 2","Label"};
	
	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean operate(Bot b,ArrayList<String> params,
			Hashtable<String, RegEntry> regs,  PlayerCode code) {
		// TODO Auto-generated method stub
		//rCode.code.add("JULT BALD 20 :KICK_AT_GOAL");
			//if (regs.get(params.get(0)) == null)
				//return false;
		
		float op1;
		if (regs.get(params.get(0)) == null)
			return false;
			
			if (params.get(1) == null )
				return false;
			
			if (params.get(2) == null)
				return false;
			
			if (regs.get(params.get(1)) == null)
				op1= Float.valueOf(params.get(1));
			else
				op1= regs.get(params.get(1)).mValue;
			
			if (params.get(2) == null)
				return false;
			
			if (regs.get(params.get(0)).mValue < op1)
			{
			//Seach the code for this label
			for (int j=0;j<code.code.size();j++)
			{
				if (code.code.get(j).startsWith(params.get(2)))
					{regs.get("IP").mValue=j;
					 return true;
					}
				
			}
			}
			
			return false;
		
		
	}
	public String getName() {
		// TODO Auto-generated method stub
		return "JULT";
	}
	public int getNumParams() {
		// TODO Auto-generated method stub
		return 3;
	}

	public String getParamHelp(int paramNum) {
		// TODO Auto-generated method stub
		return paramHelp[paramNum];
	}

	public String getCommandHelp() {
		// TODO Auto-generated method stub
		return "JULT AUX1 AUX2 :LABEL - Jumps to the specified label if param1 < param2";
	}

}
