package com.jnn.bots.command;

import java.util.ArrayList;
import java.util.Hashtable;

import com.jnn.asm.bots.Bot;
import com.jnn.asm.soccer.Soccer;

public class YELDCommand implements COMMANDS {

	public static String[] paramHelp = { "YieldToBot"};
	
	public boolean equals(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean operate(Bot b,ArrayList<String> params) {
		// TODO Auto-generated method stub
		//rCode.code.add("JULT BALD 20 :KICK_AT_GOAL");
			//if (regs.get(params.get(0)) == null)
				//return false;
		
		float op1;
		
		if (b.isReg(params.get(0)))
			op1=b.getReg(params.get(0));
		else
			op1= Float.valueOf(params.get(0));
		
	
		
		
			int team = b.team;
			if (b.team == 1)
				Soccer.team1.players.get((int) op1-1).makeMove();
			else
				Soccer.team2.players.get((int) op1-1).makeMove();
		
			
			return true;
		
		
	}
	public String getName() {
		// TODO Auto-generated method stub
		return "YELD";
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
		return "YELD 1 - Gives processing power to the bot in param1";
	}

}
