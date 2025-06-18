package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	private boolean up = false;
	private boolean down = false;
	private boolean right = false;
	private boolean left = false;
	
	private boolean block = false;
	private boolean shift = false;
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		int key = arg0.getKeyCode();
		
		if(key == KeyEvent.VK_W) up = true;
		else if(key == KeyEvent.VK_S) down = true;
		else if(key == KeyEvent.VK_D) right = true;
		else if(key == KeyEvent.VK_A) left = true;
		else if(key == KeyEvent.VK_SPACE) block = true;
		else if(key == KeyEvent.VK_SHIFT) shift = true;
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		
		if(key == KeyEvent.VK_W) up = false;
		else if(key == KeyEvent.VK_S) down = false;
		else if(key == KeyEvent.VK_D) right = false;
		else if(key == KeyEvent.VK_A) left = false;
		else if(key == KeyEvent.VK_SPACE) block = false;
		else if(key == KeyEvent.VK_SHIFT) shift = false;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//char key = arg0.getKeyChar();
		
		
	}
	
	public boolean getUp(){
		return up;
	}
	
	public boolean getDown(){
		return down;
	}
	
	public boolean getRight(){
		return right;
	}
	
	public boolean getLeft(){
		return left;
	}
	
	public boolean getBlock(){
		return block;
	}
	
	public boolean getShift(){
		return shift;
	}
}