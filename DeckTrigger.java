import java.util.LinkedList;

public class DeckTrigger<E> extends LinkedList<E> {
	public DeckTrigger() {}
	
	//put a card at the end of the deck
	public void put(E o) {
		addLast(o);
	}
	
	//return the first card and remove it
	public E get() {
		if (!this.isEmpty()) {
			return removeFirst();
		} else {
			System.err.println("You can\'t do that!");
			return null;
		} 
	}
	
	//return the first card of a deck
	public E peek() {
		return getFirst();
	}
}