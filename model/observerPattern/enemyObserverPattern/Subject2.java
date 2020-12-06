package model.observerPattern.enemyObserverPattern;

import model.EnemyComposite;

public interface Subject2 {
	void addEnemyListener(Observer2 o);
	void removeEnemyListener(Observer2 o);
	void notifyObserver(EnemyComposite.Event2 event);
}
