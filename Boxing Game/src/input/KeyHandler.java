package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	private boolean up = false;
	private boolean down = false;
	private boolean right = false;
	private boolean left = false;
	
	private boolean block = false;
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		char key = arg0.getKeyChar();
		
		switch(key){
		case 'w':
			up = true;
			break;
		case 's':
			down = true;
			break;
		case 'd':
			right = true;
			break;
		case 'a':
			left = true;
			break;
		case ' ':
			block = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		char key = arg0.getKeyChar();
		
		switch(key){
		case 'w':
			up = false;
			break;
		case 's':
			down = false;
			break;
		case 'd':
			right = false;
			break;
		case 'a':
			left = false;
			break;
		case ' ':
			block = false;
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
}