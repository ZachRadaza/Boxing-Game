package sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import resources.stopWatchX;

public class Sprite{
	
	private BufferedImage[] images;
    private int index;
    private stopWatchX stopWatch;
    private boolean reset;
    private boolean noLoop;

    public Sprite(String image1, String image2, String image3) {
        images = new BufferedImage[4];
        images[0] = loadImage(image1);
        images[1] = loadImage(image2);
        images[2] = loadImage(image1);
        images[3] = loadImage(image3);

        index = 0;
        stopWatch = new stopWatchX(300);
        reset = true;
        noLoop = false;
    }

    public Sprite(String image1) {
        images = new BufferedImage[4];
        BufferedImage img = loadImage(image1);
        for (int i = 0; i < images.length; i++) {
            images[i] = img;
        }

        index = 0;
        stopWatch = new stopWatchX(300);
        reset = true;
        noLoop = false;
    }

    public Sprite(String image1, String image2) {
        images = new BufferedImage[4];
        images[0] = loadImage(image1);
        images[1] = loadImage(image2);
        images[2] = loadImage(image2);
        images[3] = loadImage(image1);

        index = 0;
        stopWatch = new stopWatchX(70);
        reset = true;
        noLoop = false;
    }
    
    public Sprite(String image1, String image2, boolean noLoop) {
        images = new BufferedImage[4];
        images[0] = loadImage(image1);
        images[1] = loadImage(image2);
        images[2] = loadImage(image2);
        images[3] = loadImage(image1);

        index = 0;
        stopWatch = new stopWatchX(1000);
        
        reset = true;
        this.noLoop = noLoop;
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BufferedImage getImage() {
        if(stopWatch.isTimeUp() && reset) {
            index = (index + 1) % images.length;
            stopWatch.resetWatch();
            if(noLoop) reset = false;
        }
        return images[index];
    }

    public void resetStopWatch() {
        stopWatch.resetWatch();
    }

}