package sprites;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import collision.CollisionBox;

public class SpriteInfo extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector2D vector;
	private Sprite sprite;
	
	private CollisionBox colBox;
	
	public SpriteInfo(Vector2D vector, Sprite sprite, int width, int height){
		this.setOpaque(false);
		this.vector = vector;
		this.sprite = sprite;
		this.colBox = new CollisionBox(vector, width, height);
		
		this.setVisible(true);
		this.revalidate();
		this.repaint();
	}
	
	public SpriteInfo(Vector2D vector, Sprite sprite){
		this.setOpaque(false);
		this.vector = vector;
		this.sprite = sprite;
		
		this.setVisible(true);
		this.revalidate();
		this.repaint();
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (sprite != null) {
            g.drawImage(sprite.getImage(), vector.getX(), vector.getY(), this);
        }
    }
    
    public Vector2D getVector2D(){
    	return vector;
    }
    
    public CollisionBox getColBox(){
    	return colBox;
    }
    
    public void setSprite(Sprite sprite){
    	this.sprite = sprite;
    }
    
	public boolean checkCollisions(ArrayList<SpriteInfo> object){
		if(colBox == null) return false; //makes sure I am not stupid
		for(int i = 0; i < object.size(); i++){
			if(this.colBox.collisionDetection(object.get(i).getColBox())) return true;
		}
		return false;
	}
}