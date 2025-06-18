package graphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Color bgColor = Color.WHITE;

	public Frame(){
		
		this.setTitle("Boxing Game");
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setSize(new Dimension(1980, 1080));
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(bgColor);
		
		this.setResizable(false);
		
		ImageIcon image = new ImageIcon(""); //add a pixel glove
		this.setIconImage(image.getImage());
		
		this.setVisible(true);
	}
}