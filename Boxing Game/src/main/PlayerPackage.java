package main;

import java.awt.Dimension;

import javax.swing.JPanel;

import sprites.Player;
import sprites.Sprite;
import sprites.SpriteInfo;
import sprites.Vector2D;

public class PlayerPackage extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Sprite[] playerSprite;
	private Player player;
	
	private Sprite[] stanceSprite;
	private SpriteInfo stanceIcon;
	
	//object holds player sprite, stance icon, health, stamina, (name?)
	public PlayerPackage(){
		this.setOpaque(false);
		this.setLayout(null);
		this.setBounds(0, 0, 1920, 1080);
		this.setPreferredSize(new Dimension(1920, 1080));

		iniSprites();
		
		player = new Player(new Vector2D(350, 400), playerSprite, 128, 128);
		player.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.add(player);
		
		stanceIcon = new SpriteInfo(new Vector2D(450, 300), stanceSprite[0]);
		stanceIcon.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.add(stanceIcon);
		
		this.setVisible(true);
	}
	
	private void iniSprites(){
		playerSprite = new Sprite[8];
		for(int i = 0; i < playerSprite.length; i++){
			int num = i + 1;
			playerSprite[i] = new Sprite("Art/PlayerSpriteLeft/BoxerLeft" + num + "1.png", "Art/PlayerSpriteLeft/BoxerLeft"  + num + "2.png", "Art/PlayerSpriteLeft/BoxerLeft" + num + "3.png");
		}
		
		stanceSprite = new Sprite[12];
		for(int i = 0; i < stanceSprite.length; i++){
			stanceSprite[i] = new Sprite("Art/StanceIcon/stance" + (i + 1) + ".png");
		}
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public SpriteInfo getStanceIcon(){
		return stanceIcon;
	}
	
	
	public void setStance(int i){
		player.setStance(i);
		stanceIcon.setSprite(stanceSprite[i]);
	}
	
}