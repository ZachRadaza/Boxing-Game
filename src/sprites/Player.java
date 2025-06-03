package sprites;

import collision.CollisionBox;
import resources.stopWatchX;

public class Player extends SpriteInfo{
	
	private static final long serialVersionUID = 1L;

	private Sprite[] sprite;
	
	//for states
	private int stance; //stance boxer is in, + 1 is guard, + 2-3 is cross, +4-5 is hook, +6-7 is upper 0 = standard, 8 = duck, 16 = left weave, 24 = right
	private int stanceLast; //last state player was in
	private stopWatchX stanceL; //how long they can be in that state
	
	//movement
	private boolean canMove; //if player can move
	private boolean canDash; //if playey can dash
	private boolean dashDir; //direction of dash
	private stopWatchX dashCD; //timer for dash, true for forwards
	private stopWatchX dashL; //dash length, how long they will be dashing for
	
	//punch
	private int punch; //if player is punching, even is lead hand, odd is rear
	private stopWatchX punchL;
	private CollisionBox hands; //hands for when punching
	
	private boolean block;
	private boolean dodge;
	
	//other stuff
	public int health;
	public int stamina;
	public double staminaRegen; //rate stamina regenerates
	
	public Player(Vector2D vector2D, Sprite[] sprite, int width, int height){ //change sprite when changing states
		super(vector2D, sprite[0], width, height);
		
		this.sprite = sprite;
		
		this.stance = 0;
		this.stanceLast = stance;
		this.stanceL = new stopWatchX(2000);
		
		this.canMove = true;
		this.canDash = true;
		this.dashDir = false;
		this.dashCD = new stopWatchX(1500);
		this.dashL = new stopWatchX(100);
		
		this.punch = 0;
		this.punchL = new stopWatchX(200);
		Vector2D colVec = new Vector2D(vector2D.getX() + 175, vector2D.getY() + 100);
		this.hands = new CollisionBox(colVec, 220, 100);
		hands.setActive(false);
		
		this.block = false;
		this.dodge = false;
		
		this.health = 100;
		this.stamina = 100;
		this.staminaRegen = 1.00;
		
	}
	
	public int getStance(){
		return stance;
	}
	
	public int getStanceLast(){
		return stanceLast;
	}
	
	public boolean getStanceL(){
		return stanceL.isTimeUp();
	}
	
	public boolean getBlock(){
		return block;
	}
	
	public int getPunch(){
		return punch;
	}
	
	public boolean getPunchL(){
		return punchL.isTimeUp();
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
	
	public int getHealth(){
		return health;
	}
	
	public int getStamina(){
		return stamina;
	}
	
	public double getStaminaRegen(){
		return staminaRegen;
	}
	
	
	public void setStance(int i){
		stance = i;
		if(!stanceL.isTimeUp()) stanceLast = stance;
		stanceL.resetWatch();
		setSprite(sprite[i]);
		adjustHitBox(i);
	}
	
	public void resetStateL(){
		stanceL.resetWatch();
	}
	
	public void setBlock(boolean b){
		block = b;
	}
	
	public void setPunch(int i){
		if(punchL.isTimeUp() && i > 0 && canPunch(i)){
			sprite[getStance() + i].resetStopWatch();
			setStance(getStance() + i);	
			punched(i);
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
	
	public void setStaminaRegen(double staminaRegen){
		this.staminaRegen = staminaRegen;
	}
	
	public void adjustHealth(int ad){
		health += ad;
	}
	
	public void adjustStamina(int ad){
		stamina += ad;
	}
	
	public void adjustStaminaRegen(double ad){
		staminaRegen += ad;
	}
	
	public void updateCoords(int x, int y){
		if(canMove){
			this.getVector2D().setX(x);
			this.getVector2D().setY(y);
			this.getColBox().adjustCoords(getVector2D());
		}
	}
	
	private boolean canPunch(int i){ //checks if he can punch based on stamina
		if(i == 2){
			return stamina >= 5;
		} else if (i == 3){
			return stamina >= 10;
		} else {
			return stamina >= 15;
		}
	}
	
	private void punched(int i){ //when player punches
		switch(i){
		case 2:
			adjustStamina(5);
		case 3:
			adjustStamina(-10);
			//hitbox
			hands.setWidth(220);
			hands.setHeight(100);
			hands.adjustCoords(new Vector2D(getColBox().getX1(), getColBox().getY1() + 100));
			hands.setActive(true);
			break;
		default:
			adjustStamina(-15);
			//hitbox
			if(block){
				hands.setWidth(150);
				hands.setHeight(100);
			} else {
				hands.setWidth(150);
				hands.setHeight(225);
			}
			hands.adjustCoords(new Vector2D(getColBox().getX1(), getColBox().getY1() + 100));
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
			getColBox().setHeight(400);
		} else{
			getColBox().setWidth(275);
			getColBox().setHeight(450);
		}
	}
	
}