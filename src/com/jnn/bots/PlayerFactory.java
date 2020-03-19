package com.jnn.bots;

public class PlayerFactory {

	public static PlayerCode makeDefender()
	{
		PlayerCode rCode = new PlayerCode();
		rCode.code.add(":INIT");
		rCode.code.add("RSET SPD 8");
		rCode.code.add("RSET TRGY 800");
		rCode.code.add("RSET TRGX BALX");
		rCode.code.add("RSET AUX1 GOLR");
		rCode.code.add("INC AUX1 1");
		rCode.code.add(":START");
		rCode.code.add("RSET TRGX BALX");
		rCode.code.add("JULT BALD 20 :KICK_AT_GOAL");
		rCode.code.add("JMPA :START");
		rCode.code.add(":KICK_AT_GOAL");
		rCode.code.add("KICK GOLR 0");
		rCode.code.add("KICK AUX1 10");
		rCode.code.add("JMPA :START");
		
		
		return rCode;
	}
	
	public static PlayerCode makeBallChaser()
	{
		PlayerCode rCode = new PlayerCode();
		rCode.code.add(":START");
		rCode.code.add("RSET TRGX BALX");
		rCode.code.add("RSET TRGY BALY");
		rCode.code.add("KICK GOLR 10");
		rCode.code.add("JMPA :START");
		return rCode;
	}
	public static PlayerCode makeGoalSitter()
	{
		PlayerCode rCode = new PlayerCode();
		rCode.code.add(":START");
		rCode.code.add("RSET SPD 9");
		rCode.code.add("RSET AUX1 GOLY");
		rCode.code.add("INC AUX1 200");
		rCode.code.add("RSET TRGY AUX1");
		rCode.code.add("JULT BALD 20 :KICK_AT_GOAL");
		rCode.code.add("JULT BALD 250 :CHASE_BALL");
		rCode.code.add("RSET TRGX BALX");	
		rCode.code.add("JMPA :START");
		rCode.code.add(":KICK_AT_GOAL");
		rCode.code.add("KICK GOLR 0");
		rCode.code.add("KICK GOLR 10");
		rCode.code.add("JMPA :START");
		rCode.code.add(":CHASE_BALL");
		rCode.code.add("RSET SPD 10");		
		rCode.code.add("RSET TRGX BALX");
		rCode.code.add("RSET TRGY BALY");
		rCode.code.add("JULT BALD 20 :KICK_AT_GOAL");
		rCode.code.add("JULT BALD 100 :CHASE_BALL");
		rCode.code.add("JMPA :START");
		
		
		return rCode;
	}
	
	
}
