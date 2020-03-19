package com.jnn.bots.command;

import java.util.ArrayList;

import com.jnn.asm.bots.Bot;
import com.jnn.asm.util.CMath;

public class LNPTCommand implements COMMANDS {

	public static String[] paramHelp = { "Distance", "Angle", "x coord","y coord"};
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
		
		if (b.regs.get(params.get(0)) != null)
			distance=b.regs.get(params.get(0)).mValue;
		else
			distance=Float.valueOf(params.get(0));
		
		if (b.regs.get(params.get(1)) != null)
			angle=b.regs.get(params.get(1)).mValue;
		else
			angle=Float.valueOf(params.get(1));
		
		if (b.regs.get(params.get(2)) != null)
			x=b.regs.get(params.get(2)).mValue;
		else
			x=Float.valueOf(params.get(2));
		
		if (b.regs.get(params.get(3)) != null)
			y=b.regs.get(params.get(3)).mValue;
		else
			y=Float.valueOf(params.get(3));
		
		if (b.isReversed)
		{
			x=CMath.revX(x);
			y=CMath.revY(y);
			angle=CMath.revA(angle);
		}
		
		
		y += (float)(distance * Math.cos(angle));
		
		x += (float)(distance * Math.sin(angle));
		
		if (b.isReversed)
		{
			x=CMath.revX(x);
			y=CMath.revY(y);
			angle=CMath.revA(angle);
		}
		
		b.regs.get("RET1").mValue=x;
		b.regs.get("RET2").mValue=y;
		
		
		
		return true;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "LNPT";
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
		return "sets RET1 and RET2 to a point at the specified angle and  distance from a reference point";
	}

}
