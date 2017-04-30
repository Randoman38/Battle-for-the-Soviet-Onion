package net.rSoft.LD38;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key){
		case KeyEvent.VK_A:
			if(Main.mn.gm != null || (Main.mn.gameOn && Main.mn.gm.lvl.ply.didPunch)){
				Main.mn.gm.lvl.ply.dir = 'l';
				Main.mn.gm.lvl.ply.facingDir = 'l';
			}
			break;
		case KeyEvent.VK_D:
			if(Main.mn.gm != null || (Main.mn.gameOn && Main.mn.gm.lvl.ply.didPunch)){
				Main.mn.gm.lvl.ply.dir = 'r';
				Main.mn.gm.lvl.ply.facingDir = 'r';
			}
			break;
		case KeyEvent.VK_W:
			if(Main.mn.gm != null || (Main.mn.gameOn && Main.mn.gm.lvl.ply.didPunch)){
				Main.mn.gm.lvl.ply.dirY = 'u';
			}
			break;
		case KeyEvent.VK_S:
			if(Main.mn.gm != null || (Main.mn.gameOn && Main.mn.gm.lvl.ply.didPunch)){
				Main.mn.gm.lvl.ply.dirY = 'd';
			}
			break;
		case KeyEvent.VK_SPACE:
			if(Main.mn.gameOn || (Main.mn.gameOn && Main.mn.gm.lvl.ply.didPunch)){
				if(!Main.mn.win){
					if(Main.mn.gm != null && !Main.mn.gm.lvl.ply.isJumping && !Main.mn.gm.lvl.ply.canFall()){
						Main.mn.gm.lvl.ply.isJumping = true;
						if(Main.mn.gm.lvl.ply.superJumping){
							Main.mn.gm.lvl.ply.stamina-=45;
						}
						Sound.jump.play();
					}
				}else{
					Main.mn.win = false;
					Main.mn.gameOn = false;
				}
			}else{
				if(Main.mn.about){
					Main.mn.am.screen++;
				}
			}
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(Main.mn.gameOn || (Main.mn.gameOn && Main.mn.gm.lvl.ply.didPunch)){
			switch(key){
			case KeyEvent.VK_A:
				if(Main.mn.gm != null && Main.mn.gm.lvl.ply.dir != 'r'){
					Main.mn.gm.lvl.ply.dir = 's';
				}
				break;
			case KeyEvent.VK_D:
				if(Main.mn.gm != null && Main.mn.gm.lvl.ply.dir != 'l'){
					Main.mn.gm.lvl.ply.dir = 's';
				}
				break;
			case KeyEvent.VK_W:
				if(Main.mn.gm != null && Main.mn.gm.lvl.ply.dirY != 'd'){
					Main.mn.gm.lvl.ply.dirY = 's';
				}
				break;
			case KeyEvent.VK_S:
				if(Main.mn.gm != null && Main.mn.gm.lvl.ply.dirY != 'u'){
					Main.mn.gm.lvl.ply.dirY = 's';
				}
				break;
			case KeyEvent.VK_I:
				if(Main.mn.gm.lvl.ply.invUp){
					Main.mn.gm.lvl.ply.invUp = false;
				}else{
					Main.mn.gm.lvl.ply.invUp = true;
				}
				break;
			}
		}
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
}