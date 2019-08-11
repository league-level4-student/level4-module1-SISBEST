package _05_Enum_Stuff;

import javax.swing.JOptionPane;

public class _00_Horoscope {
	// 1. Create an enum in a separate file called Zodiac that contains a category for
	//    all 12 zodiac signs.
	
	// 2. Write a method that takes in a Zodiac enum object and uses a JOPtionPane to display
	//    a different horoscope based on the Zodiac's state.
	
	// 3. Make a main method to test your method
	public static void main(String[] args) {
		System.out.println(geths(Zodiac.SAGITTARIUS));
		
	}
	public static String geths(Zodiac z) {
		String hs = "Nothing shall happen.";
		switch(z) {
		case ARIES:
			hs = "Dependability and stability are key aspects of today, Aries. Take action that will prove to others that these parts of your nature are fully functional. Other people may be stubborn and not easily moved. You will have to fight to get your viewpoint across. You may need to get out the bulldozer. Maintain tenderness in all your relations with others.";
			break;
		case TAURUS:
			hs = "Be gentle and reserved in your approach, Taurus. There's a great deal of tenderness in the air that you should tune into and enjoy. Do things to improve your home. Make amends with the people you live with and clear the air so that relations will be calm in the future. You have a great deal of yourself invested in making your home a sanctuary, so respect and nourish it.";
			break;
		case GEMINI:
			hs = "This is a great day to go to thrift stores, Gemini. Your eye for beauty is keen and your radar for good deals is sharp. Lady Luck is with you and you may score some terrific buys. Concentrate on beautiful objects for your home that adorn your life. One person's trash is another's treasure. See how creative you can be with making old items into treasures.";
			break;
		case CANCER:
			hs = "Things that have been building for a long time suddenly come to a head today, Cancer. Strong, slow-moving trends rear their ugly heads. There's a great deal of opposition now making itself known in your life. Try not to lose yourself in the situation. See this as an opportunity to gain perspective and bring balance to the roller coaster that is your emotional life.";
			break;
		case LEO:
			hs = "It may be difficult to get moving today, Leo. You may want to just stay in bed. Don't feel guilty about it. Take an afternoon nap and enjoy the tender company of a partner. Stick close to home and enjoy good music and a fine meal with someone you love. Don't rock the boat. Simply relax and enjoy the pleasures life has to offer.";
			break;
		case VIRGO:
			hs = "Check your stocks today, Virgo. Read the latest investment news and assess your finances. Figure out the best place to put your hard-earned money. Do some serious long-term planning to make sure your home and family are secure. Take a reserved, stable approach in all your dealings with others, and spend a comfortable evening at home with someone you love.";
			break;
		case LIBRA:
			hs = "There may be something or someone trying to slow you down today, Libra. Maybe this is a sign that it's what you need to do. Don't fight the urge to sit on the couch and do nothing. Take this day to soak in a hot bath and recharge your spirit. Your personal possessions and security are of great importance now. Indulge in the good things that life has to offer.";
			break;
		case SCORPIO:
			hs = "Put some roots down today, Scorpio. Concentrate on a creative project that you've had in your head for a while. It's time to follow through. Ideas are great, but implementation is the key to making them work. Consolidate your resources and take the time to make a plan. The more patient you are in plotting your actions today, the more successful you will be in seeing them through to completion.";
			break;
		case SAGITTARIUS:
			hs = "If you plan on arguing for a cause today, make sure you have solid facts to back up your statements, Sagittarius. People aren't likely to fall for things too easily. It will take a great deal to change someone's mind about something. It's a great day for you to take a walk through the woods. Get back to the Earth. Try to bring some greenery into your living space.";
			break;
		case CAPRICORN:
			hs = "Today is a very expansive day for you, Capricorn. Long-term projects are coming into focus. There's a huge windfall coming your way. Your ego should experience an extra boost and your relationships should go extremely well. This would be a great time to attend to your financial investments and think about putting money into real estate or remodeling your home.";
			break;
		case AQUARIUS:
			hs = "Connect with people on a deep level, Aquarius. Much of your focus is on emotional security. Make sure your home is a sanctuary where you feel comfortable being exactly who you are. Demonstrate patience and understanding through your words and actions. The more solid and steady you are, the more you will do and the more you will connect with the people around you.";
			break;
		case PISCES:
			hs = "Much of your focus today will be on personal possessions and material goods in general, Pisces. Think about what you can do to improve your home. Make your physical surroundings comfortable and inviting. Spend time alone in your sanctuary where you can center and concentrate on the big picture. Physical comfort and beauty will translate into emotional security.";
			break;
		}
		return hs;
	}
}
