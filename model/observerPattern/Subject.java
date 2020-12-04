package model.observerPattern;

import model.Shooter;

public interface Subject {
	
	void addShooterListener(Observer o);
	void removeShooterListener(Observer o);
	void notifyObserver(Shooter.Event event);
	

}
