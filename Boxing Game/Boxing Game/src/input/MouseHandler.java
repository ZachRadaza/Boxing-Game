package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener{

	private boolean leftClick = false;
	private boolean rightClick = false;
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int click = arg0.getButton();
		switch(click){
		case 1:
			leftClick = true;
			break;
		case 3:
			rightClick = true;
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int click = arg0.getButton();
		switch(click){
		case 1:
			leftClick = false;
			break;
		case 3:
			rightClick = false;
			break;
		}
	}
	
	public boolean getLeftClick(){
		return leftClick;
	}
	
	public boolean getRightClick(){
		return rightClick;
	}
	
	public void setLeftClick(boolean leftClick){
		this.leftClick = leftClick;
	}
	
	public void setRightClick(boolean rightClick){
		this.rightClick = rightClick;
	}
}