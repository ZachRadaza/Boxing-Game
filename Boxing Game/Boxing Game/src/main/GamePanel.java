package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import input.Actions;
import input.CursorHandler;
import input.KeyHandler;
import input.MouseHandler;

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
	private CursorHandler cursorHandler = new CursorHandler();
	private MouseHandler mouseHandler = new MouseHandler();
	private Thread gameThread;
	
	//player fields
	private PlayerPackage player1;

	public GamePanel(){
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		
		this.add(cursorHandler);
		
		this.addMouseListener(mouseHandler);
		
		player1 = new PlayerPackage();
		//player1.setBounds(0, 0, this.width, this.height);
		this.add(player1);
		
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
		Actions.action(player1, keyHandler, cursorHandler, mouseHandler);
	}
	
	//repaints everything every second
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
	}
	
	//loads everthing at the start
	private void start(){
		
	}
	
}