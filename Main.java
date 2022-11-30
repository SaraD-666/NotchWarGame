import java.util.ArrayList;

public class Main {

	private static Deck deck = new Deck();
	private static DeckTrigger<Card> player1 = new DeckTrigger<Card>();
	private static DeckTrigger<Card> player2 = new DeckTrigger<Card>();
	private static DeckTrigger<Card> tempCards = new DeckTrigger<Card>();

	public static void main(String[] args) {
		deal();
		playNotchWar();
		;}


	public static void deal() {
		deck.shuffle();

		//give player1 26 cards
		for (int i = 0; i < 26; i++) {
			player1.put(deck.getCard());
		}

		//give player2 26 cards
		for (int i = 0; i < 26; i++) {
			player2.put(deck.getCard());
		}
	}

	//	//the difference between the two cards
	public static int getDifference(DeckTrigger<Card> playerA, DeckTrigger<Card> playerB) {
		return player1.peek().compareTo(player2.peek());
	}


	//	//simplify the codes a little bit
	private static void playerWin(DeckTrigger<Card> playerA, DeckTrigger<Card> playerB){
		playerA.put(playerB.get());
		playerA.put(playerA.get());
	}

	//    //simplify the codes more
	private static String round(int difference){
		if (difference > 1){
			playerWin(player1, player2);
			return "player1";
		}
		if (difference == 1){
			playerWin(player2, player1);
			return "player2";
		}
		if (difference < -1) { 
			playerWin(player2, player1);
			return "player2";
		}
		playerWin(player1, player2);
		return "player1";
	}
	public static String playRound() {
		if (player1.size() + player2.size() == 52) {
			System.out.println("Player one has " + player1.size() + ", Player two has " + player2.size());
			System.out.println(player1.peek() + " versus " + player2.peek());

		}
		if (getDifference(player1, player2) == 0) {
			war();
			return "tie";
		}
		if (getDifference(player1, player2) == 1 || getDifference(player1, player2) == -1) {

			System.out.println(" (Notched!)");
		}
		return round(getDifference(player1, player2));
	}

	public static String war() {
		DeckTrigger<Card> tempCardOne = new DeckTrigger<Card>();
		DeckTrigger<Card> tempCardTwo = new DeckTrigger<Card>();
		System.out.println("WAR!");
		if (player1.size() >= 5 && player2.size() >= 5) {
			for(int i = 0; i < 4; i++) {
				tempCardOne.put(player1.get());
				tempCardTwo.put(player2.get());
			}
			for (int i = 0; i < 4; i++) {
				tempCards.put(tempCardOne.get());
				tempCards.put(tempCardTwo.get());
			}
		}
		if (player2.size() < 5) {
			for (int i = 0; i < player2.size() - 1; i++) {
				tempCardTwo.put(player2.get());
			}
			for(int i = 0; i < 4; i++) {
				tempCardOne.put(player1.get());
			}
			for (int i = 0; i < tempCardTwo.size(); i++) {
				tempCards.put(tempCardTwo.get());
			}
			for (int i = 0; i < 4; i++) {
				tempCards.put(tempCardOne.get());
			}
		}
		if (player1.size() < 5) {
			for (int i = 0; i < player1.size() - 1; i++) {
				tempCardOne.put(player1.get());
			}
			for(int i = 0; i < 4; i++) {
				tempCardTwo.put(player2.get());
			}
			for (int i = 0; i < tempCardOne.size(); i++) {
				tempCards.put(tempCardOne.get());
			}
			for (int i = 0; i < 4; i++) {
				tempCards.put(tempCardTwo.get());
			}
		}

		System.out.println(player1.peek() + " versus " + player2.peek());
		String pr = playRound();
		
		int tempCardTotal = tempCards.size();

		if (pr.equals("player1")) {
			for (int i = 0; i < tempCardTotal; i++) {
				player1.put(tempCards.get());
			}

		}
		//if player two wins, he gets all 8 cards
		else{
			for (int i = 0; i < tempCardTotal; i++) {
				player2.put(tempCards.get());
			}
		}
		System.out.println("Player one has " + player1.size() + ", Player two has " + player2.size());
		return pr;
	}

	public static String playNotchWar() {
		while (!player1.isEmpty() && !player2.isEmpty()) {
			playRound();
		}
		if (player1.isEmpty()) {
			System.out.println("The winner is Player two!");
		}
		else{
			System.out.println("The winner is Player One!");
		}
		return "Game End";
	}

}
