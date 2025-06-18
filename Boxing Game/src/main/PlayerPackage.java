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
	
	private boolean playerLeft;
	
	private SpriteInfo[] frameIcons; //frame, health, stamina
	
	private Sprite[] playerSprite;
	private Player player;
	
	private Sprite[] stanceSprite;
	private SpriteInfo stanceIcon;
	
	//object holds player sprite, stance icon, health, stamina, (name?)
	public PlayerPackage(boolean playerLeft){
		this.setOpaque(false);
		this.setLayout(null);
		this.setBounds(0, 0, 1920, 1080);
		this.setPreferredSize(new Dimension(1920, 1080));
		
		this.playerLeft = playerLeft;
		
		if(playerLeft){
			iniSprites(1);
			stanceIcon = new SpriteInfo(new Vector2D(250, 300), stanceSprite[0]);
			player = new Player(new Vector2D(350, 400), playerSprite, 175, 500, true);
			iniFrameIcons(1);
		} else {
			iniSprites(2);
			stanceIcon = new SpriteInfo(new Vector2D(1920 - 250 - 640, 300), stanceSprite[0]);
			player = new Player(new Vector2D(1094, 400), playerSprite, 175, 500, false);
			iniFrameIcons(2);
		}
		
		player.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.add(player);
		stanceIcon.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.add(stanceIcon);
		
		this.setVisible(true);
	}
	
	private void iniSprites(int playerNumber){ //1 or 2
		playerSprite = new Sprite[33];
		int temp = 0;
		
		playerNumber--;
		String[] ori = {"Left", "Right"};
	
		for(int i = 0; i < playerSprite.length; i++){
			int num = i + 1;
			
			if(i + 1 == playerSprite.length){
				playerSprite[i] = new Sprite("Art/PlayerSprite" + ori[playerNumber] + "/Boxer" + ori[playerNumber] + num + "1.png", "Art/PlayerSprite" + ori[playerNumber] + "/Boxer" + ori[playerNumber] + num + "2.png", true);
				break;
			}
			
			if(temp == 8) temp = 0;
			if(temp == 0 || temp == 1) playerSprite[i] = new Sprite("Art/PlayerSprite" + ori[playerNumber] + "/Boxer" + ori[playerNumber] + num + "1.png", 
					"Art/PlayerSprite" + ori[playerNumber] + "/Boxer" + ori[playerNumber] + num + "2.png", 
					"Art/PlayerSprite" + ori[playerNumber] + "/Boxer" + ori[playerNumber] + num + "3.png");
			else playerSprite[i] = new Sprite("Art/PlayerSprite" + ori[playerNumber] + "/Boxer" + ori[playerNumber] + num + "1.png", "Art/PlayerSprite" + ori[playerNumber] + "/Boxer" + ori[playerNumber] + num + "2.png");
			
			temp++;
		
		}
		
		stanceSprite = new Sprite[32];
		for(int i = 0; i < stanceSprite.length; i++){
			stanceSprite[i] = new Sprite("Art/StanceIcon/stance" + (i + 1) + ".png");
		}
	}
	
	private void iniFrameIcons(int playerNumber){
		int x = 50;
		if(playerNumber == 2) x = 1920 - 896 - x;
		
		frameIcons = new SpriteInfo[3];
		String[] names = {"Frame", "Health", "Stamina"};
		for(int i = 0; i < frameIcons.length; i++){
			frameIcons[i] = new SpriteInfo(new Vector2D(0, 0), new Sprite("Art/FrameSprites/Player" + playerNumber + names[i] + ".png"));
			frameIcons[i].setBounds(x, 0, 1000, 500);
			this.add(frameIcons[i]);
		}

	}
	
	public void adjustHealth(){
		int size = 40 + (player.getHealth() * 10);
		if(playerLeft){
			frameIcons[1].setBounds(50, 0, size, 500);
		} else {
			int x = (1920 - 896 - 50) + (840 - player.getHealth() * 10);
			frameIcons[1].setBounds(x, 0, size, 500);
		}
	}
	
	public void adjustStamina(){
		int size = 40 + (((int) player.getStamina()) * 10);
		if(playerLeft){
			frameIcons[2].setBounds(50, 0, size, 500);
		} else {
			int x = (1920 - 896 - 50) + (840 - ((int) player.getStamina()) * 10);
			frameIcons[2].setBounds(x, 0, size, 500);
		}
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public SpriteInfo getStanceIcon(){
		return stanceIcon;
	}
	
	public SpriteInfo getFrameIcons(int i){
		return frameIcons[i];
	}
	
	public void setStance(int i){
		setStanceIcon(i);
		player.setStance(i);
	}
	
	public void setStanceIcon(int i){
		stanceIcon.setSprite(stanceSprite[i]);
	}
	
}