package com.jreale4.RPG.client.controllers;

import com.jreale4.RPG.shared.Attack;
import com.jreale4.RPG.shared.Move;
import com.jreale4.RPG.shared.MoveType; // Listed attacks

public class BattleController {

	public int makeSlash(){
		Attack attack =new Attack(MoveType.slash, Move.physical);
		return (int)attack.getAttackPower(attack.getMoveType(), attack.getMove(), 1);
	}
	

}
