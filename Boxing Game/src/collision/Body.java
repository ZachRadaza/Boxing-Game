package collision;

import sprites.Vector2D;

public class Body{
	
	
	private boolean left;
	private boolean right;
	
	private Vector2D vec2D;
	private CollisionBox[] colBox; // left and right
	
	private int width;
	private int height;
	
	//object for body of boxer so they cant go thru
	public Body(Vector2D vec2D, int width, int height){
		this.vec2D = vec2D;
		left = true;
		right = true;
		this.width = width;
		this.height = height;
		
		iniColBox();
	}
	
	private void iniColBox(){
		colBox = new CollisionBox[2];
		
		int oriX = vec2D.getX();
		int[] x = {oriX, oriX + width - 75};
		
		for(int i = 0; i < colBox.length; i++){
			colBox[i] = new CollisionBox(new Vector2D(x[i], vec2D.getY()), 75, this.height);
		}
	}
	
	public boolean collisionDetection(Body object){
		boolean ret = true;
		for(int i = 0; i < colBox.length; i++){
			for(int j = 0; j < colBox.length; j++){
				if(colBox[i].collisionDetection(object.getColBox(j))){
					if(i == 0) left = false;
					else right = false;
					ret = false;
				} else{
					if(i == 0) left = true;
					else right = true;
				}
			}
		}
		return ret;
	}
	
	public CollisionBox getColBox(int i){
		return colBox[i];
	}
	
	public boolean getLeft(){
		return left;
	}
	
	public boolean getRight(){
		return right;
	}
	
	public void adjustVectors(Vector2D vec2D){
		colBox[0].adjustCoords(vec2D);
		vec2D.adjustX(75);
		colBox[1].adjustCoords(vec2D);
	}
}