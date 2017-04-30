package net.rSoft.LD38;

import java.awt.Graphics;

import net.rSoft.LD38.GUI.CharList;
import net.rSoft.LD38.GUI.StaminaBar;
import net.rSoft.LD38.GUI.Menu.Inventory;
import net.rSoft.LD38.World.Level;

public class Game {
	public int collectiblesCollected = 0, totalCollectibles = 11;
	public int onion = 0;
	
	public CharList enemLeft;
	
	public Level lvl;
	public Inventory inv;
	public StaminaBar sb;
	
	public Game(){
		lvl = new Level(Level.level1);
		
		enemLeft = new CharList("enemies left:", 10, 510, 18);
		
		onion = lvl.onion;
		inv = new Inventory(0, 26, Main.WIDTH, Main.HEIGHT, "Inventory", onion);
		sb = new StaminaBar(10, 30);
	}
	
	public void tick(){
		if(lvl.ply.invUp){
			inv.tick();
		}else{
			lvl.tick();
		}
		
		onion = lvl.onion;
		inv.on.type = onion;
		
		inv.on.update();
		
		enemLeft.str = "enemies left: ".concat(Integer.toString(lvl.enemy.size()));
	}
	
	public void render(Graphics g){
		if(lvl.ply.invUp){
			inv.render(g);
		}else{
			lvl.render(g);
			sb.render(g);
		}
		
		CharList.drawList(g, enemLeft);
	}
}