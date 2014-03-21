package com.jreale4.RPG.shared;

import java.util.ArrayList;

import junit.framework.TestCase;

public class CharacterTest extends TestCase{
	private Hero hero1;
	
	private int lvl;
	
	private int exp;
	
	private Attack attack1;
	private Attack attack2;
	private Attack attack3;
	private Attack attack4;
	ArrayList<Attack> a = new ArrayList<Attack>();
	
	private Item potion;
	private Item attUp;
	private Item acid;
	ArrayList<Item> i = new ArrayList<Item>();
	
	private Equipment helmet;
	private Equipment torso;
	private Equipment bracers;
	private Equipment pants;
	private Equipment boots;
	private Equipment gloves;
	private Equipment pack;
	ArrayList<Equipment> e = new ArrayList<Equipment>();
	
	protected void setUp(){
		
		lvl=1;
		
		exp=900;
		
		attack1= new Attack(MoveType.slash, MoveStyle.physical);
		attack2= new Attack(MoveType.water, MoveStyle.magic);
		attack3= new Attack(MoveType.stab, MoveStyle.physical);
		attack4= new Attack(MoveType.earth, MoveStyle.magic);
		a.add(attack1);
		a.add(attack2);
		a.add(attack3);
		a.add(attack4);
		
		potion= new Item(ItemType.health);
		attUp= new Item(ItemType.boost);
		acid= new Item(ItemType.poison);
		i.add(potion);
		i.add(attUp);
		i.add(acid);
		
		helmet= new Equipment(EquipmentType.head, 1);
		torso= new Equipment(EquipmentType.chest, 2);
		bracers= new Equipment(EquipmentType.arms, 1);
		pants= new Equipment(EquipmentType.legs, 2);
		boots= new Equipment(EquipmentType.feet, 3);
		gloves= new Equipment(EquipmentType.hands, 2);
		pack= new Equipment(EquipmentType.back, 3);
		e.add(helmet);
		e.add(torso);
		e.add(bracers);
		e.add(pants);
		e.add(boots);
		e.add(gloves);
		e.add(pack);
		
		hero1= new Hero(lvl, exp+10, a, i, e);
	}
	
	public void testLvlUp(){
		hero1.LevelUp(hero1.experience);
		assertEquals(1, hero1.getLevel());
		
		hero1.addExp(100);
		hero1.LevelUp(hero1.experience);
		assertEquals(2, hero1.getLevel());
	}
	
	public void testGetMoveType(){
		assertEquals(MoveType.slash,attack1.getMoveType());
		assertEquals(MoveType.water,attack2.getMoveType());
		assertEquals(MoveType.stab,attack3.getMoveType());
		assertEquals(MoveType.earth,attack4.getMoveType());
	}

	public void testGetMove(){
		assertEquals(MoveStyle.physical,attack1.getMove());
		assertEquals(MoveStyle.magic,attack2.getMove());
		assertEquals(MoveStyle.physical,attack3.getMove());
		assertEquals(MoveStyle.magic,attack4.getMove());
	}
	
	public void testGetPower(){
		assertEquals( (float)(1.5), attack1.getAttackPower(attack1.getMoveType(), attack1.getMove(), 1));
		assertEquals( (float)(3), attack2.getAttackPower(attack2.getMoveType(), attack2.getMove(), 1));
		assertEquals( (float)(1), attack3.getAttackPower(attack3.getMoveType(), attack3.getMove(), 1));
		assertEquals( (float)(3.5), attack4.getAttackPower(attack4.getMoveType(), attack4.getMove(), 1));
	}
	
	public void testItemEffect(){
		assertEquals(5, hero1.ItemList.get(hero1.ItemList.indexOf(potion)).getItemEffect(hero1.Level));
		assertEquals(3, hero1.ItemList.get(hero1.ItemList.indexOf(attUp)).getItemEffect(hero1.Level));
		assertEquals(-5, hero1.ItemList.get(hero1.ItemList.indexOf(acid)).getItemEffect(hero1.Level));	
	}
	
	public void testEquipLvl(){
		assertEquals(1, hero1.EquipList.get(hero1.EquipList.indexOf(helmet)).getEquipLvl());
		assertEquals(2, hero1.EquipList.get(hero1.EquipList.indexOf(torso)).getEquipLvl());
		assertEquals(1, hero1.EquipList.get(hero1.EquipList.indexOf(bracers)).getEquipLvl());
		assertEquals(2, hero1.EquipList.get(hero1.EquipList.indexOf(pants)).getEquipLvl());
		assertEquals(3, hero1.EquipList.get(hero1.EquipList.indexOf(boots)).getEquipLvl());
		assertEquals(2, hero1.EquipList.get(hero1.EquipList.indexOf(gloves)).getEquipLvl());
		assertEquals(3, hero1.EquipList.get(hero1.EquipList.indexOf(pack)).getEquipLvl());
	}
	
	public void testEquipType(){
		assertEquals(EquipmentType.head, hero1.EquipList.get(hero1.EquipList.indexOf(helmet)).getEquipmentType());
		assertEquals(EquipmentType.chest, hero1.EquipList.get(hero1.EquipList.indexOf(torso)).getEquipmentType());
		assertEquals(EquipmentType.arms, hero1.EquipList.get(hero1.EquipList.indexOf(bracers)).getEquipmentType());
		assertEquals(EquipmentType.legs, hero1.EquipList.get(hero1.EquipList.indexOf(pants)).getEquipmentType());
		assertEquals(EquipmentType.feet, hero1.EquipList.get(hero1.EquipList.indexOf(boots)).getEquipmentType());
		assertEquals(EquipmentType.hands, hero1.EquipList.get(hero1.EquipList.indexOf(gloves)).getEquipmentType());
		assertEquals(EquipmentType.back, hero1.EquipList.get(hero1.EquipList.indexOf(pack)).getEquipmentType());
	}
	
	public void testEquipBonus(){
		assertEquals(1, hero1.EquipList.get(hero1.EquipList.indexOf(helmet)).getEquipBonus());
		assertEquals(3, hero1.EquipList.get(hero1.EquipList.indexOf(torso)).getEquipBonus());
		assertEquals(1, hero1.EquipList.get(hero1.EquipList.indexOf(bracers)).getEquipBonus());
		assertEquals(3, hero1.EquipList.get(hero1.EquipList.indexOf(pants)).getEquipBonus());
		assertEquals(5, hero1.EquipList.get(hero1.EquipList.indexOf(boots)).getEquipBonus());
		assertEquals(3, hero1.EquipList.get(hero1.EquipList.indexOf(gloves)).getEquipBonus());
		assertEquals(5, hero1.EquipList.get(hero1.EquipList.indexOf(pack)).getEquipBonus());
		
	}
	
}
