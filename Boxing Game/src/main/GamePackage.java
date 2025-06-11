package main;

import javax.swing.JPanel;

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
	private SpriteInfo ko;
	private SpriteInfo background;
	private SpriteInfo ring;
	
	
	// for all sprites used in game
	public GamePackage(){
		
		round = new SpriteInfo(new Vector2D((1920/2) - (640 / 2), 300), new Sprite("Art/ResourceSprites/round.png"));
		round.setBounds(0, 0, 1920,1080);
		
		roundNumberSprite = new Sprite[3];
		for(int i = 0; i < roundNumberSprite.length; i++){
			roundNumberSprite[i] = new Sprite("Art/ResourceSprites/" + (i + 1) + ".png");
		}
		roundNumber = new SpriteInfo(new Vector2D((1920/2) + (640 / 2), 300), roundNumberSprite[0]);
		
		ko = new SpriteInfo(new Vector2D((1920/2) - (640 / 2), 300), new Sprite("Art/ResourceSprites/KO.png"));
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
	
	public SpriteInfo getRound(){
		return round;
	}
	
	public SpriteInfo getRoundNumber(){
		return roundNumber;
	}
	
	public SpriteInfo getKO(){
		return ko;
	}
}