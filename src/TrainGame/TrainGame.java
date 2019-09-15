package TrainGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;




public class TrainGame implements ActionListener, KeyListener {
	public static final Color BORDER_COLOR = Color.ORANGE;
	public static final Color BACKGROUND_COLOR = Color.CYAN;
	public static final Color FOOD_COLOR = Color.ORANGE;
	public static final int WIDTH = 30;
	public static final int HEIGHT = 20;
	public static final int WINDOW_SCALE = 50;
	public static final int WINDOW_WIDTH = WINDOW_SCALE * WIDTH;
	public static final int WINDOW_HEIGHT = WINDOW_SCALE * HEIGHT;
	private JFrame window;
	private JPanel panel;
	private Train train;
	private Timer timer;
	private Location foodLocation;
	public static BufferedImage image;
	public static BufferedImage bg_image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
		    needImage = false;
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    }
	}

	public TrainGame() {
		train = new Train(new Location(WIDTH / 2, HEIGHT / 2));
		window = new JFrame("Samuel Train");
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
					try {
						bg_image = ImageIO.read(this.getClass().getResourceAsStream("/TrainGame/tracks.jpg"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					g.drawImage(bg_image, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
					g2.setColor(BACKGROUND_COLOR);
					g2.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
				if (needImage) {
					Random r = new Random();
				    loadImage("/TrainGame/com" + r.nextInt(12) + ".png");
				}
				if (gotImage) {
					if(image.getWidth()>800) {
						g.drawImage(image, foodLocation.x * WINDOW_SCALE, foodLocation.y * WINDOW_SCALE, image.getWidth()/8, image.getHeight()/8, null);
					}
					else {
						g.drawImage(image, foodLocation.x * WINDOW_SCALE, foodLocation.y * WINDOW_SCALE, image.getWidth()/2, image.getHeight()/2, null);	
					}
					g2.setColor(FOOD_COLOR);
					g2.drawOval(foodLocation.x * WINDOW_SCALE, foodLocation.y * WINDOW_SCALE, Train.BODY_SIZE, Train.BODY_SIZE);
				
				}
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
		
		String instructions = "Welcome to S Train! Select a difficulty below:";

		String[] options = new String[] { "Level 3", "Level 2", "Level 1", "Tutorial" };
		int input = JOptionPane.showOptionDialog(null, instructions, "Samuel Train", 0, -1, null, options, 0);

		String choice = options[input];

		
		
		
		switch (choice) {
		case "Level 3":
			timer.setDelay(40);
			break;
		case "Level 2":
			timer.setDelay(50);
			break;
		case "Level 1":
			timer.setDelay(100);
			break;
		case "Tutorial":
			tutorial();
			break;
		}
		
		timer.start();
	}

	private void tutorial() {
		JOptionPane.showMessageDialog(null, "To move around, use the arrow keys.", "Tutorial: 25%", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Your goal is to pick up passengers without hitting the walls (derailing) or hitting yourself.", "Tutorial: 50%", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "To pick up a passenger, drive over their circular orange hitbox. A new passenger will appear.", "Tutorial: 75%", JOptionPane.INFORMATION_MESSAGE);
		String[] tutplayrestart = new String[] { "Let's start easy...", "Back to main menu", "Restart Tutorial" };
		int tutplayrestart_i = JOptionPane.showOptionDialog(null, "You're done! Select an option below...", "Tutorial: Done", 0,JOptionPane.INFORMATION_MESSAGE, null, tutplayrestart, 0);
		String choice = tutplayrestart[tutplayrestart_i];
		switch(choice) {
		case "Let's start easy...":
			timer.setDelay(700);
			timer.start();
			break;
		case "Back to main menu":
			startGame();
			break;
		case "Restart Tutorial":
			tutorial();
			break;
		}
	}

	public static void main(String[] args) {
		new TrainGame();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
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
		
		case KeyEvent.VK_ESCAPE:
			gameOver();
			break;
		}
		
		

		

	}

	private void setFoodLocation() {
		
		Random r = new Random();
		Location l = new Location(r.nextInt(WIDTH), r.nextInt(HEIGHT));
		needImage = true;
		gotImage = false;
		if (!train.isLocationOnTrain(l)) {
			foodLocation = l;
		}
	}

	private void gameOver() {

		
		timer.stop();
		
		JOptionPane.showMessageDialog(null,
				"Your train crashed. I'm very sorry. But that's OK, it was just a test train. (STILL, WE HAVE TO BUY NEW S70S NOW! THANKS FOR NOTHING!)",
				"Game Over", JOptionPane.ERROR_MESSAGE);
		
		int play = JOptionPane.showOptionDialog(null, "Do u want to bring train back to life and play again?",
				"Play Again", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (play == 0) {
			timer.start();
			train.reset(new Location(2, 2));
			foodLocation = new Location(5, 5);
		} else {
			System.exit(1000);
		}
		
		
		

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		train.update();
		
		
		if(train.isHeadCollidingWithBody()) {
			gameOver();
		}
		if(train.isOutOfBounds()) {
			gameOver();
		}		
		
		if(train.getHeadLocation().equals(foodLocation)) {
			train.feed();
			setFoodLocation();
		}
		
		panel.repaint();
	}
}
