package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler2Player implements KeyListener{

	private boolean[] up = {false, false};
	private boolean[] down = {false, false};
	private boolean[] right = {false, false};
	private boolean[] left = {false, false};
	
	private boolean[] block = {false, false};
	private boolean[] shift = {false, false};
	//for mouse
	private boolean[] leanLeft = {false, false}; //last pressed button, to make it only do it once when pressed
	private boolean[] leanRight = {false, false};
	private int[] currentStance = {0, 0}; //clockwise, 3 is the most
	private boolean[] punchLeft = {false, false};
	private boolean[] punchRight = {false, false};
	private boolean[][] stances = {{false, false, false, false}, {false, false, false, false}}; //middle, bottom, left, right
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		int key = arg0.getKeyCode();
		
		if(key == KeyEvent.VK_W) up[0] = true;
		if(key == KeyEvent.VK_S) down[0] = true;
		if(key == KeyEvent.VK_D) right[0] = true;
		if(key == KeyEvent.VK_A) left[0] = true;
		if(key == KeyEvent.VK_SPACE) block[0] = true;
		if(key == KeyEvent.VK_SHIFT) shift[0] = true;
		if(key == KeyEvent.VK_Q && leanLeft[0] == false){ 
			leanLeft[0] = true;
			currentStance[0]--;
			if(currentStance[0] < 0) currentStance[0] = 3;
			adjustLean(0);
		}
		if(key == KeyEvent.VK_E && leanRight[0] == false){ 
			leanRight[0] = true;
			currentStance[0]++;
			if(currentStance[0] > 3) currentStance[0] = 0;
			adjustLean(0);
		}
		if(key == KeyEvent.VK_C) punchLeft[0] = true;
		if(key == KeyEvent.VK_V) punchRight[0] = true;
		
		if(key == KeyEvent.VK_5) up[1] = true;
		if(key == KeyEvent.VK_8) down[1] = true;
		if(key == KeyEvent.VK_6) right[1] = true;
		if(key == KeyEvent.VK_4) left[1] = true;
		if(key == KeyEvent.VK_0) block[1] = true;
		if(key == KeyEvent.VK_ENTER) shift[1] = true;
		if(key == KeyEvent.VK_7 && leanLeft[1] == false){
			leanLeft[1] = true;
			currentStance[1]--;
			if(currentStance[1] < 0) currentStance[1] = 3;
			adjustLean(1);
		}
		if(key == KeyEvent.VK_9 && leanRight[1] == false){
			leanRight[1] = true;
			currentStance[1]++;
			if(currentStance[1] > 3) currentStance[1] = 0;
			adjustLean(1);
		}
		if(key == KeyEvent.VK_1) punchLeft[1] = true;
		if(key == KeyEvent.VK_2) punchRight[1] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		
		if(key == KeyEvent.VK_W) up[0] = false;
		if(key == KeyEvent.VK_S) down[0] = false;
		if(key == KeyEvent.VK_D) right[0] = false;
		if(key == KeyEvent.VK_A) left[0] = false;
		if(key == KeyEvent.VK_SPACE) block[0] = false;
		if(key == KeyEvent.VK_SHIFT) shift[0] = false;
		if(key == KeyEvent.VK_Q && leanLeft[0] == true) leanLeft[0] = false;
		if(key == KeyEvent.VK_E && leanRight[0] == true) leanRight[0] = false;
		if(key == KeyEvent.VK_C) punchLeft[0] = false;
		if(key == KeyEvent.VK_V) punchRight[0] = false;
		
		if(key == KeyEvent.VK_5) up[1] = false;
		if(key == KeyEvent.VK_8) down[1] = false;
		if(key == KeyEvent.VK_6) right[1] = false;
		if(key == KeyEvent.VK_4) left[1] = false;
		if(key == KeyEvent.VK_0) block[1] = false;
		if(key == KeyEvent.VK_ENTER) shift[1] = false;
		if(key == KeyEvent.VK_7 && leanLeft[1] == true) leanLeft[1] = false;
		if(key == KeyEvent.VK_9 && leanRight[1] == true) leanRight[1] = false;
		if(key == KeyEvent.VK_1) punchLeft[1] = false;
		if(key == KeyEvent.VK_2) punchRight[1] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//char key = arg0.getKeyChar();
		
		
	}
	
	public boolean getUp(int i){
		return up[i];
	}
	
	public boolean getDown(int i){
		return down[i];
	}
	
	public boolean getRight(int i){
		return right[i];
	}
	
	public boolean getLeft(int i){
		return left[i];
	}
	
	public boolean getBlock(int i){
		return block[i];
	}
	
	public boolean getShift(int i){
		return shift[i];
	}
	
	public boolean getPunchLeft(int i){
		return punchLeft[i];
	}
	
	public boolean getPunchRight(int i){
		return punchRight[i];
	}
	
	private void adjustLean(int i){
		for(int j = 0; j < stances[i].length; j++){
			stances[i][j] = false;
		}
		switch(currentStance[i]){
		case 0:
			stances[i][0] = true;
			break;
		case 1:
			stances[i][3] = true;
			break;
		case 2:
			stances[i][1] = true;
			break;
		case 3:
			stances[i][2] = true;
			break;
		}
	}
	
	public int getStance(int j){
		int ret = 0;
		for(int i = 0; i < stances[j].length; i++){
			if(stances[j][i]){
				ret = i;
				break;
			}
		}
		
		return ret;
	}
}