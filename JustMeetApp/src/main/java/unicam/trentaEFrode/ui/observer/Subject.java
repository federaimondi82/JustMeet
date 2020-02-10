package unicam.trentaEFrode.ui.observer;

public interface Subject {

	/**
	 * 
	 * @param observer
	 */
	void aggiungiObserver(Observer observer);

	/**
	 * 
	 * @param observer
	 */
	void rimuoviObserver(Observer observer);

	Observer getObservers();

	void notificaTutti();

}