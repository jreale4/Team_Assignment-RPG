package com.jreale4.RPG.server;

public class Attack {
	private MoveType movetype;
	private Move move;
	
	public Attack(MoveType movetype, Move move){
		this.movetype=movetype;
		this.move=move;
	}
	
	public MoveType getMoveType(){
		return movetype;
	}
	public Move getMove(){
		return move;
	}
	
	public int getAttackPower(MoveType movetype, Move move, int Level){
		int Power=getMoveTypePower(movetype);
		Power+=getMovePower(move);
		Power= Power*Level/4;
		return Power;
	}
	private int getMoveTypePower(MoveType movetype){
		int Power=0;
		if(getMoveType()==MoveType.physical){
			Power=1;
		}
		if(getMoveType()==MoveType.fire){
			Power=2;
		}
		if(getMoveType()==MoveType.water){
			Power=3;
		}
		if(getMoveType()==MoveType.earth){
			Power=4;
		}
		if(getMoveType()==MoveType.air){
			Power=5;
		}
		return Power;
	}
	private int getMovePower(Move move){
		int Power=0;
		if(getMove()==Move.stab){
			Power=1;
		}
		if(getMove()==Move.slash){
			Power=2;
		}
		if(getMove()==Move.magic){
			Power=3;
		}
		return Power;
	}
}
