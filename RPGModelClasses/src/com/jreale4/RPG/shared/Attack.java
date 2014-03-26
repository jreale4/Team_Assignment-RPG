package com.jreale4.RPG.shared;

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
	
	public float getAttackPower(MoveType movetype, Move move, int Level){
		float Power=getMoveTypePower(movetype);
		Power+=getMovePower(move);
		Power= Power*Level/2;
		return Power;
	}
	private int getMoveTypePower(MoveType movetype){
		int Power=0;
		if(getMoveType()==MoveType.slash || getMoveType()==MoveType.stab || getMoveType()==MoveType.flamenPunch || getMoveType()==MoveType.flamenKick || getMoveType()==MoveType.gigaFlamenBreak){
			Power=1;
		}
		if(getMoveType()==MoveType.fira){
			Power=2;
		}
		if(getMoveType()==MoveType.aqua){
			Power=3;
		}
		if(getMoveType()==MoveType.terra){
			Power=4;
		}
		if(getMoveType()==MoveType.venta){
			Power=5;
		}
		if(getMoveType()==MoveType.thundara){
			Power=6;
		}
		return Power;
	}
	private int getMovePower(Move move){
		int Power=0;
		if(getMove()==Move.physical){
			Power=1;
		}
		if(getMove()==Move.magic){
			Power=2;
		}
		return Power;
	}
}
