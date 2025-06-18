package sprites;

import collision.Body;
import collision.CollisionBox;
import resources.stopWatchX;

public class Player extends SpriteInfo{
	
	private static final long serialVersionUID = 1L;

	private Sprite[] sprite;
	
	//for states
	private int stance; //stance boxer is in, + 1 is guard, + 2-3 is cross, +4-5 is hook, +6-7 is upper 0 = standard, 8 = duck, 16 = left weave, 24 = right
	
	//movement
	private boolean canDash; //if playey can dash
	private boolean dashDir; //direction of dash
	private stopWatchX dashCD; //timer for dash, true for forwards
	private stopWatchX dashL; //dash length, how long they will be dashing for
	private Body body;
	
	//punch
	private int punch; //if player is punching, even is lead hand, odd is rear
	private stopWatchX punchL;
	private CollisionBox hands; //hands for when punching
	
	private boolean block;
	private boolean dodge;
	
	//other stuff
	public int health;
	private float stamina;
	private float staminaRegen; //rate stamina regenerates
	private boolean left; //if it is the left player
	
	public Player(Vector2D vector2D, Sprite[] sprite, int width, int height, boolean left){ //change sprite when changing states
		super(vector2D, sprite[0], width, height);
		
		this.sprite = sprite;
		
		this.stance = 0;
		
		this.canDash = true;
		this.dashDir = false;
		this.dashCD = new stopWatchX(1500);
		this.dashL = new stopWatchX(100);
		this.body = new Body(vector2D, width, height);
		
		this.punch = 0;
		this.punchL = new stopWatchX(200);
		Vector2D colVec = new Vector2D(vector2D.getX() + 175, vector2D.getY() + 100);
		this.hands = new CollisionBox(colVec, 220, 100);
		hands.setActive(false);
		
		this.block = false;
		this.dodge = false;
		
		this.health = 80;
		this.stamina = 80f;
		this.staminaRegen = 0.01f;
		
		this.left = left;
	}
	
	public int getStance(){
		return stance;
	}
	
	public int getStanceSimple(){ //simplified stances, 0 for up, 1 for down, 2 for left , and 3 for right
		return stance / 8;
		
	}
	
	public Sprite getSprite(int i){
		return sprite[i];
	}
	
	public boolean getBlock(){
		return block;
	}
	
	public boolean getDodge(){
		return dodge;
	}
	
	public int getPunch(){
		return punch;
	}
	
	public boolean getPunchL(){
		return punchL.isTimeUp();
	}
	
	public CollisionBox getHands(){
		return hands;
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
	
	public Body getBody(){
		return body;
	}
	
	public int getHealth(){
		return health;
	}
	
	public float getStamina(){
		return stamina;
	}
	
	public float getStaminaRegen(){
		return staminaRegen;
	}
	
	public void setStance(int i){
		stance = i;
		setSprite(sprite[i]);
		adjustHitBox(i);
	}
	
	public void setBlock(boolean b){
		block = b;
	}
	
	public void setDodge(boolean b){
		dodge = b;
	}
	
	public void setPunch(int i){
		if(punchL.isTimeUp() && i > 0){
			punched(i);
			sprite[getStance() + i].resetStopWatch();
			punchL.resetWatch();
		} else {
			hands.setActive(false);
		}
	}
	
	public void resetPunchL(){
		punchL.resetWatch();
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
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public void setStamina(int stamina){
		this.stamina = stamina;
	}
	
	public void setStaminaRegen(float staminaRegen){
		this.staminaRegen = staminaRegen;
	}
	
	public void adjustHealth(int ad){
		health += ad;
	}
	
	public void adjustStamina(float ad){
		if(stamina + ad <= 80) stamina += ad;
	}
	
	public void adjustStaminaRegen(double ad){
		staminaRegen += ad;
	}
	
	public void updateCoords(int x, int y){
		this.getVector2D().setX(x);
		this.getVector2D().setY(y);
		this.getColBox().adjustCoords(getVector2D());
		body.adjustVectors(new Vector2D(x, y));
	}
	
	public boolean canPunch(int i){ //checks if he can punch based on stamina
		if(i == 2){
			return stamina >= 5;
		} else if (i == 3){
			return stamina >= 10;
		} else {
			return stamina >= 20;
		}
	}
	
	private void punched(int i){ //when player punches
		int adjustment = 0;
		if(!left) adjustment = -100;
		switch(i){
		case 2:
			adjustStamina(5);
		case 3:
			adjustStamina(-10);
			//hitbox
			hands.setWidth(270);
			hands.setHeight(100);
			hands.adjustCoords(new Vector2D(getColBox().getX1() + adjustment, getColBox().getY1() + 50));
			hands.setActive(true);
			break;
		default:
			adjustStamina(-20);
			//hitbox
			if(block){
				hands.setWidth(220);
				hands.setHeight(225);
			} else {
				hands.setWidth(220);
				hands.setHeight(100);
			}
			hands.adjustCoords(new Vector2D(getColBox().getX1() + adjustment, getColBox().getY1() + 50));
			hands.setActive(true);
			break;
		}
	}
	
	private void adjustHitBox(int i){
		if(i < 8){
			getColBox().setWidth(175);
			getColBox().setHeight(500);
		} else if(i < 16){
			getColBox().setWidth(350);
			getColBox().setHeight(380);
		} else{
			getColBox().setWidth(275);
			getColBox().setHeight(450);
		}
	}
	
}