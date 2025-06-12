package main;

import javax.swing.JPanel;

import resources.stopWatchX;
import sprites.Player;
import sprites.Sprite;
import sprites.SpriteInfo;
import sprites.Vector2D;

public class GamePackage extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SpriteInfo round;
	private SpriteInfo roundNumber;
	private Sprite[] roundNumberSprite;
	private stopWatchX roundTimerVisible;
	
	private SpriteInfo ko;
	private Sprite[] koSprite; //ko, player1 wins, player 2 wins
	private stopWatchX koTimer;
	
	private SpriteInfo background;
	private SpriteInfo ring;
	
	private int win; //0 is nobody won, 1 is player 1 won, 2 is etc
	
	// for all sprites used in game
	public GamePackage(){
		this.setBounds(0, 0, 1920, 1080);
		this.setOpaque(false);
		this.setLayout(null);
		
		win = 0;
		
		roundTimerVisible = new stopWatchX(2500);
		koTimer = new stopWatchX(2500);
		
		round = new SpriteInfo(new Vector2D((1920/2) - (640 / 2) - 100, 300), new Sprite("Art/ResourceSprites/round.png"));
		round.setBounds(0, 0, 1920,1080);
		
		roundNumberSprite = new Sprite[3];
		for(int i = 0; i < roundNumberSprite.length; i++){
			roundNumberSprite[i] = new Sprite("Art/ResourceSprites/" + (i + 1) + ".png");
		}
		roundNumber = new SpriteInfo(new Vector2D((1920/2) + (640 / 2) - 100, 300), roundNumberSprite[0]);
		
		koSprite = new Sprite[3];
		String[] spriteName = {"KO", "Player1Wins", "Player2Wins"};
		for(int i = 0; i < koSprite.length; i++){
			koSprite[i] = new Sprite("Art/ResourceSprites/" + spriteName[i] + ".png");
		}
		
		ko = new SpriteInfo(new Vector2D((1920/2) - (640 / 2), 300), koSprite[0]);
		ko.setVisible(false);
		
		round.setBounds(0, 0, 1920,1080);
		roundNumber.setBounds(0, 0, 1920,1080);
		ko.setBounds(0, 0, 1920,1080);
		
		this.add(ko);
		this.add(round);
		this.add(roundNumber);
	}
	
	public void setRoundNumber(int i){
		if(i <= roundNumberSprite.length)
			roundNumber.setSprite(roundNumberSprite[i - 1]);
	}
	
	public int getWin(){
		return win;
	}
	
	public SpriteInfo getRound(){
		return round;
	}
	
	public SpriteInfo getRoundNumber(){
		return roundNumber;
	}
	
	public SpriteInfo getKO(){
		return ko;
	}
	
	public Sprite getKOSprite(int i){
		return koSprite[i];
	}
	
	public boolean getKOTimer(){
		return koTimer.isTimeUp();
	}
	
	public boolean getRoundTimerVisible(){
		return roundTimerVisible.isTimeUp();
	}
	
	public void checkRoundTimerVisible(){
		if(roundTimerVisible.isTimeUp()){
			round.setVisible(false);
			roundNumber.setVisible(false);
		}
	}
	
	public void resetRoundTimerVisible(){
		roundTimerVisible.resetWatch();
	}
	
	public void koSwitchSprite(){
		ko.setSprite(koSprite[win]);
	}
	
	public void checkKO(Player player1, Player player2){
		if(player1.getHealth() <= 0 && win == 0){
			ko.setVisible(true);
			koTimer.resetWatch();
			win = 2;
		} else if(player2.getHealth() <= 0 && win == 0){
			ko.setVisible(true);
			koTimer.resetWatch();
			win = 1;
		}
	}
}