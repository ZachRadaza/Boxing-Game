package sprites;

public class Vector2D{

	private int x;
	private int y;
	
	// Constructor
	public Vector2D(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	// Methods
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
	
	public void adjustX(int adjustment){
		this.x += adjustment;
	}
	
	public void adjustY(int adjustment){
		this.y += adjustment;
	}
}