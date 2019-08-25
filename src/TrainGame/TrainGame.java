package TrainGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

// Go through the methods and complete the steps in this class
// and the train class

public class TrainGame implements ActionListener, KeyListener {
	public static final Color BORDER_COLOR = Color.WHITE;
	public static final Color BACKGROUND_COLOR = Color.BLACK;
	public static final Color FOOD_COLOR = Color.RED;
	public static final int WIDTH = 15;
	public static final int HEIGHT = 12;
	public static final int WINDOW_SCALE = 50;
	public static final int WINDOW_WIDTH = WINDOW_SCALE * WIDTH;
	public static final int WINDOW_HEIGHT = WINDOW_SCALE * HEIGHT;

	private JFrame window;
	private JPanel panel;

	private Train train;

	private Timer timer;

	private Location foodLocation;

	public TrainGame() {
		train = new Train(new Location(WIDTH / 2, HEIGHT / 2));

		window = new JFrame("train");
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;

				g2.setColor(BACKGROUND_COLOR);
				g2.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

				g2.setColor(FOOD_COLOR);
				g2.drawOval(foodLocation.x * WINDOW_SCALE, foodLocation.y * WINDOW_SCALE, Train.BODY_SIZE,
						Train.BODY_SIZE);
				train.draw(g);
			}
		};

		panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		window.add(panel);

		timer = new Timer(0, this);

		window.pack();
		window.addKeyListener(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		setFoodLocation();

		startGame();
	}

	public void startGame() {
		// 1. Save the instructions for the game in the following string variable.
		String instructions = "Welcome to Samuel train! (try not to die lol)";

		String[] options = new String[] { "GOD", "BOSS", "noob" };
		int input = JOptionPane.showOptionDialog(null, instructions, "S train", 0, -1, null, options, 0);

		String choice = options[input];

		// 2. Use a switch statement to determine which difficulty was chosen.
		// Use timer.setDelay(delay) with different numbers to change the speed
		// of the game. The smaller the number, the faster it goes.
		switch (choice) {
		case "GOD":
			timer.setDelay(10);
			break;
		case "BOSS":
			timer.setDelay(30);
			break;
		case "noob":
			timer.setDelay(50);
			break;
		}
		// 3. start the timer
		timer.start();
	}

	public static void main(String[] args) {
		new TrainGame();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 1. Use a switch statement on e.getKeyCode()
		// to determine which key was pressed.
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			train.setDirection(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			train.setDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			train.setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			train.setDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_SPACE:
			train.feed();
		}
		// if an arrow key is pressed, set the train's
		// direction accordingly

		// if the space key is pressed, call the train's feed method

	}

	private void setFoodLocation() {
		// 1. Create a new Location object that is set to a random location
		Random r = new Random();
		Location l = new Location(r.nextInt(WIDTH), r.nextInt(HEIGHT));
		// 2. set the foodLocation variable equal to the Location object you just
		// created.
		// use the train's isLocationOntrain method to make sure you don't put the food
		// on the train
		if (!train.isLocationOnTrain(l)) {
			foodLocation = l;
		}
	}

	private void gameOver() {

		// 1. stop the timer
		timer.stop();
		// 2. tell the user their train is dead
		JOptionPane.showMessageDialog(null,
				"Your train died. I'm very sorry. If you need mental support, call +1 619-468-5480 or visit http://tiny.cc/deadtrain",
				"Game Over", JOptionPane.ERROR_MESSAGE);
		// 3. ask them if they want to play again.
		int play = JOptionPane.showOptionDialog(null, "Do u want to bring train back to life and play again?",
				"Play Again", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (play == 0) {
			train.reset(new Location(2, 2));
			foodLocation = new Location(5, 5);
		} else {
			System.exit(1000);
		}
		// 4. if they want to play again
		// reset the train and the food and start the timer
		// else, exit the game

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//1. update the train
		train.update();
		//2. if the train is colliding with its own body 
		//   or if the train is out of bounds, call gameOver
		if(train.isHeadCollidingWithBody() || train.isOutOfBounds()) {
			gameOver();
		}
		//3. if the location of the head is equal to the location of the food,
		// 	 feed the train and set the food location
		if(train.getHeadLocation() == foodLocation) {
			train.feed();
			setFoodLocation();
		}
		//4. call panel.repaint();
		panel.repaint();
	}
}
