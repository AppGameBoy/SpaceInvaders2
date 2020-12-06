package model.observerPattern.shooterObserverPattern;

public interface Observer {
	void enemyIsDestroyed();
	void shooterIsDamaged();
	void annihilation();
	void touchEnemies();
}
