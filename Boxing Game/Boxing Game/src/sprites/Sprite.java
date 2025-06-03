package sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import resources.stopWatchX;

public class Sprite{
	
	private String[] image; //3 for simple animation
	private int index;
	private stopWatchX stopWatch;
	
	public Sprite(String image1, String image2, String image3){
		image = new String[4];
		this.image[0] = image1;
		this.image[1] = image2;
		this.image[2] = image1;
		this.image[3] = image3;
		
		this.index = 0;
		this.stopWatch = new stopWatchX(300);
	}
	
	public Sprite(String image1){
		image = new String[4];
		for(int i = 0; i < image.length; i++){
			image[i] = image1;
		}
		
		this.index = 0;
		this.stopWatch = new stopWatchX(300);
	}
	
	public BufferedImage getImage(){
		try {
			if(stopWatch.isTimeUp()){
				index++;
				if(index > image.length - 1) index = 0;
				stopWatch.resetWatch();
			}
			
			return ImageIO.read(new File(image[index]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}