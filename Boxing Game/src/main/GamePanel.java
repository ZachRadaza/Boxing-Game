package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import input.Actions;
import input.CursorHandler;
import input.KeyHandler;
import input.KeyHandler2Player;

public class GamePanel extends JPanel implements Runnable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//for the panel
	public final int width = Main.getFrame().getWidth();
	public final int height = Main.getFrame().getHeight();
	private int fps = 100;
	
	//game fields
	private KeyHandler keyHandler = new KeyHandler();
	private KeyHandler2Player keyHandler2Player = new KeyHandler2Player();
	private CursorHandler cursorHandler = new CursorHandler();
	private Thread gameThread;
	private boolean singlePlayer;
	private GamePackage gamePackage;
	
	//player fields
	private PlayerPackage player1;
	private PlayerPackage player2;

	public GamePanel(boolean singlePlayer){
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		
		this.singlePlayer = singlePlayer;
		
		if(singlePlayer){
			this.addKeyListener(keyHandler);
			this.add(cursorHandler);
		} else {
			this.addKeyListener(keyHandler2Player);
			this.setFocusable(true);
		}
		this.setFocusable(true);

		iniPlayersNGame();
	
		startGameThread();
	}
	
	private void iniPlayersNGame(){
		player1 = new PlayerPackage(true);
		player2 = new PlayerPackage(false);
		gamePackage = new GamePackage();
		
		this.add(gamePackage);
		//done so players are in front due to printing order
		this.add(player1.getPlayer());
		this.add(player2.getPlayer());
		
		this.add(player1.getStanceIcon());
		this.add(player2.getStanceIcon());
		
		for(int i = 0; i < 3; i++){
			this.add(player1.getFrameIcons(i));
			this.add(player2.getFrameIcons(i));
		}
	}
	
	private void startGameThread(){
		gameThread = new Thread(this);
		gameThread.start();
	}

	//runs when thread starts
	@Override
	public void run() {
		// TODO Auto-generated method stub
		start();
		
		double drawInterval = 1000000000/fps;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null){
			
			update();
			
			repaint();
			
			//pauses to not make it refresh so fast
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime /= 1000000;
				
				if(remainingTime < 0) remainingTime = 0;
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.setVisible(true);
			this.revalidate();
			this.repaint();
		}
		
	}
	
	//updates every second
	private void update(){
		if(singlePlayer) Actions.action(player1, keyHandler, cursorHandler, player2);
		else{
			Actions.action(player1, keyHandler2Player, player2, 0);
			Actions.action(player2, keyHandler2Player, player1, 1);
		}
		
		gamePackage.checkKO(player1.getPlayer(), player2.getPlayer());
		if(gamePackage.getKOTimer() && gamePackage.getWin() != 0) gamePackage.koSwitchSprite();
		if(gamePackage.checkRoundTimer()) gamePackage.resetCoords(player1.getPlayer(), player2.getPlayer());;
	}
	
	//repaints everything every second
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
	}
	
	//loads everthing at the start
	private void start(){
		
	}
	
}