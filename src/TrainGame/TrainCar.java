package TrainGame;

import java.awt.Graphics;

public class TrainCar {
	private Location location;
	private int size;

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
	
	public void draw(Graphics g) {
		g.setColor(Train.SNAKE_COLOR);
		g.drawRect(location.x * size, location.y * size, size, size);;
	}
}
