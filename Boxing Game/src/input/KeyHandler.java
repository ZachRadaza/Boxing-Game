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
		
		switch(key){
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_SPACE:
			block = true;
			break;
		case KeyEvent.VK_SHIFT:
			shift = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		
		switch(key){
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_SPACE:
			block = false;
			break;
		case KeyEvent.VK_SHIFT:
			shift = false;
			break;
		}
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