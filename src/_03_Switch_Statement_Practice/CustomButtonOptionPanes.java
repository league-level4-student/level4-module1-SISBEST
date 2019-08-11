package _03_Switch_Statement_Practice;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

public class CustomButtonOptionPanes {
	public static void main(String[] args) {
		String[] options = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

		int input = JOptionPane.showOptionDialog(null, "Choose a day of the week", "Custom Buttons", 0, -1, null,
				options, 0);

		String choice = options[input];

		// use a switch statement to do something cool for each option
		switch (choice) {
		case "Sunday":
			filemadness();
			break;
		case "Monday":
			techdif();
			break;
		
		case "Tuesday":
			try {
				Desktop.getDesktop().browse(new URI("http://www.youtube.com/TomScottGo"));
			} catch (IOException | URISyntaxException e) {
				System.out.println("Whoops, your computer didn't work. That's something you might not have known!");
			}
			break;
		case "Wednesday":
			try {
				Desktop.getDesktop().browse(new URI("http://www.youtube.com/AntVenom"));
			} catch (IOException | URISyntaxException e) {
				System.out.println("Whoops, your computer didn't work. That's about it, for me for now.");
			}
			break;
		case "Thursday":
			JOptionPane.showMessageDialog(null, "ClIcK oK tO sEe ThE bEsT mOvIe On ThE iNtErNeT!");
			try {
				Desktop.getDesktop().browse(new URI("http://apps.samuelsharp.com/cohdfiles/ep1.mp4"));
			} catch (IOException | URISyntaxException e) {
				System.out.println("Whoops, your computer didn't work.");
			}
			break;
		case "Friday":
			try {
				Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=DPVTl9K0lqc"));
			} catch (IOException | URISyntaxException e) {
				System.out.println("Whoops, your computer didn't work.");
			}
			break;
		case "Saturday":
			try {
				Desktop.getDesktop().browse(new URI("http://www.bbc.co.uk/newsbeat/article/12784330/worst-song-ever-gets-29m-views-after-going-viral"));
			} catch (IOException | URISyntaxException e) {
				System.out.println("Whoops, your computer didn't work.");
			}
			break;
		default:
			System.out.println("BEEP!!!!");
		}
	}

	static void techdif() {
		JOptionPane.showMessageDialog(null, "I WILL GUESS YOUR NAME!");
		String[] techdif_options = { "Person by day, asleep by night.", "Built for leisure, not for speed.", "Professional broadcast engineer.", "Once got five gold runs on Blockbusters."};

		int techdif_input = JOptionPane.showOptionDialog(null, "What is your personality?", "The Technical Difficulties", 0, -1, null,
				techdif_options, 0);

		String techdif_choice = techdif_options[techdif_input];
		String techdif_name = "Tom Scott";
		switch(techdif_choice) {
		case "Person by day, asleep by night.":
			techdif_name = "Gary Brannan";
			break;
		
		case "Built for leisure, not for speed.":
			techdif_name = "Gary Brannan";
			break;
	
		case "Professional broadcast engineer.":
			techdif_name="Matt Gray";
			break;
	
		case "Once got five gold runs on Blockbusters.":
			techdif_name="Tom Scott";
			break;
		default:
			techdif_name="Chris Joel";
			break;
		}
		JOptionPane.showMessageDialog(null, "Your name is " + techdif_name + "!");
	}
	static void filemadness() {
		JOptionPane.showMessageDialog(null, "It's Sunday. Your computer hates you.");
		File f = new File("beep.txt");
		while (true) {
			try {
				f.createTempFile("beep", "kjdkedkdfjedjj3we", null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("ha ha ha");
		}
	}
	}
