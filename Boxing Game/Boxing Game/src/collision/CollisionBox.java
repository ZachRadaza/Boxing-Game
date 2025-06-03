package collision;

import sprites.Vector2D;

public class CollisionBox{
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int width;
	private int height;
	
	boolean collision;
	
	public CollisionBox(Vector2D vector, int width, int height){
		x1 = vector.getX();
		y1 = vector.getY();
		x2 = x1 + width;
		y2 = y1 + height;
		this.width = width;
		this.height = height;
		collision = false;
	}
	
	public int getX1(){
		return x1;
	}
	
	public int getY1(){
		return y1;
	}
	
	public int getX2(){
		return x2;
	}
	
	public int getY2(){
		return y2;
	}
	
	public boolean getCollision(){
		return collision;
	}
	
	public void setX1(int x1){
		this.x1 = x1;
	}
	
	public void setX2(int x2){
		this.x2 = x2;
	}
	
	public void setY1(int y1){
		this.y1 = y1;
	}
	
	public void setY2(int y2){
		this.y2 = y2;
	}
	
	public void setCollision(boolean c){
		collision = c;
	}
	
	public void adjustCoords(Vector2D vector){
		setX1(vector.getX());
		setX2(vector.getX() + width);
		setY1(vector.getY());
		setY2(vector.getY() + height);
	}
	
	public boolean collisionDetection(CollisionBox object){
		if((this.x1 < object.getX2()) &&
			(this.x2 > object.getX1()) &&
			(this.y1 < object.getY2()) &&
			(this.y2 > object.getY1())){
			
			collision = true;
			object.setCollision(true);
			return true;
		} else return false;
	}
	
}