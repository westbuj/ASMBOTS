package com.jnn.bots.command;

import java.util.ArrayList;

import com.jnn.asm.bots.Bot;

public class DSETCommand implements COMMANDS {

	public static String[] paramHelp = { "source reg 1", "target value 1", "source reg 2","target value 2"};
	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean operate(Bot b, ArrayList<String> params) {
		// TODO Auto-generated method stub
		Float value=(float) 0;
		//Float angle=(float) 0;
		Float value2=(float) 0;
	//	Float y=(float) 0;
		
		if (b.regs.get(params.get(0)) == null)
			return false;
		
		if (b.regs.get(params.get(1)) != null)
			value=b.regs.get(params.get(1)).mValue;
		else
			value=Float.valueOf(params.get(1));
		
		
		if (b.regs.get(params.get(2)) == null)
			return false;
		
		if (b.regs.get(params.get(3)) != null)
			value2=b.regs.get(params.get(3)).mValue;
		else
			value2=Float.valueOf(params.get(3));
		
		
		
		b.regs.get(params.get(0)).mValue=value;
		b.regs.get(params.get(2)).mValue=value2;
		
		
		
		return true;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "DSET";
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
		return "sets reg in param1 to the value in param2 and set reg in param3 to the value in param4";
	}

}
