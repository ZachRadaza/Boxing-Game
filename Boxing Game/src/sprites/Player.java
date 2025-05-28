package sprites;

import collision.CollisionBox;
import resources.stopWatchX;

public class Player extends SpriteInfo{
	
	private static final long serialVersionUID = 1L;

	private Sprite[] sprite;
	
	//for states
	private int state; //state is the position the boxer is in, + 1 is guard, 0 = standard, 2 = duck, 4 = left weave, 6 right
	
	private boolean canMove; //if player can move
	private boolean canDash; //if playey can dash
	private boolean dashDir; //direction of dash
	private stopWatchX dashCD; //timer for dash, true for forwards
	private stopWatchX dashL; //dash length, how long they will be dashing for
	private int punch; //if player is punching, 0 for not, 1 for lead hand, 2 for rear
	private boolean block;
	private boolean dodge;
	
	private CollisionBox hands; //hands for when punching
	
	
	public Player(Vector2D vector2D, Sprite[] sprite, int width, int height){ //change sprite when changing states
		super(vector2D, sprite[0], width, height);
		
		this.sprite = sprite;
		this.state = 0;
		this.canMove = true;
		this.canDash = true;
		this.dashDir = false;
		this.dashCD = new stopWatchX(1500);
		this.dashL = new stopWatchX(100);
		this.punch = 0;
		this.block = false;
		this.dodge = false;
		
	}
	
	public int getState(){
		return state;
	}
	
	public boolean getBlock(){
		return block;
	}
	
	public int getPunch(){
		return punch;
	}
	
	public boolean getCanDash(){
		return canDash;
	}
	
	public boolean getDashDir(){
		return dashDir;
	}
	
	public boolean getDashCD(){
		return dashCD.isTimeUp();
	}
	
	public boolean getDashL(){
		return dashL.isTimeUp();
	}
	
	
	public void setState(int i){
		state = i;
		setSprite(sprite[i]);
	}
	
	public void setBlock(boolean b){
		block = b;
	}
	
	public void setPunch(int i){
		punch = i;
	}
	
	public void setCanDash(boolean b){
		canDash = b;
	}
	
	public void setDashDir(boolean b){
		dashDir = b;
	}
	
	public void resetDashCD(){
		dashCD.resetWatch();
	}
	
	public void resetDashL(){
		dashL.resetWatch();
	}
	
	public void updateCoords(int x, int y){
		if(canMove){
			this.getVector2D().setX(x);
			this.getVector2D().setY(y);
			this.getColBox().adjustCoords(getVector2D());
		}
	}
	
	
}