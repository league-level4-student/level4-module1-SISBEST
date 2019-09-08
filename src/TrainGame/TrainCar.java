package TrainGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public class TrainCar {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	private Location location;
	private int size;
	void loadImage(String imageFile) {
	    if (needImage) {
	    	System.out.println("GETTING IMAGE!");
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        needImage = false;
	    }
	}
	public TrainCar(Location loc, int size) {
		this.location = loc;
		this.size = size;
	}
	
	public void setLocation(Location loc) {
		this.location = loc;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void draw(Graphics g) throws IOException {
		if (needImage) {
		    loadImage("/TrainGame/car.jpg");
		}
		if (gotImage) {
			g.drawImage(image, location.x * size, location.y * size, size, size, null);
		} else {
			g.setColor(Train.SNAKE_COLOR);
		    g.drawRect(location.x * size, location.y * size, size, size);
		
		}
		}
}
