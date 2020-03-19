package com.jnn.bots.command;

import java.util.ArrayList;
import java.util.Hashtable;

import com.jnn.asm.bots.Bot;

public class JUXTCommand implements COMMANDS {

	public static String[] paramHelp = { "number", "lower","upper",":label"};
	static float op1;
	static float op2;
	static float op3;
	
	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean operate(Bot b,ArrayList<String> params) {
		// TODO Auto-generated method stub
		//rCode.code.add("JULT BALD 20 :KICK_AT_GOAL");
			//if (regs.get(params.get(0)) == null)
				//return false;
		
		
		
		if (b.isReg(params.get(0)))
			op1=b.getReg(params.get(0));
		else
			op1= Float.valueOf(params.get(0));
		
		if (b.isReg(params.get(1)))
			op2=b.getReg(params.get(1));
		else
			op2= Float.valueOf(params.get(1));
		
		if (b.isReg(params.get(2)))
			op3=b.getReg(params.get(2));
		else
			op3= Float.valueOf(params.get(2));
		
		
		if (params.get(3) == null)
				return false;
			
		 
			
			if (!((op1 > op2) && (op1 < op3)))
			{
			//Seach the code for this label
			for (int j=0;j<b.mCode.code.size();j++)
			{
				if (b.mCode.code.get(j).startsWith(params.get(3)))
					{b.regs.get("IP").mValue=j;
					 return true;
					}
				
			}
			}
			
			return true;
		
		
	}
	public String getName() {
		// TODO Auto-generated method stub
		return "JUXT";
	}
	public int getNumParams() {
		// TODO Auto-generated method stub
		return 4;
	}

	public String getParamHelp(int paramNum) {
		// TODO Auto-generated method stub
		return paramHelp[paramNum];
	}

	public String getCommandHelp() {
		// TODO Auto-generated method stub
		return "Jumps to the specified label if number is outside lower and upper";
	}

}
