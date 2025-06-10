package main;

import graphics.Frame;

public class Main{
	
	private static Frame frame = new Frame();
	
	public static void main(String[] args){
		setFrame();
	}
	
	private static Frame setFrame(){
		GamePanel panel = new GamePanel(false);
		frame.add(panel);
		
		return frame;
	}
	
	public static Frame getFrame(){
		return frame;
	}
}