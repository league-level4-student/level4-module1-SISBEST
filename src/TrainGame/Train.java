package TrainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Train {
	public static final Color SNAKE_COLOR = Color.BLUE;
	public static final int BODY_SIZE = 50;

	private TrainCar head;
	private ArrayList<TrainCar> train;

	private Direction currentDirection;

	private boolean canMove = true;

	public Train(Location location) {
		train = new ArrayList<TrainCar>();
		head = new TrainCar(location, BODY_SIZE);
		train.add(head);

		currentDirection = Direction.RIGHT;
	}

	public void feed() {
		// 1. add a new TrainSegment object to the train
		train.add(new TrainCar(train.get(0).getLocation(), BODY_SIZE));
	}

	public Location getHeadLocation() {
		// 2. return the location of the train's head
		return head.getLocation();
	}

	public void update() {
		// 1. use a switch statement to check on the currentDirection
		// of the train and calculate its next x and y position.
		switch (currentDirection) {
		case DOWN:
			head.setLocation(new Location(head.getLocation().x, head.getLocation().y + 1));
			break;
		case UP:
			head.setLocation(new Location(head.getLocation().x, head.getLocation().y - 1));
			break;
		case LEFT:
			head.setLocation(new Location(head.getLocation().x - 1, head.getLocation().y));
			break;
		case RIGHT:
			head.setLocation(new Location(head.getLocation().x + 1, head.getLocation().y));
			break;
		}
		// 2. Iterate through the TrainSegments in reverse order
		// 2a. Update each train segment to the location of the segment
		// in front of it.
		for (int i = train.size(); i > 0; i--) {
			if (train.size() == 1) {
				head.setLocation(train.get(i - 1).getLocation());
			} else {
				train.get(i).setLocation(train.get(i - 1).getLocation());
			}
		}
		// 3. set the location of the head to the new location calculated in step 1
		// Did that in switch
		// 4. set canMove to true
		canMove = true;
	}

	public void setDirection(Direction d) {
		// 1. set the current direction equal to the passed in Direction only if canMove
		// is true.
		// set canMove equal to false.
		// make sure the train cannot completely reverse directions.
		if (canMove) {
			currentDirection = d;
			canMove = false;
		}
	}

	public void reset(Location loc) {
		// 1. clear the train
		for (int i = 0; i < train.size(); i++) {
			train.remove(i);
		}
		// 2. set the location of the head
		head.setLocation(loc);
		// 3. add the head to the train
		train.add(head);
		update();
	}

	public boolean isOutOfBounds() {
		// 1. complete the method so it returns true if the head of the train is outside
		// of the window
		// and false otherwise
		if (head.getLocation().x > 15 || head.getLocation().x < 0) {
			return true;
		}
		if (head.getLocation().y > 12 || head.getLocation().y < 0) {
			return true;
		}
		return false;
	}

	public boolean isHeadCollidingWithBody() {
		// 1. complete the method so it returns true if the head is located
		// in the same location as any other body segment
		for (int i = 1; i < train.size(); i++) {
			if (head.getLocation() == train.get(i).getLocation()) {
				return true;
			}
		}
		return false;
	}

	public boolean isLocationOnTrain(Location loc) {
		// 1. complete the method so it returns true if the passed in
		// location is located on the train
		for (int i = 0; i < train.size(); i++) {
			if (loc == train.get(i).getLocation()) {
				return true;
			}
		}
		return false;
	}

	public void draw(Graphics g) {
		for (TrainCar s : train) {
			s.draw(g);
		}
	}
}
