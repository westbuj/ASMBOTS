package com.jnn.bots.command;

import java.util.ArrayList;

import com.jnn.asm.bots.Bot;
import com.jnn.asm.util.CMath;

public class ANGLCommand implements COMMANDS {

	public static String[] paramHelp = { "x1", "y1", "x2","y2"};
	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean operate(Bot b, ArrayList<String> params) {
		// TODO Auto-generated method stub
		Float x1=(float) 0;
		Float y1=(float) 0;
		Float x2=(float) 0;
		Float y2=(float) 0;
		Float angle=(float) 0;
		
		if (b.regs.get(params.get(0)) != null)
			x1=b.regs.get(params.get(0)).mValue;
		else
			x1=Float.valueOf(params.get(0));
		
		if (b.regs.get(params.get(1)) != null)
			y1=b.regs.get(params.get(1)).mValue;
		else
			y1=Float.valueOf(params.get(1));
		
		if (b.regs.get(params.get(2)) != null)
			x2=b.regs.get(params.get(2)).mValue;
		else
			x2=Float.valueOf(params.get(2));
		
		if (b.regs.get(params.get(3)) != null)
			y2=b.regs.get(params.get(3)).mValue;
		else
			y2=Float.valueOf(params.get(3));
		
		if (b.isReversed)
		{
			x1=CMath.revX(x1);
			y1=CMath.revY(y1);
			x2=CMath.revX(x2);
			y2=CMath.revY(y2);
			
		}
		
		angle = CMath.calcAngle(x1,y1,x2,y2);
		
		
		
		if (b.isReversed)
		{			
			angle=CMath.revA(angle);
		}
		
		b.regs.get("RET1").mValue=angle;
		
		
		
		return true;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "ANGL";
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
		return "sets RET1 to the angle between two points";
	}

}
