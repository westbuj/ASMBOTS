package com.jnn.bots.command;

import java.util.ArrayList;

import com.jnn.asm.bots.Bot;
import com.jnn.asm.util.CMath;

public class DISTCommand implements COMMANDS {

	public static String[] paramHelp = { "X1", "Y1", "X2","Y2"};
	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean operate(Bot b, ArrayList<String> params) {
		// TODO Auto-generated method stub
		Float distance=(float) 0;
		Float angle=(float) 0;
		Float x=(float) 0;
		Float y=(float) 0;
		
		float x1 = CMath.getValueFromParam(b,params.get(0));
		float y1 = CMath.getValueFromParam(b,params.get(1));
		float x2 = CMath.getValueFromParam(b,params.get(2));
		float y2 = CMath.getValueFromParam(b,params.get(3));
		
		b.regs.get("RET1").mValue = (float) Math.sqrt( (x2-x1)*(x2-x1) + (y2-y1) * (y2-y1));
		
		
		
		return true;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "DIST";
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
		return "Calculates the distance between two points";
	}

}
