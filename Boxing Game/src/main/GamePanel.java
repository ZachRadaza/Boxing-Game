package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import input.Actions;
import input.CursorHandler;
import input.KeyHandler;
import input.MouseHandler;
import sprites.Player;
import sprites.Sprite;
import sprites.Vector2D;

public class GamePanel extends JPanel implements Runnable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//for the panel
	private final int width = Main.getFrame().getWidth();
	private final int height = Main.getFrame().getHeight();
	private int fps = 100;
	
	//game fields
	private KeyHandler keyHandler = new KeyHandler();
	private CursorHandler cursorHandler = new CursorHandler();
	private MouseHandler mouseHandler = new MouseHandler();
	private Thread gameThread;
	
	//player fields
	private Sprite[] playerSprite;
	private Player player;

	public GamePanel(){
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		
		this.add(cursorHandler);
		
		this.addMouseListener(mouseHandler);
		
		setPlayerSprite();
		
		player = new Player(new Vector2D(100, 100), playerSprite, 128, 128);
		player.setBounds(player.getVector2D().getX(), player.getVector2D().getY(), this.width, this.height);
		this.add(player);
		
		startGameThread();
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
		Actions.action(player, keyHandler, cursorHandler, mouseHandler);
	}
	
	//repaints everything every second
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
	}
	
	//loads everthing at the start
	private void start(){
		
	}
	
	private void setPlayerSprite(){
		playerSprite = new Sprite[8];
		for(int i = 0; i < playerSprite.length; i++){
			int num = i + 1;
			playerSprite[i] = new Sprite("Art/PlayerSpriteLeft/test" + num + "1.png", "Art/PlayerSpriteLeft/test"  + num + "2.png", "Art/PlayerSpriteLeft/test" + num + "3.png");
		}
	}
	
}