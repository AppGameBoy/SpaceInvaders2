package model.observerPattern.shooterObserverPattern;

import model.Shooter;

public interface Subject {
	
	void addShooterListener(Observer o);
	void removeShooterListener(Observer o);
	void notifyObserver(Shooter.Event event);
	

}
