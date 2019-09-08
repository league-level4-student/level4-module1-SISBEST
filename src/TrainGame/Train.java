package TrainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

public class Train {
	public static final Color SNAKE_COLOR = Color.BLUE;
	public static final int BODY_SIZE = 50;

	private TrainCar head;
	public ArrayList<TrainCar> train;

	private Direction currentDirection;

	private boolean canMove = false;

	public Train(Location location) {
		train = new ArrayList<TrainCar>();
		head = new TrainCar(location, BODY_SIZE);
		train.add(head);

		currentDirection = Direction.RIGHT;
	}

	public void feed() {
		
		train.add(new TrainCar(train.get(0).getLocation(), BODY_SIZE));
		update();
	}

	public Location getHeadLocation() {
		
		return head.getLocation();
	}

	public void update() {
		
		
		int nx = 0;
		int ny = 0;
		switch (currentDirection) {
		case DOWN:
			ny += 1;
			break;
		case UP:
			ny -= 1;
			break;
		case LEFT:
			nx -= 1;
			break;
		case RIGHT:
			nx += 1;
			break;
		}
		
		
		
		for (int i = train.size() - 1; i > 0; i--) {
			TrainCar s = train.get(i);
			TrainCar t = train.get(i - 1);
			s.setLocation(t.getLocation());
		}
		
		Location nl = new Location(head.getLocation().x + nx, head.getLocation().y + ny);
		head.setLocation(nl);
		
		canMove = true;
	}

	public void setDirection(Direction d) {
		
		
		
		
		if (canMove) {
			currentDirection = d;
			canMove = false;
		}
	}

	public void reset(Location loc) {
		
		train = new ArrayList<TrainCar>();
		
		head.setLocation(loc);
		
		train.add(head);
		update();
	}

	public boolean isOutOfBounds() {
		
		
		
		if (head.getLocation().x > 15 || head.getLocation().x < 0) {
			return true;
		}
		if (head.getLocation().y > 12 || head.getLocation().y < 0) {
			return true;
		}
		return false;
	}

	public boolean isHeadCollidingWithBody() {
		
		
		for (int i = 1; i < train.size(); i++) {
			if (head.getLocation().equals(train.get(i).getLocation())) {
				return true;
			}
		}
		return false;
	}

	public boolean isLocationOnTrain(Location loc) {
		
		
		for (int i = 0; i < train.size(); i++) {
			if (loc.equals(train.get(i).getLocation())) {
				return true;
			}
		}
		return false;
	}

	public void draw(Graphics g) {
		for (TrainCar s : train) {
			try {
				s.draw(g);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
