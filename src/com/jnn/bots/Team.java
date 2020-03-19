package com.jnn.bots;

import java.util.ArrayList;

public class Team {

	int points=0;
	public  ArrayList<Bot> players = new ArrayList<Bot>();
	
	public Bot getBot(int botno){
		return players.get(botno);		
	}
	

	public GamePiece getPlayer(int botno){
		return players.get(botno);		
	}
	
	public void addBot(Bot b){
		players.add(b);
	}
	
	
}
