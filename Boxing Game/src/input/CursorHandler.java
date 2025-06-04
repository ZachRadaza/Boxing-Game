package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class CursorHandler extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel[] panels;
	private boolean[] stances; //middle, bottom, left, right
	
	private boolean leftClick = false;
	private boolean rightClick = false;
	
	public CursorHandler(){
		this.setOpaque(false);
		this.setLayout(null);
		this.setBounds(0, 0, 1920, 1080);
		
		setPanels();
	}
	
	private void setPanels(){
		stances = new boolean[4];
		for(int i = 0; i < stances.length; i++) stances[i] = false;
		
		panels = new JPanel[4];
		
		int[] x = {this.getWidth()/3, 0, 0, (this.getWidth() / 3) * 2};
		int[] y = {0, (this.getHeight() / 3 * 2), 0, 0};
		int[] width = {this.getWidth() / 3, this.getWidth(), this.getWidth() / 3, this.getWidth() / 3};
		int[] height = {(this.getHeight() / 3) * 2, (this.getHeight() / 3), (this.getHeight() / 3) * 2, (this.getHeight() / 3) * 2};
		
		for(int i = 0; i < panels.length; i++){
			panels[i] = new JPanel();
			panels[i].setOpaque(false);
			panels[i].setBounds(x[i], y[i], width[i], height[i]);
			panels[i].addMouseListener(setMouseListener(i));	
			this.add(panels[i]);
		}
	}
	
	private MouseListener setMouseListener(int i){
		MouseListener ret = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				stances[i] = true;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				stances[i] = false;
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				int click = e.getButton();
				switch(click){
				case MouseEvent.BUTTON1:
					leftClick = true;
					break;
				case MouseEvent.BUTTON3:
					rightClick = true;
					break;
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				int click = e.getButton();
				switch(click){
				case MouseEvent.BUTTON1:
					leftClick = false;
					break;
				case MouseEvent.BUTTON3:
					rightClick = false;
					break;
				}
			}
		};
		return ret;
	}
	//return current state since only one can be true
	public int getStance(){
		int ret = 0;
		for(int i = 0; i < stances.length; i++){
			if(stances[i]){
				ret = i;
				break;
			}
		}
		return ret;
	}
	
	public boolean getLeftClick(){
		return leftClick;
	}
	
	public boolean getRightClick(){
		return rightClick;
	}
}