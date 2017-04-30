package net.rSoft.LD38;

import java.awt.*;

import javax.swing.JFrame;

import net.rSoft.LD38.GUI.Mouse;
import net.rSoft.LD38.GUI.Menu.AboutMenu;
import net.rSoft.LD38.GUI.Menu.StartMenu;
import net.rSoft.LD38.GUI.Menu.WinMenu;

public class Main extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private String name = "Battle for the Soviet Onion";
	
	public static final int WIDTH = 960, HEIGHT = 540;
	public Dimension size = new Dimension(WIDTH, HEIGHT);
	
	private boolean isRunning = false;

	public boolean gameOn = false, about = false, win = false;
	
	private Image screen;
	private Thread thread;
	
	public static Main mn;
	
	public InputHandler ih;
	public Mouse ms;
	
	public Game gm;
	public StartMenu sm;
	public AboutMenu am;
	public WinMenu wm;
	
	
	public static void main(String[] args){
		mn = new Main();
	}
	
	public Main(){
		thread = new Thread(this);
		
		ih = new InputHandler();
		ms = new Mouse();
		
		sm = new StartMenu(0, 0, WIDTH, HEIGHT, "Title");
		am = new AboutMenu(0, 0, WIDTH, HEIGHT, "About");
		wm = new WinMenu(0, 0, WIDTH, HEIGHT, "EndScreen");
		
		addKeyListener(ih);
		addMouseListener(ms);
		
		setSize(size);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(name);
		
		start();
	}
	
	public void start(){
		isRunning = true;
		thread.start();
	}
	
	public void tick(){
		ms.tick();
		
		if(gameOn){
			if(!win){
				gm.tick();
			}
		}else{
			if(about){
				am.tick();
			}else{
				sm.tick();
			}
		}
	}
	
	public void render(){
		Graphics g = screen.getGraphics();
		
		g.setColor(new Color(109, 24, 0));
		g.fillRect(0, 0, size.width, size.height);
		
		if(gameOn){
			if(win){
				wm.render(g);
			}else{
				gm.render(g);
			}
		}else{
			if(about){
				am.render(g);
			}else{
				sm.render(g);
			}
		}
		
		g= getGraphics();
		
		g.drawImage(screen, 0, 0, size.width, size.height, null);
	}
	
	public void run() {
		screen = createVolatileImage(WIDTH, HEIGHT);
		
		while(isRunning){
			tick();
			render();
			
			try{
				Thread.sleep(20);
			}catch(Exception e){ }
		}
	}
}