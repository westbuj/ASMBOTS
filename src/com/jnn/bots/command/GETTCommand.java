package com.jnn.bots.command;

import java.util.ArrayList;
import java.util.Hashtable;

import com.jnn.asm.bots.Bot;
import com.jnn.asm.soccer.Soccer;
import com.jnn.asm.soccer.Team;
import com.jnn.asm.util.CMath;

public class GETTCommand implements COMMANDS {

	public static String[] paramHelp = { "Teammate ID"};
	
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
		
	
		Team targetTeam=null;
		
			//int team = b.team;
			if (b.team == 1)
				targetTeam=Soccer.team1;
			else
				targetTeam=Soccer.team2;
			
			
			float tVal = targetTeam.players.get((int) op1-1).regs.get("POSX").mValue;
			tVal = targetTeam.players.get((int) op1-1).regs.get("POSY").mValue;
			tVal = targetTeam.players.get((int) op1-1).regs.get("DIR").mValue;
			tVal = targetTeam.players.get((int) op1-1).regs.get("SPD").mValue;
			
			//b.
			b.regs.get("RET1").mValue= targetTeam.players.get((int) op1-1).regs.get("POSX").mValue;
			b.regs.get("RET2").mValue= targetTeam.players.get((int) op1-1).regs.get("POSY").mValue;
			b.regs.get("RET3").mValue= targetTeam.players.get((int) op1-1).regs.get("DIR").mValue;
			b.regs.get("RET4").mValue= targetTeam.players.get((int) op1-1).regs.get("SPD").mValue;
			
			
			
			
			return true;
		
		
	}
	public String getName() {
		// TODO Auto-generated method stub
		return "GETT";
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
		return "GETT 1 - Returns position, heading, and speed of bot on your team to RET1, RET2, RET3, and RET4";
	}

}
