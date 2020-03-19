package com.jnn.bots.command;

import java.util.ArrayList;
import java.util.Hashtable;

import com.jnn.asm.bots.Bot;

public class JULTCommand implements COMMANDS {

	public static String[] paramHelp = { "Number 1", "Number 2","Label"};
	
	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean operate(Bot b,ArrayList<String> params) {
		// TODO Auto-generated method stub
		//rCode.code.add("JULT BALD 20 :KICK_AT_GOAL");
			//if (regs.get(params.get(0)) == null)
				//return false;
		
		boolean retval=false;
		float op1;
		float op2;
		
		if (b.isReg(params.get(0)))
			op1=b.getReg(params.get(0));
		else
			op1= Float.valueOf(params.get(0));
		
		if (b.isReg(params.get(1)))
			op2=b.getReg(params.get(1));
		else
			op2= Float.valueOf(params.get(1));
				
		if (params.get(2) == null)
				return false;
			
		
			
			if (op2 > op1)
			{
			//Seach the code for this label
			for (int j=0;j<b.mCode.code.size();j++)
			{
				if (b.mCode.code.get(j).startsWith(params.get(2)))
					{b.regs.get("IP").mValue=j;
					 retval= true;
					 break;
					}
				
			}
			}
			else retval=true;
			
			return retval;
		
		
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
