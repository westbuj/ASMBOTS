package com.jnn.asm.soccer;

import java.util.ArrayList;

import com.jnn.asm.bots.Bot;
import com.jnn.asm.bots.GamePiece;

public class Team {

	public float goaly=0;
	public int points=0;
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
	public void empty(){
		players.clear();
	}
	
	
}
